package siq
trait FerryCore{
  object ferry{

    trait Expression

    abstract class Literal[T]( val pvalue : T ) extends Expression

    case class BoolLiteral  ( value : Boolean ) extends Literal[Boolean](value)
    case class StringLiteral( value : String )  extends Literal[String] (value)
    case class IntLiteral   ( value : Int )     extends Literal[Int]    (value)
    case class DoubleLiteral   ( value : Double )  extends Literal[Double] (value)

    case class OperatorApplication(
      operator : String,
      lhs : Expression,
      rhs : Expression
    ) extends Expression

    case class Tuple() extends Expression

    case class PositionalAccess(
      item : Expression,
      position: Int
    ) extends Expression

    case class FerryList[T](
      items : Option[T] // FerryCore lists have one or zero elements. Really, no kiddin.
    ) extends Expression

    case class TableReference(
      name     : String,
      columns  : List[(String,String)],
      keys     : List[List[String]],
      order    : List[String]
    ) extends Expression

    case class Let(
      variable : String,
      value : Expression
    ) extends Expression

    case class Variable(
      name : String
    ) extends Expression

    case class If(
      if_ : Expression,
      then_ : Expression,
      else_ : Expression
    ) extends Expression

    case class For(
      variable : String,
      in : Expression,
      returned: Expression,
      orderBy : Option[Expression] = None
    ) extends Expression

    case class FunctionApplication() extends Expression
  }
}