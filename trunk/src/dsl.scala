package siq
object dsl{
  val dsl_old: IFerry2Result with ISchema = new Ferry2Result with Schema{}
  val dsl : IScalaIntegratedQuery = new ScalaIntegratedQuery
}
trait IScalaIntegratedQuery extends ISchema with IModules{
  protected def query[T]( r:Rep[T], debug : Boolean )( implicit manifest: Manifest[T] ) : Any
  case class Shipable[T]( r:Iterable[T] ){
    def todb = liftIterable( r )
  }
  def todb[T] = liftIterable[T] _
  implicit def ship_enable[T]( r:Iterable[T] ) = Shipable(r)
  case class Queryable[T]( r:Rep[T] )( m: Manifest[T] ){
    def fromdb(debug:Boolean = false) = query(r,debug)(m)
  }
  implicit def query_enable[T]( r:Rep[T] )( implicit m: Manifest[T] ) = Queryable(r)(m)
  def fromdb[T]( r:Rep[T], debug:Boolean=false )( implicit m: Manifest[T] ) = Queryable(r)(m).fromdb(debug)
}
class ScalaIntegratedQuery extends IScalaIntegratedQuery
    with Schema
    with SIQ2FerryCore
    with FerryCore2Algebra
    with Algebra2SQL
    with SQL2RelationalData
    with RelationalData2FerryData
    with FerryData2Scala
    /* with Results*/{
  var debug : Boolean = false
  protected def query[T]( r:Rep[T], debug:Boolean = false )( implicit manifest: Manifest[T] ) : T = {
    this.debug = debug
    if(debug){
      println("-"*80)
      println("SIQ:")
      println(r)
    }
    val ferrycore = siq2ferrycore( r )
    if(debug){
      println("-"*80)
      println("Ferry Core:")
      println(ferrycore)
    }
    val algebra   = ferrycore2algebra( ferrycore )
    if(debug){
      println("-"*80)
      println("Relational Algebra:")
      println(algebra)
    }
    val sql       = algebra2sql( algebra )
    if(debug){
      println("-"*80)
      println("SQL:")
      println(sql)
    }
    val relational_data = sql2relationaldata( sql )
    if(debug){
      println("-"*80)
      println("Result (via postgres jdbc):")
      println("")
      println(relational_data)
    }
    val ferry_data = relationaldata2ferrydata( relational_data, ferrycore.type_ )
    if(debug){
      println("-"*80)
      println("Ferry Result:")
      println("")
      println(ferry_data)
    }
    val data = ferrydata2scala[T]( ferry_data )(manifest)
    if(debug){
      println("-"*80)
      println("Scala Result:")
      println("")
      println(data)
      println("-"*80)
    }
    data
/*    val result_data = result.map(_.drop(2))
    val data = if( algebra.relation.type_.asInstanceOf[ferry.FerryCoreTypes.list].element == ferry.FerryCoreTypes.atomic ) result_data.flatten else result_data
    extract( new Result( data ) )*/
  }
}
