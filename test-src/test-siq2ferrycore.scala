package siq
import Predef.{any2stringadd => _, _} // prohibit automatic toString conversion of objects when + method is used
object test_siq2ferrycore {
  def mai_n( args:Array[String] ) {
    return();
    {
      println("-"*80)
      println("Ferry Core:")
      val q = dsl.dsl.asInstanceOf[ScalaIntegratedQuery]
      import q._
      import q.tables._
      import q.implicits._
      val s2f = siq2ferrycore _
      val f_const    = s2f( Const(5) )
      val f_employee =  s2f( employee )
      val f_simple_comprehension = s2f( for( e <- employee ) yield e.name )
      println( f_const )
      println( f_employee )
      println( f_simple_comprehension )

      println("-"*80)
      println("Relational Algebra:")
      val f2a = ferrycore2algebra _
      val a_const = f2a( f_const )
      val a_employee =  f2a( f_employee )

      println( a_const )
      println( a_employee )

      println("-"*80)
      println("SQL:")
      val a2s = algebra2sql _
      println( a2s(a_const) )
      println( a2s(a_employee) )
    }
  }
}