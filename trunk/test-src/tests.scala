package siq
import Predef.{any2stringadd => _, _} // prohibit automatic toString conversion of objects when + method is used
object tests {
  import dsl.dsl._
  import dsl.dsl.tables._
  import dsl.dsl.implicits._
  def main( args:Array[String] ) {
    {
      val q = for( e <- employee; w <- workgroup;if e.workgroup_id == w.id ) yield (e.name,w.name)
      q.fromdb.foreach( println(_) )
    }
    {
      val q = for( e <- employee; w <- workgroup ) yield (e,w)
      q.fromdb.foreach( println(_) )
    }
    {
      val q = for( e <- employee; w <- workgroup ) yield (e.id,w)
      q.fromdb.foreach( println(_) )
    }

    {
      val q = for( c <- CUSTOMER.orderBy(_.C_NATIONKEY).groupBy(_.C_NATIONKEY) ) yield {
        val country_orders =  for( o <- ORDERS; x <- zip(c.C_CUSTKEY, c.C_MKTSEGMENT); if o.O_CUSTKEY == x._1 ) yield (x._2,o.O_TOTALPRICE)
        val total_price = country_orders.map(_._2).sum
        (
          c.C_NATIONKEY.the,
          c.C_NATIONKEY.count,
          total_price
        )
      }
      q.fromdb.foreach( println(_) )
    }
/*
    {
      val q = for( c <- CUSTOMER.orderBy(_.C_NATIONKEY).groupBy(_.C_NATIONKEY) ) yield (
        c.C_NATIONKEY.the,
        c.C_NATIONKEY.count,
        zip( c.C_CUSTKEY, c.C_MKTSEGMENT ).groupBy(_._2).map( x => (
          x._2.the,
          x._1,
          for( o <- ORDERS; custkey <- x._1 if o.O_CUSTKEY == custkey ) yield (custkey,o.O_TOTALPRICE)
        ))  //         (for( x <- c.C_MKTSEGMENT) yield (x,x.(x.count*100)/c._1.count) )
      )
      q.fromdb.foreach( println(_) )
    }
*/

    /*
    printDsl( (7.0:Rep[Double]) + 7.0 )
    printDsl( dsl.unit(7.0) + 7.0 )
    printDsl( dsl.unit(7.0) == "Chris" )
    printDsl( (dsl.unit(7.0):Rep[Double]) + (7:Rep[Double]) == dsl.unit(9) + "Chris" )
    printDsl( for( e <- employee ) yield "test" )
    printDsl( for( e <- employee ) yield e.name )
    printDsl( for( e <- employee; f <- employee ; if e.name == f.name && e.id <> f.id ) yield e.id + " has same name as " + f.id  )
    printDsl( for( e <- employee; w <- workgroup ; if e.workgroup_id == w.id ) yield e.name + " is in group " + w.name )
    printDsl( 7 + 7 == (9:Rep[Double]) )

    printDsl( for( e <- employee if e.name == "test" ) yield "test" )
    printDsl( for( (e:EmployeeSchemaBase) <- employee if e.name == "test" orderBy e.name + e.id ) yield "test" )
    */
/*
    // TODO:
    val e = employee
    val e2 = e.map( b => (b,b._2) )
    val e3 = e2.map( e => (e._1,e._2,e) )
    val e4 = e3.map( e => (e._1,e._1._3,e._2,e) )
    print( query(
        e4
    ).foreach(print(_)) )
*/

/*    {
      val names = for( p <- employee ) yield (p._1,p.name)
      println( (for(x <- (for( e <- (for( e <- employee ) yield e) ) yield e)) yield x.name) exec )
      val gamounts = ((for( a <- rep2amountsgroupable(amounts).groupBy(_.country) ) yield a) exec)
      println( gamounts )
      println( gamounts.map(_.country) )
    }
*/

    {
      // TPC-H Query6
      val date = "1993-01-01" // DATE is the first of January of a randomly selected year within [1993 .. 1997];
      val date_plus = "1994-01-01" // date ':1' + interval '1' year
      val discount = 2// 2. DISCOUNT is randomly selected within [0.02 .. 0.09];
      val quantity = 24 // 3. QUANTITY is randomly selected within [24 .. 25].
      val q = LINEITEM.withFilter( l =>
        l.L_SHIPDATE > date // FIXME >=
        && l.L_SHIPDATE < date_plus
        && (l.L_DISCOUNT*100) > discount - 1
        && (l.L_DISCOUNT*100) < discount + 1
        && l.L_QUANTITY < quantity
      ).map( l => l.L_EXTENDEDPRICE * 100 * l.L_DISCOUNT * 100 ).sum
      println( q fromdb )
    }
    {
      // REGRESSION TEST CASE:
      println( employee.groupBy( _.workgroup_id ).map(x => x.name).orderBy( x => x ) fromdb )
    }
    // TEST CASE (
    {
      val query = for( e <- employee.groupBy( e => e.workgroup_id ) ) yield e.name.orderBy(x => x)
      query.fromdb.foreach( x => println(x) )
    }

    /*{
      val q = for( c <- CUSTOMER.groupBy(_.C_NATIONKEY).orderBy( _.C_NATIONKEY.the, descending ) )
        yield (i, c.C_NATIONKEY.the, c.C_NATIONKEY.count)
      println( q fromdb );
    }*/


    /* // SURPRISING RESULT:
    {
      val last = for( p <- employee; if p._1 == (for(e <- employee.groupBy(_._1)) yield e._1.count) ) yield p
      println( query(last) : Iterable[_] )
    }
    */
    {
      println( query(for( p <- employee; if p._1 == 3 ) yield p) : Iterable[_] )
      println( query( amounts ) : Iterable[_] )
      println( query( ship(List((1,"test"),(2,"aloha"))) ) : Iterable[_] )
    }
    {
      println( query( for( a <- amounts; e<-employee ) yield(a,e) ) : Iterable[_] )
    }
    {
      val q = amounts.orderBy( e => (e._1,e._2,e._3), ascending )
      println(query( q ):Product)
    }
    {
      val q = amounts.orderBy( _._2, ascending )
      println(query( q ):Product)
    }
    {
      val q = amounts.orderBy( _._2, descending )
      println(query( q ):Product)
    }
    {
      val q = amounts.groupBy( _._1 )
      println(query( q ):Product)
    }
    {
      val q = amounts.groupBy( _.country )
      println( q exec )
    }
    {
      val q = workgroup.groupBy( _.id )
      println( q exec )
    }
    {
      val q = for ( a <- amounts ) yield (a.country,a.branch)
      println( q.groupBy(x => "test").map(x => (x._1.the,x._2.the)) exec )
    }
    {
      val q2 = amounts.count
      println(query( q2 ):Int)
      val q3 = for( a <- amounts ) yield (amounts.count, a._2)
      println(query( q3 ):Product)
      val q4 = (for( a <- amounts.groupBy( (a:(Rep[_],Rep[_],Rep[_])) => a._1 ) ) yield
            ( a._1.the,a._3.sum, for( b <- zip(a) ) yield (b._2,b._3,b._3*200/a._3.sum) )).count
      val result = query(q4)
    }
    {
      Range(1,10).foreach{x=>{
        val startTime = System.currentTimeMillis()
        val q = (for( a <- amounts.groupBy( (a:(Rep[_],Rep[_],Rep[_])) => a._1 ) ) yield
              ( a._1.the,a._3.sum, for( b <- zip(a) ) yield (b._2,b._3,b._3*200/a._3.sum) )).count
        println(System.currentTimeMillis()-startTime)
        val startTime2 = System.currentTimeMillis()
        val result = query(q)
        println(System.currentTimeMillis()-startTime2)
        val startTime3 = System.currentTimeMillis()
        result : Int
        println(System.currentTimeMillis()-startTime3)
          println("---")
      }}
    }
/*
    {
      // does not work, due to missing implementation of filter
      for(
        (country,sector,amount) <- amounts.groupBy( a => a._1 )
      ) yield
            ( country.the, amount.sum, (for(b <- zip(sector,amount)) yield (b._1,b._2,b._2*100/amount.sum) ) )
    }
*/

    query(
        for( a <- amounts.groupBy( (a:(Rep[_],Rep[_],Rep[_])) => a._1 ) ) yield
          ( a._1.the,a._3.sum, for( b <- a._2 ) yield b )
    ).map( x =>println(x) )

    query(
        employee.groupBy( (e:(Rep[_],Rep[_],Rep[_])) => e._3 ).map( e => e._2 )
    ).map( x =>println(x) )

    query(
        employee.groupBy( (e:(Rep[_],Rep[_],Rep[_])) => e._3 ).map( e => e._2 )
    ).map( x =>println(x) )

    query(
        for( w <- workgroup ) yield ( w.name, for(e <- employee /*if e.workgroup_id == w.id*/) yield e )
    ).map( x => println(x) )

    query(
        for( i <- ship(List(1,2)) ) yield ( i, for(e <- employee) yield (i,e.name) )
    ).map( x => println(x) )
    query(
      for( x <-
          for( i <- ship(List(1,2)); e <- employee/*; if e.id == i*/ ) yield ( i, e )
      ) yield x._2.name
    ).map( x => println(x) )

    query(
        employee.map( e => e.name )
    ).map( x =>println(x) )

    query(
      for( w <- workgroup ) yield w
    ).map( x =>println(x) )

    query(
      for( e <- employee ) yield e
    ).map( e =>println(e) )

    query(
      for( x <- (
        for( e <- employee; i <- ship(List(1,2,3,4)) ) yield T( T(i,e._2,T(i,e._2)), e._2,T(T(i,e._2),e._2) )
      )) yield (x, unitAnyVal(5))
    ).map( x =>println(x) )

    query(
      for( x <- (
        for( e <- employee ) yield ( e._1, e )
      )) yield x._2._2
    ).map( x =>println(x) )


    val names_ids = for(
      x <- ship(List(1,2,3,4));
      e <- employee
    ) yield ( x, e.id, e.id, e.name )

    query(names_ids).map( x => println(x) )

    def print_names[T](t:T)( implicit manifest:Manifest[T] ){
      print(manifest)
    }
    //print_names(names_ids)

    val res = query(
//        names_ids.map( (x:Query[_]) => Tuple1(5:Rep[Int],6) )
//     names_ids.map( x => (x._2,x._1) )
//        names_ids.map( x => {print(x.toString); (5:Rep[Int])} )//Tuple1(x._1) )
//        for( x <- names_ids ) yield (5:Rep[Int],6,x(1))
      for( x <- names_ids ) yield (x._2,x._1)
    )
    res.foreach( x => {/*print_manifest(x); print_manifest(x._2); print_manifest(x._2 : Int); */println( x._2:Int )} )//extract2(x):(String,Int) ) )
//    res.foreach( x => println( (extractFlat2[String,Int](x):(String,Int)) ) )//extract2(x):(String,Int) ) )
//    print_manifest(res)
//    res.foreach( x => print_manifest( extract2(x) ) )//extract2(x):(String,Int) ) )
    return ()
    printFerry(
      for(
        e <- employee
        //;id <-generator(Array(1,2,3))
        //;if e.id == id
      ) yield (e.id,e.name) // e.name + " (" + e.id + ")" //
    )
    val results = query(
      for(
        e <- employee
        //;id <-generator(Array(1,2,3))
        //;if e.id == id
      ) yield (e.id,e.name) // e.name + " (" + e.id + ")" //
    )
/*
/*    printTree(
      for(
        e <- employee
        //;id <-generator(Array(1,2,3))
        //;if e.id == id
      ) yield (e.id,e.name) // e.name + " (" + e.id + ")" //
    )
*/
    //print( "types: " )
    //println( (results(0)._1.asInstanceOf[AnyRef].getClass, results(0)._2.asInstanceOf[AnyRef].getClass) )
    //for( record <- results ) println ( record:(Int,String) )

/*    val results2 = query(
      for(
        e <- employee
        //;id <-generator(Array(1,2,3))
        //;if e.id == id
      ) yield e.name + " (" + e.id + ")" //
    )
*/
    //for( record <- results2 ) println ( record : String )
    if(true){
      val dsl2 : engines.IJdbc2 with Schemata with generators.ITreeGenerator = new engines.Jdbc2 with SchemataExp with generators.TreeGenerator {}
      import dsl2.{unit => _, _}
      implicit def unitBoolean( v: Boolean ) = dsl2.unit(v)
      implicit def unitString( v: String ) = dsl2.unit(v)
      implicit def unitDouble( v: Double ) = dsl2.unit(v)
      implicit def unitTuples[T <: Product]( v: T ) = dsl2.unit(v)
      implicit def unitInt( v: Int ) = dsl2.unit(v)
      val q = ("Test":Rep[String]) + 2


    val results = query(
      for(
        e <- dsl2.tables.employee
        //;id <-generator(Array(1,2,3))
        //;if e.id == id
      ) yield (e.id,e.name) // e.name + " (" + e.id + ")" //
    )
    /*for( record <- results ) println ( record:(Int,String) )
      /*println( q match{ case s:Sym[_] => findDefinition(s) match{
        case Some(TP(_,x)) => x
        case _ => throw new Exception()
      }} )
      println( transform("Test",q) match{ case s:Sym[_] => findDefinition(s) match{
        case Some(TP(_,x)) => x
        case _ => throw new Exception()
      }} )*/
      printSql( for(
        e <- dsl2.tables.employee
        //;id <-generator(Array(1,2,3))
        if e.id == e.id
      ) yield (e.id,e.name+" test") )
      println( genSql(q) )*/
    }

/*    printDsl( employee.name == "Chris" )
    printDsl( people.age == 5 )
    printDsl( 27 - people.age == 0 )
*/*/
  }
  /*
  def printInternals( query : Rep[Any] ){
    println( "symbol: " + query)
    // breaking out of dsl mode
    val exposedDsl = dsl.asInstanceOf[BaseExp]
    println( "findDefinition(...): " + exposedDsl.findDefinition(query.asInstanceOf[exposedDsl.Sym[_]]) )
  }*/
/*  def printDsl( rep:Rep[_] ) {
    println("-----------------------------------------------------")
    println("Expression tree:")
    //printTree( rep )
    println("")
    println("Ferry:")
    //printFerry( rep )
    println("")
    println("Sql:")
    printSql( rep )
  }*/
}

// where does this comment come from?
	  // use one of the approaches on http://stackoverflow.com/questions/1252915/scala-how-to-define-generic-function-parameters
	  // to support integer, etc. addition
