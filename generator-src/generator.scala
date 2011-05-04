package siq

case class Meta(
  val table : String,
  val fields : Map[String,String],
  val keys : List[String]
)
object generator{
  import org.fusesource.scalate._ 
  val engine = new TemplateEngine
  def renderOnce[A]( name : String, infilename:String="", outfilename:String="", args : Map[String,A] = Map[String,A]() ){
    val file_ = "src/"+(if(outfilename!="") outfilename else name)+".scala"
    if( !(new java.io.File(file_)).exists() )
      renderHelper( name, infilename, file_, args )
  }
  def render[A]( name : String, infilename:String="", outfilename:String="", args : Map[String,A] = Map[String,A]() ){
    val file_ = "src/0_generated/"+(if(outfilename!="") outfilename else name)+".scala"
    renderHelper( name, infilename, file_, args )
  }
  def renderHelper[A]( name : String, infilename:String="", outfilename:String, args : Map[String,A] ){
    val file_ = (if(infilename!="") infilename else name)
    val output = engine.layout("templates/"+(file_)+".ssp",args)
    var out_file = new java.io.FileOutputStream(outfilename)
    var out_stream = new java.io.PrintStream(out_file)
    out_stream.print("/* this file has been auto-generated */\r\n\r\n")
    out_stream.print(output)
    out_stream.close
  }
  def main(args: Array[String]){
    render("Schema",args=Map("schema" -> Map(
      "employee" -> Meta(
        table = "employee",
        fields = Map(
          "id" -> "Int",
          "name" -> "String",
          "workgroup_id" -> "Int"
        ),
        keys = List("id")
      ),
      "workgroup" -> Meta(
        table = "workgroup",
        fields = Map(
          "id" -> "Int",
          "name" -> "String"
        ),
        keys = List("id")
      ),
      "amounts" -> Meta(
        table = "countries_budgets",
        fields = Map(
          "country" -> "String",
          "branch" -> "String",
          "amount" -> "Int"
        ),
        keys = List("country","branch")
      ),
      "NATION" -> Meta(
        table = "NATION",
        fields = Map(
          "N_NATIONKEY" -> "Int",
          "N_NAME" -> "String",
          "N_REGIONKEY" -> "Int",
          "N_COMMENT" -> "String"
        ),
        keys = List("N_NATIONKEY")
      ),
      "REGION" -> Meta(
        table = "REGION",
        fields = Map(
          "R_REGIONKEY" -> "Int",
          "R_NAME" -> "String",
          "R_COMMENT" -> "String"
        ),
        keys = List("R_REGIONKEY")
      ),
      "PART" -> Meta(
        table = "PART",
        fields = Map(
          "P_PARTKEY" -> "Int",
          "P_NAME" -> "String",
          "P_MFGR" -> "String",
          "P_BRAND" -> "String",
          "P_TYPE" -> "String",
          "P_SIZE" -> "Int",
          "P_CONTAINER" -> "String",
          "P_RETAILPRICE" -> "Int",
          "P_COMMENT" -> "String"
        ),
        keys = List("P_PARTKEY")
      ),
      "SUPPLIER" -> Meta(
        table = "SUPPLIER",
        fields = Map(
          "S_SUPPKEY" -> "Int",
          "S_NAME" -> "String",
          "S_ADDRESS" -> "String",
          "S_NATIONKEY" -> "Int",
          "S_PHONE" -> "String",
          "S_ACCTBAL" -> "Int",
          "S_COMMENT" -> "String"
        ),
        keys = List("S_SUPPKEY")
      ),
      "PARTSUPP" -> Meta(
        table = "PARTSUPP",
        fields = Map(
          "PS_PARTKEY" -> "Int",
          "PS_SUPPKEY" -> "Int",
          "PS_AVAILQTY" -> "Int",
          "PS_SUPPLYCOST" -> "Int",
          "PS_COMMENT" -> "String"
        ),
        keys = List("PS_PARTKEY","PS_SUPPKEY")
      ),
      "CUSTOMER" -> Meta(
        table = "CUSTOMER",
        fields = Map(
          "C_CUSTKEY" -> "Int",
          "C_NAME" -> "String",
          "C_ADDRESS" -> "String",
          "C_NATIONKEY" -> "Int",
          "C_PHONE" -> "String",
          "C_ACCTBAL" -> "Int",
          "C_MKTSEGMENT" -> "String",
          "C_COMMENT" -> "String"
        ),
        keys = List("C_CUSTKEY")
      ),
      "ORDERS" -> Meta(
        table = "ORDERS",
        fields = Map(
          "O_ORDERKEY" -> "Int",
          "O_CUSTKEY" -> "Int",
          "O_ORDERSTATUS" -> "String",
          "O_TOTALPRICE" -> "Int",
          "O_ORDERDATE" -> "String", // DATE
          "O_ORDERPRIORITY" -> "String",
          "O_CLERK" -> "String",
          "O_SHIPPRIORITY" -> "Int",
          "O_COMMENT" -> "String"
        ),
        keys = List("O_ORDERKEY")
      ),
      "LINEITEM" -> Meta(
        table = "LINEITEM",
        fields = Map(
          "L_ORDERKEY" -> "Int",
          "L_PARTKEY" -> "Int",
          "L_SUPPKEY" -> "Int",
          "L_LINENUMBER" -> "Int",
          "L_QUANTITY" -> "Int",
          "L_EXTENDEDPRICE" -> "Int",
          "L_DISCOUNT" -> "Int",
          "L_TAX" -> "Int",
          "L_RETURNFLAG" -> "String",
          "L_LINESTATUS" -> "String",
          "L_SHIPDATE" -> "String", // DATE
          "L_COMMITDATE" -> "String", // DATE
          "L_RECEIPTDATE" -> "String", // DATE
          "L_SHIPINSTRUCT" -> "String",
          "L_SHIPMODE" -> "String",
          "L_COMMENT" -> "String"
        ),
        keys = List("L_ORDERKEY","L_LINENUMBER")
      )
    )))

    render("Results")
    render("Tuples")
    //render("OperatorArity")

    import DslSpec.modules
    render("GeneratedModules",args=Map("modules" -> modules))
    val phases = List("1Normalization")
    val targets = List()//"Sql","Tree")
    for( (module,module_num) <- modules.zipWithIndex ){
      render("Module",outfilename=module.name,args=Map("module" -> module,"module_num" -> module_num))
//      render("Transformer",outfilename="Transformer"+module.name,args=Map("module" -> module,"last_phase"->phases.last))
      for( phase <- phases ){
//        renderOnce("Transformer","TransformerPhase","transformers/Transformer"+phase+module.name,args=Map("module" -> module,"phase"->phase,"last_phase"->phases.last))
      }
      for( target <- targets ){
        //renderOnce("Generator","GeneratorTarget","generators/"+target.toLowerCase()+"/"+module.name,args=Map("module" -> module,"target"->target))
      }
    }
    for( phase <- phases ){
//      renderOnce("TransformerBase",outfilename="transformers/Transformer"+phase+"Base",args=Map("phase" -> phase))
//      render("TransformerModules",outfilename="TransformerModules"+phase,args=Map("modules" -> modules,"phase" -> phase))
      //renderOnce("Transformer",outfilename="transformers/Transformer"+phase,args=Map("module" -> module,"phases"->phases,"last_phase"->phases.last))
    }
    for( target <- targets ){
      //renderOnce("GeneratorBase",outfilename="generators/"+target.toLowerCase()+"/_Base",args=Map("target"->target))
      //render("GeneratorModules",outfilename=target+"GeneratorModules",args=Map("modules" -> modules,"target"->target))
//      renderOnce("Generator",outfilename="generators/"+target,args=Map("modules" -> modules,"target"->target))
    }
    println("done")
  }
}
