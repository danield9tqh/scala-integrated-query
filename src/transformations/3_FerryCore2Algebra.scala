package siq
trait FerryCore2Algebra extends RelationalAlgebra with FerryCore{
  import algebra._

  def position2column( i: Int ) = "item"+i // called cid in Tom's Ferry Thesis
  def ferrycore2algebra( from:ferry.Expression,
                         loop : Relation = LiteralTable(List(1), List("iter")),
                         scope : Map[String,Nested] = Map()
                        ) : Nested = {
    def itapp( left: Map[String,Nested], right: Map[String,Nested] ) = left ++ right // FIXME
    def colum2position( str: String ) = str.drop(4).toInt // called ord in Tom's Ferry Thesis
    // transformation closure
    def t( from:ferry.Expression,
           loop_ : Relation = loop,
           scope_ : Map[String,Nested] = scope
          ) = ferrycore2algebra(from,loop_,scope_)
    from match {
      case ferry.Box( boxee ) =>
        Nested(
          Attach( 1, "pos", Projection( ("iter", "iter"->"item1"), loop ) ),
          Map( "item1" -> t(boxee) )
        )
      case ferry.Unbox( unboxee ) => {
        val Nested( relation, itbls ) = t(unboxee)
        assert( relation.data_columns.size == 1, relation.data_columns )
        itbls( relation.data_columns(0) )
      }
      case ferry.Literal(value) =>
        Nested(
          Attach(
            value, position2column(1),
            Attach( 1, "pos", loop )
          ),
          Map()
        )
      case l@ferry.FerryList( values, element_type ) =>
        Nested(
          l.type_ match {
            case ferry.FerryCoreTypes.list(atomic) =>
              if(values.size == 0){
                LiteralTable(List(),List("iter","pos"))
              } else {
                val positional_names = List("item1")
                  CartesianProduct(
                    loop,
                    RowNumber( "pos", positional_names,
                      LiteralTable( values, List("item1") )
                    )
                  )
              }
          },
          Map()
        )
      case ferry.TableReference(name,columns,keys,order) => {
        val column_positional_names = columns.map(_._1)
                                             .zipWithIndex.map( name_index => ( name_index._1, position2column(name_index._2+1)) )
                                             .toMap
        Nested(
          CartesianProduct(
            loop,
            RowNumber( "pos", order.map( column_positional_names ),
              Projection(
                column_positional_names,
                Table( columns.map(_._1), name, keys )
              )
            )
          ),
          Map()
        )
      }
      case ferry.PositionalAccess( tuple, position ) => {
        val Nested(q_e,itbls_e) = t(tuple)
        val c_old = "item"+position
        Nested(
          Projection(
            ("iter","pos",c_old -> "item1"),
            q_e
          ),
          if( itbls_e.contains(c_old) ) Map( "item0" -> itbls_e(c_old) ) else Map()
        )
      }
      case ferry.VariableAccess( name, _, _ ) => scope(name)
      case ferry.For( name, in_, return_, orderBy ) => {
        val nested_e1 = t(in_)
        val q_e1 = nested_e1.relation
        val q_v = Attach(
          1, "pos",
          Projection(
            ( "inner_" -> "iter", q_e1.data_columns ),
            RowNumber( "inner_", List("iter","pos"), q_e1 )
          )
        )
        val loop_v = Projection( List("iter"), q_v )
        
        val map = Projection(
          ( "iter" -> "outer_", "inner_"  ),
          RowNumber( "inner_", List("iter","pos"), t(in_).relation ) // inner is already a reserved SQL keyword
        )
        val Nested( q_e2, itbls_e2 ) = t( return_, loop_v, scope+(name -> Nested(q_v,nested_e1.itbls) ) )
        val q_e2_ = Projection(
          ("outer_"->"inner_", "pos_"->"pos", q_e2.data_columns),
          RowNumber( "pos_", List("iter","pos"),
            Join( Operator( "=", Variable("iter"), Variable("inner_") ), q_e2, map ),
            Some(List("outer_"))
          )
        )
        Nested( q_e2_, itbls_e2 )
      }
      case ferry.Append( left, right ) =>
        val Nested( left_relation,  left_itbls  ) = t(left)
        val Nested( right_relation, right_itbls ) = t(right)
        require( left_relation.schema == right_relation.schema, left_relation.schema +","+right_relation.schema )
        val q = RowNumber( "item_", List("iter","ord","pos"),
          DisjointUnion(
            Attach( 1, "ord", left_relation ),
            Attach( 2, "ord", right_relation )
          )
        )
        val q_ = Projection(
          ( "iter", "pos_" -> "pos", left_relation.data_columns.diff( left_itbls.keys.toList ), "item_" -> left_itbls.keys ),
          RowRank("pos_",List("ord","pos"), q)
        )
        val itbls_ = itapp( left_itbls, right_itbls )
        Nested( q_, itbls_ )
    }
  }
}