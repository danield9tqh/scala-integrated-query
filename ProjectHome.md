**_Update:_** We are currently working on project [Slick](https://slick.typesafe.com), integrating ideas from **Scala Integrated Query**, **[Scala Query](http://scalaquery.org/)** and additional techniques for compile time processing and optimizations. We made an initial release in summer 2012.

### Scala Integrated Query (SIQ) ###

Scala Integrated Query is a prototype developed at LAMP, EPFL. It
compiles a subset of Scala into SQL and executes it in the DBMS. Queries
can result in single values or arbitrarily nested lists and tuples,
which can require more than one SQL query. Compared to using SQL
directly, using Scala Integrated Query can lead to more accurate code
and makes it easier to achieve good performance for complex queries. It
features greater expressiveness, type safety, familiar syntax and easy
composability. Complex queries are efficiently mapped to SQL and
automatically optimized. Avalanches of SQL queries are prevented, in
particular when correlating data in main-memory with data in the
database. This prototype was developed in a Master's project and builds
up on research of University of Tübingen, namely Ferry and Pathfinder.

[SIQ Thesis](http://scala-integrated-query.googlecode.com/files/SIQ-Thesis-final.pdf) (PDF)

### Talks about SIQ ###
**Stanford University, Scala Days 2011, June 2011**

overview, motivation, use case

[Slides (+extras)](http://scala-integrated-query.googlecode.com/files/SIQ-Scala-Days-final.pdf) | [Video](http://days2011.scala-lang.org/node/138/279) | [Announcement](http://days2011.scala-lang.org/node/244)


**EPFL Lausanne, Switzerland, August 2011**

focus on translation Scala to SQL involving Ferry translation

[Slides](http://scala-integrated-query.googlecode.com/files/SIQ-EPFL-defense.pdf)


**RWTH Aachen University, Germany, August 2011**

focus on code lifting and type safety

[Slides](http://scala-integrated-query.googlecode.com/files/SIQ-RWTH-defense.pdf)


## Running the tests ##
(This has not been tested on computers other than my development machine so far. If it does not work for you as described, please contact me at scala-integrated-query@cvogt.org)

1. You need Scala Virtualized to try it out. Get it at https://github.com/TiarkRompf/scala-virtualized/ . Compile it with ant and then proceed.

2. Checkout the code of Scala Integrated Query and create a file in the main folder of the working copy containing the location of your Scala Virtualized location.

local.properties
```
scala.virtualized.home=c:/scala-virtualized/build/pack
```
(adapt to your path)

3. Setup a postgres server and import the file demo.sql from your working copy into a database which can be reached by jdbc under this connection
```
jdbc:postgresql://localhost/siq?user=siq&password=siq&characterEncoding=UTF8
```
which is currently hard coded in the prototype.

4. run
`sbt test-run`
in the main folder of the working copy.