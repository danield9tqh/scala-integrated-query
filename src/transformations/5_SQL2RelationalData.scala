package siq
trait SQL2RelationalData{
  var debug : Boolean
  // Import JDBC package from standard Java SDK
  import java.sql.{Connection, DriverManager, ResultSet}
  // Load the driver
  Class.forName(classOf[org.postgresql.Driver].getName).newInstance
  def sql2relationaldata( sql : String ) : List[List[Any]] = { // FIXME: return type
    val connection = DriverManager getConnection "jdbc:postgresql://localhost/siq?user=siq&password=siq&characterEncoding=UTF8"
    var results = List[List[Any]]()
    try{
      val statement = connection createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
      val rs = statement executeQuery sql
      while (rs next) {
        var result = List[Any]()
        try{
          for( i<-Range(1,999) ){
            result = rs.getString(i) :: result
          }
        } catch{ case _ => () }
        results = result.reverse :: results
      }
      statement close
    } catch {
      case e:org.postgresql.util.PSQLException => throw new Exception( (if(!this.debug) sql + "\n" else "") + e.getMessage)
    } finally { connection close }
    results.reverse
  }
}
