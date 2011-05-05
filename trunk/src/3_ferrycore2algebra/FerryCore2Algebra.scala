package siq
trait FerryCore2Algebra extends RelationalAlgebra with FerryCore{
  import algebra._

  // actually the retur
  def ferrycore2algebra( from:ferry.Expression ) : Node = {
    def position2column( i: Int ) = "item"+i // called cid in Tom's Ferry Thesis
    def colum2position( str: String ) = str.drop(4).toInt // called ord in Tom's Ferry Thesis

    val t = ferrycore2algebra _
    from match {
      case _:ferry.Literal[_] => Constant( from.asInstanceOf[ferry.Literal[_]].pvalue )
      case ferry.TableReference(name,columns,keys,order) => {
        val column_positional_names = columns.map(_._1)
                                             .zipWithIndex.map( name_index => ( name_index._1, position2column(name_index._2)) )
                                             .toMap
/*        NestedRelation(
          ListResult
          ,column_positional_names.map(_._2)
          ,[]
          ,*/
          RowNumber(
            Projection(
              Table( columns.map(_._1), name, keys ),
              column_positional_names.map(_._2).toList
            ),
            "pos",
            order.map( column_positional_names )
          )
//        )
      }
/*
          case _:Comprehension[_] => {
            val comprehension = def_.asInstanceOf[Comprehension[_]]
            For(
              'i'+comprehension.key,
              t( comprehension.tables(0) ),
              t( comprehension.element )
            )
          }
          case _:FieldReference => {
            val ref = def_.asInstanceOf[FieldReference]
            PositionalAccess(
              Variable( "i"+{
                val it = ref.asInstanceOf[FieldReference].iterable
                rep2def(it) match {
                  case _:Generator[_] => rep2def(it).asInstanceOf[Generator[_]].key
                  case _ => t(it)//"<"+rep2def(it).toString+">"
                }
              })
              , ref.position
            )


          }
          /*
          case _:[_] => {
            val  = def_.asInstanceOf[[_]]

          }
          */
        }
      }
 */
    }
  }
}