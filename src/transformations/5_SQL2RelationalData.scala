package siq
trait SQL2RelationalData extends Algebra2SQL{
  var debug : Boolean
  // Import JDBC package from standard Java SDK
  import java.sql.{Connection, DriverManager, ResultSet}
  // Load the driver
  Class.forName(classOf[org.postgresql.Driver].getName).newInstance
  case class NestedRelationalData( data:List[RelationalDataRow], columns:List[String], nested:Map[String,NestedRelationalData] )
  case class RelationalDataRow( iter : Int, pos: Int, values:List[Any] )
  def sql2relationaldata( sql : NestedSQL ) : NestedRelationalData = { // FIXME: return type
    val connection = DriverManager getConnection "jdbc:postgresql://localhost/siq?user=siq&password=siq&characterEncoding=UTF8"
    var results = List[RelationalDataRow]()
    try{
      val statement = connection createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
      val rs = statement executeQuery sql.sql
      while (rs next) {
        results =  RelationalDataRow(
          rs.getInt(1),
          rs.getInt(2),
          {
            var result = List[Any]()
            try{
              for( i<-Range(3,999) ){
                result = rs.getString(i) :: result
              }
            } catch{ case _ => () }
            result.reverse
          }
        ) :: results
      }
      statement close
    } catch {
      case e:org.postgresql.util.PSQLException => throw new Exception( (if(!this.debug) sql + "\n" else "") + e.getMessage)
    } finally { connection close }
    NestedRelationalData( results.reverse, sql.data_columns, sql.nested.keys zip sql.nested.values.map(sql2relationaldata _) toMap )
  }
}
