package siq
trait Algebra2SQL extends RelationalAlgebra{
  import algebra._
//  val t = algebra2sql _
  def algebra2sql( from:Nested ) : String = {
    def flatten( relation:Relation ) : List[Relation] = (relation match{
        case bin:BinaryRelationOperator => relation :: flatten(bin.left) ::: flatten(bin.right)
        case un:UnaryRelationOperator => relation :: flatten(un.relation)
        case _:LiteralTable[_] => List(relation)
        case _:Table => List()
      })

    val relations = flatten(from.relation)

    // put together complete sql query from component queries
    "WITH\n\n"+
    // distinct is needed to kill multiple occurence of LOOP relation
    relations.reverse.distinct.map( relation => "-- " + relation.getOperatorName + "\n" +
    "%s(%s) AS\n(%s)".format(
      relation.name
      ,relation.schema.mkString(",")
      ,relation2sql(relation)
    )).mkString(",\n\n") +
    "\n\n"+
    "SELECT * FROM "+from.relation.name
//    throw new Exception(from.toString)
  }
  def expression2sql( from:Expression ) : String = {
    def t = expression2sql _
    from match {
      case Variable( name ) => name
      case Operator( symbol, left, right ) => t(left) + " " + symbol + " " + t(right)
    }
  }

  def relation2sql( from: Relation ) : String = {
    def escape( value: Any ) : String = value match {
      case i:Int => i.toString
      case s:String   => '"' + s.replace("\\","\\\\").replace("\"","\\\"") + '"'
      case s:Char   => escape (s.toString)
      case b:Boolean => (if(b) 1 else 0).toString
    }
    (from match {
      case p@Projection( _, relation ) => "SELECT %s FROM %s".format(
        ( relation.qualify_columns(p.renames.keys), p.renames.values ).zipped.map{
            case( from, to ) => from + " AS " + to
        }.mkString(", ")
        , relation.name
      )
      case CartesianProduct( left, right ) => "SELECT %s FROM %s".format(
        (left.qualified_schema ++ right.qualified_schema).mkString(","),
        left.name + "," + right.name
      )
      case Join( predicate, left, right ) => "SELECT * FROM " + left.name +","+ right.name + " WHERE " + expression2sql( predicate )
      case DisjointUnion( left, right ) => "SELECT * FROM " + left.name + " UNION ALL SELECT * FROM " + right.name
      case Attach( value, as, relation ) => "SELECT %s FROM %s".format(
        escape(value) +" AS "+ as + "," + relation.qualified_schema.mkString(",")
        , relation.name
      )
      case RowRank( as, orderBy, relation ) => // TODO: check this, not sure if right in Kaichaun Wen's Thesis
        "SELECT DENSE_RANK() OVER (ORDER BY %s) AS %s, %s FROM %s".format(
          orderBy.mkString(","),
          as,
          relation.qualify_columns(relation.schema).mkString(","),
          relation.name
        )
      case RowNumber( as, orderBy, relation, partitionBy ) => // TODO: check this, not sure if right in Kaichaun Wen's Thesis
        "SELECT ROW_NUMBER() OVER (%sORDER BY %s) AS %s, %s FROM %s".format(
          (partitionBy match {
            case Some(x) => "PARTITION BY "+x.mkString(",")+" "
            case _ => ""
          }),
          orderBy.mkString(","),
          as,
          relation.qualify_columns(relation.schema).mkString(","),
          relation.name
        )
      case LiteralTable( data, schema ) => "VALUES %s".format( data.map( x => x match {
        case p:Product => "(%)".format( p.productIterator.mkString(",") )
        case _ => "("+x+")"
      }).mkString(", "))
    })
  }
}