package siq
trait SQL2RelationalData extends Algebra2SQL{
  var debug : Boolean
  // Import JDBC package from standard Java SDK
  import java.sql.{Connection, DriverManager, ResultSet}
  // Load the driver
  Class.forName(classOf[org.postgresql.Driver].getName).newInstance
  case class NestedRelationalData( data:List[List[String]], columns:List[String], nested:List[(String,NestedRelationalData)] )
  def sql2relationaldata( sql : NestedSQL ) : NestedRelationalData = { // FIXME: return type
    val connection = DriverManager getConnection "jdbc:postgresql://localhost/siq?user=siq&password=siq&characterEncoding=UTF8"
    var results = List[List[String]]()
    try{
      val statement = connection createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
      val rs = statement executeQuery sql.sql
      while (rs next) {
        results = {
          var result = List[String]()
          try{
            for( i<-Range(1,999) ){
              result = rs.getString(i) :: result
            }
          } catch{ case _ => () }
          result.reverse
        } :: results
      }
      statement close
    } catch {
      case e:org.postgresql.util.PSQLException => throw new Exception( (if(true || !this.debug) sql + "\n" else "") + e.getMessage)
    } finally { connection close }
    NestedRelationalData( results.reverse, sql.columns, sql.nested.map(_._1) zip sql.nested.map(_._2).map(sql2relationaldata _) )
  }
}
