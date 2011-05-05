package siq
trait RelationalAlgebra{
  object algebra{
    // helper functions
    abstract class freshName{
      var counter : Int = 0
      protected def next = {counter+=1;counter}
    }
    object freshTableName extends freshName{
      def apply() = 't'+next.toString
    }
    // the AST only implements a pragmatic subset of the real requirements to ease debugging during development
    def require_schemata_disjoint(left:List[String],right:List[String]) = require(
      List() == (left intersect right)
      ,left.toString + " disjoint " + right.toString
    )

/*    def require_schema_contains_all_columns(relation:Relation,columns:List[String]) = require(
      columns.forall( relation.schema.contains(_) )
      , relation.schema.toString + " contains all "+ columns.toString
    )
*/

/*    /**
     * Tabelleninformationsknoten (q, cols, itbls) as called in Tom's Thesis
     * not part of relational algebra, but in here for pragmatic reasons
     */
    case class NestedRelations(
      result_type : ResultType,
      inner_relations : Map[String,NestedRelation],
      relation : Relation
    ) extends Node*/

     // AST
    trait Node
    abstract class Relation(
      // unqualified column names
      val schema : List[String],
      val name : String = freshTableName()
    ) extends Node{
      val qualified_columns = schema.map( name+"."+_ )
    }
    trait UnaryRelationOperator{
      val relation:Relation
    }
    trait BinaryRelationOperator{
      val left:Relation
      val right:Relation
    }
    trait Expression extends Node

    abstract class ResultType
    object TupleResult extends ResultType
    object ListResult extends ResultType


    case class Constant[T](
      value : T
    ) extends Expression

    case class Projection( // including rename
      relation : Relation,
      new_column_names : List[String]
    ) extends Relation( new_column_names ) with UnaryRelationOperator{
      require( relation.schema.size == new_column_names.size )
    }

    case class Filter(
      relation : Relation,
      predicate : Expression
    ) extends Relation( relation.schema ) with UnaryRelationOperator

    case class CartesianProduct(
      left : Relation,
      right : Relation
    ) extends Relation( left.schema ::: right.schema ) with BinaryRelationOperator{
      require_schemata_disjoint( left.schema, right.schema )
    }

    case class Join(
      left : Relation,
      right : Relation,
      predicate : Expression
    ) extends Relation( left.schema ::: right.schema ) with BinaryRelationOperator{
      require_schemata_disjoint( left.schema, right.schema )
    }

    case class DisjointUnion(
      left : Relation,
      right : Relation
    ) extends Relation (left.schema) with BinaryRelationOperator{
      require( left.schema == right.schema )
    }

    case class Difference(
      left : Relation,
      right : Relation
    ) extends Relation (left.schema) with BinaryRelationOperator{
      require( left.schema == right.schema )
    }

    case class Distinct(
      relation : Relation
    ) extends Relation( relation.schema ) with UnaryRelationOperator

    case class Attach(
      relation : Relation,
      column_name : String,
      value : Constant[_]
    ) extends Relation( column_name :: relation.schema ) with UnaryRelationOperator{
      require( !relation.schema.contains(column_name) )
    }

    case class RowRank(
      relation : Relation,
      column_name : String
    ) extends Relation( column_name :: relation.schema ) with UnaryRelationOperator{
      require( !relation.schema.contains(column_name) )
    }

    case class RowNumber(
      relation : Relation,
      column_name : String,
      partinionByColumns : List[String],
      orderByColumns : Option[List[String]] = None
    ) extends Relation( column_name +: relation.schema ) with UnaryRelationOperator{
      require( !relation.schema.contains(column_name) )
    }

    case class Operator(
      left : Expression,
      right : Expression
    ) extends Expression

/*    case class Aggregation(
      relation : Relation
    ) extends Relation*/

    case class Table(
      schema_ : List[String],
      name_ : String,
      keys     : List[List[String]]
    ) extends Relation(schema_,name_)

/*    case class LiteralTable(

    ) extends Relation
*/
 }
}