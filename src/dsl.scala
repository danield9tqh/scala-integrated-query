package siq
object dsl{
  val dsl_old: IFerry2Result with ISchema = new Ferry2Result with Schema{}
  val dsl : IScalaIntegratedQuery = new ScalaIntegratedQuery
}
trait IScalaIntegratedQuery extends ISchema with IModules{
  protected def query[T]( r:Rep[T], debug : Boolean )( implicit manifest: Manifest[T] ) : Any
 /* case class Shipable( r:Iterable[_] ){
    def todb = ship( r )
  }
  implicit def ship_enable( r:Iterable[_] ) = Shipable(r)
*/
  case class Queryable[T]( r:Rep[T] )( m: Manifest[T] ){
    def fromdb(debug:Boolean = false) = query(r,debug)(m)
  }
  implicit def query_enable[T]( r:Rep[T] )( implicit m: Manifest[T] ) = Queryable(r)(m)
  def fromdb[T]( r:Rep[T], debug:Boolean=false )( implicit m: Manifest[T] ) = Queryable(r)(m).fromdb(debug)

}
class ScalaIntegratedQuery extends IScalaIntegratedQuery with Schema with SIQ2FerryCore with FerryCore2Algebra with Algebra2SQL with SQL2Result{
  protected def query[T]( r:Rep[T], debug:Boolean = false )( implicit manifest: Manifest[T] ) = {
    val ferrycore = siq2ferrycore( r )
    val algebra   = ferrycore2algebra( ferrycore )
    val sql       = algebra2sql( algebra )

    if(debug){
      println("-"*80)
      println("SIQ:")
      println(rep2def(r))
      println("-"*80)
      println("Ferry Core:")
      println(ferrycore)
      println("-"*80)
      println("Relational Algebra:")
      println(algebra)
      println("-"*80)
      println("SQL:")
      println(sql)
    }

    val result = sql2result( sql )(manifest)

    if(debug){
      println("-"*80)
      println("Result (via postgres jdbc):")
      println(result)
    }

    result
  }
}
