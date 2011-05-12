package siq

trait IComprehensions extends IModuleBase with ITuples { //FIXME: s/BaseExp/Base/
  trait Order
  // methods for iterables of any type
  implicit def rep2igenerator[T]( r: Rep[Iterable[T]] ) : IGenerator[T]
  trait IGenerator[+T]{
    def orderBy( orders : (Rep[T] => Order)* ) : Rep[Iterable[T]]
    def map[R]( f : Rep[T] => Rep[R] ) : Rep[Iterable[R]]
    def flatMap[R]( f : Rep[T] => Rep[Iterable[R]] ) : Rep[Iterable[R]]
    def withFilter( f : Rep[T] => Rep[Boolean] ) : Rep[Iterable[T]]
  }

  abstract class IOrderable{
    def asc : Order
    def desc : Order
  }
  implicit def order_enable( r:Rep[Any] ) : IOrderable
  implicit def defaultOrder( r:Rep[Any] ) : Order
  // future? implicit def defaultOrder[T <% Rep[Any]]( r:T ) : Order

  // transfer scala iterable into literal db table
//  def todb[T <% Rep[_ >: T]]( i:Iterable[T] ) : Rep[Iterable[T]]
  //implicit def liftIterableOfReps[T]( i:Iterable[Rep[T]] ) : Rep[Iterable[T]]
  implicit def liftIterable[T/* <% Rep[T]*/]( i:Iterable[T] ) : Rep[Iterable[T]]
  //implicit def liftOther[T,I <: Iterable[T]]( i:I ) : Rep[I] = liftIterable(i).asInstanceOf[Rep[I]]

  // base stuff for db schema
  trait SchemaBase extends Product
  /*trait Table[+T <: Product] extends IGenerator[T]{
    val name   : String
  }*/

  // operations on comprehensions
  def infix_count( r:Rep[Iterable[_]] ) : Rep[Int]
  def infix_sum( r:Rep[Iterable[Int]] ) : Rep[Int]
  def infix_the[T]( r:Rep[Iterable[T]] ) : Rep[T]
  def infix_flatten[T]( r:Rep[Iterable[Iterable[T]]] ) : Rep[Iterable[T]]
}

trait Comprehensions extends IComprehensions with ModuleBase with Tuples{
  implicit def rep2igenerator[T]( r: Rep[Iterable[T]] ) = (rep2def(r) match {
    case _:FieldReference => rep2def(rep2def(r).asInstanceOf[FieldReference].referree.asInstanceOf[Rep[Iterable[T]]])
    case _ => rep2def(r)
  }).asInstanceOf[IGenerator[T]]
  protected var _query_counter = 0
  trait Generator[+T] extends Def[Iterable[T]] with IGenerator[T]{
    val element : Rep[T]
    val key = {_query_counter = _query_counter + 1;_query_counter}.toString
    def orderBy( orders : (Rep[T] => Order)* ) = {
      new Comprehension[T](
        List[Rep[Iterable[_]]](this)//replace_with_references(toAtom(this),this),
        , element = replace_with_references(element,this)
        , orderBy = orders.toList.map( _(replace_with_references(element,this)) )
      )
    }
    def map[S]( f : Rep[T] => Rep[S] ) = {
      new Comprehension[S](
        List[Rep[Iterable[_]]](this)//replace_with_references(toAtom(this),this),
        , element = f( replace_with_references(element,this) )
      )
    }
    def flatMap[S]( f : Rep[T] => Rep[Iterable[S]] ) = {
      this.map( f ).flatten
    }
    def withFilter( f : Rep[T] => Rep[Boolean] ) = {
      new Comprehension[T](
        List[Rep[Iterable[_]]](this)
        , element = replace_with_references(element,this)
        , filter = Some(f( replace_with_references(element,this) ))
      )
    }
  }
  case class Ascending(r:Rep[Any]) extends Order
  case class Descending(r:Rep[Any]) extends Order

  class Orderable( r:Rep[Any] ) extends IOrderable{
    def asc : Order = Ascending(r)
    def desc : Order = Descending(r)
  }
  implicit def defaultOrder( r:Rep[Any] ) : Order = Ascending(r:Rep[Any])
  //future? implicit def defaultOrder[T <% Rep[Any]]( r:T ) : Order = Ascending(r:Rep[Any])
  implicit def order_enable( r:Rep[Any] ) : Orderable = new Orderable(r)

  // as convenience for internal use
  def rep2generator[T]( r: Rep[Iterable[T]] ) : Generator[T] = rep2igenerator(r).asInstanceOf[Generator[T]]

  // Node to represent a comprehensions
  case class Comprehension[R] (
    var tables : List[Rep[Iterable[_]]],
    val element : Rep[R],
    val filter : Option[Rep[Boolean]] = None,
    val orderBy : List[Order] = List(),
    val groupBy : Option[Rep[_]] = None
  ) extends Generator[R]


  //implicit def liftIterableOfReps[T](i:Iterable[Rep[T]]) =
  implicit def liftIterable[T/* <% Rep[T]*/]( i:Iterable[T] ) = toAtom(LiteralTable[T](
    i
  )) // FIXME: restrict to only list of values and list of tuples of values with viewbounds
//  case class Reference[T]( generator:Generator[_] ) extends Def[T]
  case class LiteralTable[T/* <% Rep[T]*/]( i: Iterable[T] ) extends Generator[T]{
    val values = i.toList
    val element = replace_with_references[T]((
      // FIXME: this is very VERY hacky
      if(values.size > 0 && values(0).isInstanceOf[Product]) toAtom(
        new LiftedTuple(new Product{
          val list = values(0).asInstanceOf[Product].productIterator.map(x => toAtom(new Def[T]{})).toList
          def productElement(n: Int) = list(n)
          def productArity = list.size
          def canEqual( that:Any ) = false
        })
      ) else new Def[T]{}
    ),this)
    def unapply( a:Any ) = Some(Tuple1(values))
  }

  // base stuff for db schema
  abstract class TableExp[+T <: Product](
   val name   : String
  ) extends Generator[T]/* with Table[T]*/{
    val columns : Array[Column[_]]
    val keys : Array[Column[_]]
  }
  case class Column[T](
    name : String = null,
    type_ : String = null
  ) extends Def[T]
  case class LiftedSchema[ T<:SchemaBase, P<:Product ]( schema:T ) extends LiftedTuple[P](schema)

  // base stuff for tuple arity dependent operations (in trait Tuples)
  trait Zip{
    val target:Rep[Any]
  }
  case class Zipped[T](
    field : Rep[Iterable[T]]
  ) extends Def[T]
  case class Grouped[T]( // FIXME: get rid of the column attr
    column : Rep[T]
  ) extends Generator[T]{
    val element = column
  }

  // base stuff for Tuples.replace_with_references
  class Counter( var count : Int )
  case class FieldReference(
      iterable : Rep[Iterable[_]],
      position : Int,
      referree : Rep[_]
    ) extends Def[Any]
  class GeneratorReference[T](
    val reference : FieldReference,
    counter : Counter
  ) extends Comprehension[T] (
    List[Rep[Iterable[_]]](),
    element = {
      val generator = rep2generator(reference.referree.asInstanceOf[Rep[Iterable[T]]])
      val outer_generator = reference.iterable
      replace_with_references(generator.element,outer_generator,counter)
    }
  )

  case class Count( iterable:Rep[Iterable[_]] ) extends Def[Int]
  def infix_count( r:Rep[Iterable[_]] ) = Count(r)
  case class Sum( iterable:Rep[Iterable[Int]] ) extends Def[Int]
  def infix_sum( r:Rep[Iterable[Int]] ) = Sum(r)
  case class The[T]( iterable:Rep[Iterable[T]] ) extends Def[T]
  def infix_the[T]( r:Rep[Iterable[T]] ) = The[T](r)
  case class Flatten[T]( iterable:Rep[Iterable[Iterable[T]]] ) extends Generator[T]{
    val element = replace_with_references( rep2generator(rep2generator(iterable).element).element, this )
  }
  def infix_flatten[T]( r:Rep[Iterable[Iterable[T]]] ) = Flatten[T](r)
}
