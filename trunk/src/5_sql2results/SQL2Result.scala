package siq
trait SQL2Result{
  // Import JDBC package from standard Java SDK
  import java.sql.{Connection, DriverManager, ResultSet}
  // Load the driver
  Class.forName(classOf[org.postgresql.Driver].getName).newInstance
  def sql2result[T]( sql : String )(implicit manifest: scala.reflect.Manifest[T]) : Any = { // FIXME: return type
    val connection = DriverManager getConnection "jdbc:postgresql://localhost/siq?user=siq&password=siq&characterEncoding=UTF8"
    def getValue( rs:ResultSet, index: Int, column_type : String ) = column_type match {
      case "java.lang.String" => rs getString index
      case "Int" => rs getInt index
      case _ => throw new Exception("unexpected column type: "+column_type)
    }
    
    var results = List[List[Any]]()
    try{
      // Create statement for readonly
      val statement = connection createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
      val rs = statement executeQuery sql

      // Looping resultset
      while (rs next) {
/*
        val result = List[Any]()
        if( false && manifest.typeArguments.length > 0 ){
          try{
            for( i<-Range(1,99) ){
              result = result :+ getValue(rs, i, manifest.typeArguments(i-1).typeArguments(0).toString)
            }
          } catch{ case _ => () }
        } else {
          result = result :+ getValue(rs, 1, manifest.toString )
        }*/
        results = results :+ List( rs.getInt(1), rs.getString(2), rs.getString(3) )
      }
      statement close
    } finally { connection close }
    results
  }
}
