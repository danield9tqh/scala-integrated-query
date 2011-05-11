/* this file has been auto-generated */

package siq
trait ISchema extends IComprehensions{
  abstract class Tables{
    def SUPPLIER : Rep[Iterable[SUPPLIER]]
    def employee : Rep[Iterable[Employee]]
    def REGION : Rep[Iterable[REGION]]
    def CUSTOMER : Rep[Iterable[CUSTOMER]]
    def ORDERS : Rep[Iterable[ORDERS]]
    def PARTSUPP : Rep[Iterable[PARTSUPP]]
    def workgroup : Rep[Iterable[Workgroup]]
    def NATION : Rep[Iterable[NATION]]
    def LINEITEM : Rep[Iterable[LINEITEM]]
    def PART : Rep[Iterable[PART]]
    def amounts : Rep[Iterable[Amounts]]
  }
  val tables : Tables

  class SUPPLIER(
    val S_NAME: String
    ,val S_PHONE: String
    ,val S_SUPPKEY: Int
    ,val S_COMMENT: String
    ,val S_ACCTBAL: Int
    ,val S_ADDRESS: String
    ,val S_NATIONKEY: Int
  ) extends Tuple7( S_NAME, S_PHONE, S_SUPPKEY, S_COMMENT, S_ACCTBAL, S_ADDRESS, S_NATIONKEY ){
    override def toString = "SUPPLIER"+super.toString
  }

  class Employee(
    val id: Int
    ,val name: String
    ,val workgroup_id: Int
  ) extends Tuple3( id, name, workgroup_id ){
    override def toString = "Employee"+super.toString
  }

  class REGION(
    val R_REGIONKEY: Int
    ,val R_NAME: String
    ,val R_COMMENT: String
  ) extends Tuple3( R_REGIONKEY, R_NAME, R_COMMENT ){
    override def toString = "REGION"+super.toString
  }

  class CUSTOMER(
    val C_MKTSEGMENT: String
    ,val C_COMMENT: String
    ,val C_PHONE: String
    ,val C_CUSTKEY: Int
    ,val C_NAME: String
    ,val C_ADDRESS: String
    ,val C_NATIONKEY: Int
    ,val C_ACCTBAL: Int
  ) extends Tuple8( C_MKTSEGMENT, C_COMMENT, C_PHONE, C_CUSTKEY, C_NAME, C_ADDRESS, C_NATIONKEY, C_ACCTBAL ){
    override def toString = "CUSTOMER"+super.toString
  }

  class ORDERS(
    val O_COMMENT: String
    ,val O_ORDERKEY: Int
    ,val O_ORDERPRIORITY: String
    ,val O_CUSTKEY: Int
    ,val O_CLERK: String
    ,val O_ORDERSTATUS: String
    ,val O_SHIPPRIORITY: Int
    ,val O_ORDERDATE: String
    ,val O_TOTALPRICE: Int
  ) extends Tuple9( O_COMMENT, O_ORDERKEY, O_ORDERPRIORITY, O_CUSTKEY, O_CLERK, O_ORDERSTATUS, O_SHIPPRIORITY, O_ORDERDATE, O_TOTALPRICE ){
    override def toString = "ORDERS"+super.toString
  }

  class PARTSUPP(
    val PS_AVAILQTY: Int
    ,val PS_PARTKEY: Int
    ,val PS_SUPPLYCOST: Int
    ,val PS_SUPPKEY: Int
    ,val PS_COMMENT: String
  ) extends Tuple5( PS_AVAILQTY, PS_PARTKEY, PS_SUPPLYCOST, PS_SUPPKEY, PS_COMMENT ){
    override def toString = "PARTSUPP"+super.toString
  }

  class Workgroup(
    val id: Int
    ,val name: String
  ) extends Tuple2( id, name ){
    override def toString = "Workgroup"+super.toString
  }

  class NATION(
    val N_NATIONKEY: Int
    ,val N_NAME: String
    ,val N_REGIONKEY: Int
    ,val N_COMMENT: String
  ) extends Tuple4( N_NATIONKEY, N_NAME, N_REGIONKEY, N_COMMENT ){
    override def toString = "NATION"+super.toString
  }

  class LINEITEM(
    val L_PARTKEY: Int
    ,val L_SHIPMODE: String
    ,val L_TAX: Int
    ,val L_RETURNFLAG: String
    ,val L_RECEIPTDATE: String
    ,val L_COMMITDATE: String
    ,val L_SHIPINSTRUCT: String
    ,val L_COMMENT: String
    ,val L_LINESTATUS: String
    ,val L_EXTENDEDPRICE: Int
    ,val L_SUPPKEY: Int
    ,val L_SHIPDATE: String
    ,val L_ORDERKEY: Int
    ,val L_DISCOUNT: Int
    ,val L_LINENUMBER: Int
    ,val L_QUANTITY: Int
  ) extends Tuple16( L_PARTKEY, L_SHIPMODE, L_TAX, L_RETURNFLAG, L_RECEIPTDATE, L_COMMITDATE, L_SHIPINSTRUCT, L_COMMENT, L_LINESTATUS, L_EXTENDEDPRICE, L_SUPPKEY, L_SHIPDATE, L_ORDERKEY, L_DISCOUNT, L_LINENUMBER, L_QUANTITY ){
    override def toString = "LINEITEM"+super.toString
  }

  class PART(
    val P_NAME: String
    ,val P_TYPE: String
    ,val P_RETAILPRICE: Int
    ,val P_MFGR: String
    ,val P_COMMENT: String
    ,val P_PARTKEY: Int
    ,val P_SIZE: Int
    ,val P_CONTAINER: String
    ,val P_BRAND: String
  ) extends Tuple9( P_NAME, P_TYPE, P_RETAILPRICE, P_MFGR, P_COMMENT, P_PARTKEY, P_SIZE, P_CONTAINER, P_BRAND ){
    override def toString = "PART"+super.toString
  }

  class Amounts(
    val country: String
    ,val branch: String
    ,val amount: Int
  ) extends Tuple3( country, branch, amount ){
    override def toString = "Amounts"+super.toString
  }

// --
  class SUPPLIERSchema(
    val S_NAME: Rep[String]
    ,val S_PHONE: Rep[String]
    ,val S_SUPPKEY: Rep[Int]
    ,val S_COMMENT: Rep[String]
    ,val S_ACCTBAL: Rep[Int]
    ,val S_ADDRESS: Rep[String]
    ,val S_NATIONKEY: Rep[Int]
  ) extends Tuple7( S_NAME, S_PHONE, S_SUPPKEY, S_COMMENT, S_ACCTBAL, S_ADDRESS, S_NATIONKEY ) with SchemaBase{
    override def toString = "SUPPLIERSchema"+super.toString
  }

  class EmployeeSchema(
    val id: Rep[Int]
    ,val name: Rep[String]
    ,val workgroup_id: Rep[Int]
  ) extends Tuple3( id, name, workgroup_id ) with SchemaBase{
    override def toString = "EmployeeSchema"+super.toString
  }

  class REGIONSchema(
    val R_REGIONKEY: Rep[Int]
    ,val R_NAME: Rep[String]
    ,val R_COMMENT: Rep[String]
  ) extends Tuple3( R_REGIONKEY, R_NAME, R_COMMENT ) with SchemaBase{
    override def toString = "REGIONSchema"+super.toString
  }

  class CUSTOMERSchema(
    val C_MKTSEGMENT: Rep[String]
    ,val C_COMMENT: Rep[String]
    ,val C_PHONE: Rep[String]
    ,val C_CUSTKEY: Rep[Int]
    ,val C_NAME: Rep[String]
    ,val C_ADDRESS: Rep[String]
    ,val C_NATIONKEY: Rep[Int]
    ,val C_ACCTBAL: Rep[Int]
  ) extends Tuple8( C_MKTSEGMENT, C_COMMENT, C_PHONE, C_CUSTKEY, C_NAME, C_ADDRESS, C_NATIONKEY, C_ACCTBAL ) with SchemaBase{
    override def toString = "CUSTOMERSchema"+super.toString
  }

  class ORDERSSchema(
    val O_COMMENT: Rep[String]
    ,val O_ORDERKEY: Rep[Int]
    ,val O_ORDERPRIORITY: Rep[String]
    ,val O_CUSTKEY: Rep[Int]
    ,val O_CLERK: Rep[String]
    ,val O_ORDERSTATUS: Rep[String]
    ,val O_SHIPPRIORITY: Rep[Int]
    ,val O_ORDERDATE: Rep[String]
    ,val O_TOTALPRICE: Rep[Int]
  ) extends Tuple9( O_COMMENT, O_ORDERKEY, O_ORDERPRIORITY, O_CUSTKEY, O_CLERK, O_ORDERSTATUS, O_SHIPPRIORITY, O_ORDERDATE, O_TOTALPRICE ) with SchemaBase{
    override def toString = "ORDERSSchema"+super.toString
  }

  class PARTSUPPSchema(
    val PS_AVAILQTY: Rep[Int]
    ,val PS_PARTKEY: Rep[Int]
    ,val PS_SUPPLYCOST: Rep[Int]
    ,val PS_SUPPKEY: Rep[Int]
    ,val PS_COMMENT: Rep[String]
  ) extends Tuple5( PS_AVAILQTY, PS_PARTKEY, PS_SUPPLYCOST, PS_SUPPKEY, PS_COMMENT ) with SchemaBase{
    override def toString = "PARTSUPPSchema"+super.toString
  }

  class WorkgroupSchema(
    val id: Rep[Int]
    ,val name: Rep[String]
  ) extends Tuple2( id, name ) with SchemaBase{
    override def toString = "WorkgroupSchema"+super.toString
  }

  class NATIONSchema(
    val N_NATIONKEY: Rep[Int]
    ,val N_NAME: Rep[String]
    ,val N_REGIONKEY: Rep[Int]
    ,val N_COMMENT: Rep[String]
  ) extends Tuple4( N_NATIONKEY, N_NAME, N_REGIONKEY, N_COMMENT ) with SchemaBase{
    override def toString = "NATIONSchema"+super.toString
  }

  class LINEITEMSchema(
    val L_PARTKEY: Rep[Int]
    ,val L_SHIPMODE: Rep[String]
    ,val L_TAX: Rep[Int]
    ,val L_RETURNFLAG: Rep[String]
    ,val L_RECEIPTDATE: Rep[String]
    ,val L_COMMITDATE: Rep[String]
    ,val L_SHIPINSTRUCT: Rep[String]
    ,val L_COMMENT: Rep[String]
    ,val L_LINESTATUS: Rep[String]
    ,val L_EXTENDEDPRICE: Rep[Int]
    ,val L_SUPPKEY: Rep[Int]
    ,val L_SHIPDATE: Rep[String]
    ,val L_ORDERKEY: Rep[Int]
    ,val L_DISCOUNT: Rep[Int]
    ,val L_LINENUMBER: Rep[Int]
    ,val L_QUANTITY: Rep[Int]
  ) extends Tuple16( L_PARTKEY, L_SHIPMODE, L_TAX, L_RETURNFLAG, L_RECEIPTDATE, L_COMMITDATE, L_SHIPINSTRUCT, L_COMMENT, L_LINESTATUS, L_EXTENDEDPRICE, L_SUPPKEY, L_SHIPDATE, L_ORDERKEY, L_DISCOUNT, L_LINENUMBER, L_QUANTITY ) with SchemaBase{
    override def toString = "LINEITEMSchema"+super.toString
  }

  class PARTSchema(
    val P_NAME: Rep[String]
    ,val P_TYPE: Rep[String]
    ,val P_RETAILPRICE: Rep[Int]
    ,val P_MFGR: Rep[String]
    ,val P_COMMENT: Rep[String]
    ,val P_PARTKEY: Rep[Int]
    ,val P_SIZE: Rep[Int]
    ,val P_CONTAINER: Rep[String]
    ,val P_BRAND: Rep[String]
  ) extends Tuple9( P_NAME, P_TYPE, P_RETAILPRICE, P_MFGR, P_COMMENT, P_PARTKEY, P_SIZE, P_CONTAINER, P_BRAND ) with SchemaBase{
    override def toString = "PARTSchema"+super.toString
  }

  class AmountsSchema(
    val country: Rep[String]
    ,val branch: Rep[String]
    ,val amount: Rep[Int]
  ) extends Tuple3( country, branch, amount ) with SchemaBase{
    override def toString = "AmountsSchema"+super.toString
  }

// --
  class SUPPLIERGrouped(
    val S_NAME: Iterable[String]
    ,val S_PHONE: Iterable[String]
    ,val S_SUPPKEY: Iterable[Int]
    ,val S_COMMENT: Iterable[String]
    ,val S_ACCTBAL: Iterable[Int]
    ,val S_ADDRESS: Iterable[String]
    ,val S_NATIONKEY: Iterable[Int]
  ) extends Tuple7( S_NAME, S_PHONE, S_SUPPKEY, S_COMMENT, S_ACCTBAL, S_ADDRESS, S_NATIONKEY ) with SchemaBase{
    override def toString = "SUPPLIERGrouped"+super.toString
  }

  class EmployeeGrouped(
    val id: Iterable[Int]
    ,val name: Iterable[String]
    ,val workgroup_id: Iterable[Int]
  ) extends Tuple3( id, name, workgroup_id ) with SchemaBase{
    override def toString = "EmployeeGrouped"+super.toString
  }

  class REGIONGrouped(
    val R_REGIONKEY: Iterable[Int]
    ,val R_NAME: Iterable[String]
    ,val R_COMMENT: Iterable[String]
  ) extends Tuple3( R_REGIONKEY, R_NAME, R_COMMENT ) with SchemaBase{
    override def toString = "REGIONGrouped"+super.toString
  }

  class CUSTOMERGrouped(
    val C_MKTSEGMENT: Iterable[String]
    ,val C_COMMENT: Iterable[String]
    ,val C_PHONE: Iterable[String]
    ,val C_CUSTKEY: Iterable[Int]
    ,val C_NAME: Iterable[String]
    ,val C_ADDRESS: Iterable[String]
    ,val C_NATIONKEY: Iterable[Int]
    ,val C_ACCTBAL: Iterable[Int]
  ) extends Tuple8( C_MKTSEGMENT, C_COMMENT, C_PHONE, C_CUSTKEY, C_NAME, C_ADDRESS, C_NATIONKEY, C_ACCTBAL ) with SchemaBase{
    override def toString = "CUSTOMERGrouped"+super.toString
  }

  class ORDERSGrouped(
    val O_COMMENT: Iterable[String]
    ,val O_ORDERKEY: Iterable[Int]
    ,val O_ORDERPRIORITY: Iterable[String]
    ,val O_CUSTKEY: Iterable[Int]
    ,val O_CLERK: Iterable[String]
    ,val O_ORDERSTATUS: Iterable[String]
    ,val O_SHIPPRIORITY: Iterable[Int]
    ,val O_ORDERDATE: Iterable[String]
    ,val O_TOTALPRICE: Iterable[Int]
  ) extends Tuple9( O_COMMENT, O_ORDERKEY, O_ORDERPRIORITY, O_CUSTKEY, O_CLERK, O_ORDERSTATUS, O_SHIPPRIORITY, O_ORDERDATE, O_TOTALPRICE ) with SchemaBase{
    override def toString = "ORDERSGrouped"+super.toString
  }

  class PARTSUPPGrouped(
    val PS_AVAILQTY: Iterable[Int]
    ,val PS_PARTKEY: Iterable[Int]
    ,val PS_SUPPLYCOST: Iterable[Int]
    ,val PS_SUPPKEY: Iterable[Int]
    ,val PS_COMMENT: Iterable[String]
  ) extends Tuple5( PS_AVAILQTY, PS_PARTKEY, PS_SUPPLYCOST, PS_SUPPKEY, PS_COMMENT ) with SchemaBase{
    override def toString = "PARTSUPPGrouped"+super.toString
  }

  class WorkgroupGrouped(
    val id: Iterable[Int]
    ,val name: Iterable[String]
  ) extends Tuple2( id, name ) with SchemaBase{
    override def toString = "WorkgroupGrouped"+super.toString
  }

  class NATIONGrouped(
    val N_NATIONKEY: Iterable[Int]
    ,val N_NAME: Iterable[String]
    ,val N_REGIONKEY: Iterable[Int]
    ,val N_COMMENT: Iterable[String]
  ) extends Tuple4( N_NATIONKEY, N_NAME, N_REGIONKEY, N_COMMENT ) with SchemaBase{
    override def toString = "NATIONGrouped"+super.toString
  }

  class LINEITEMGrouped(
    val L_PARTKEY: Iterable[Int]
    ,val L_SHIPMODE: Iterable[String]
    ,val L_TAX: Iterable[Int]
    ,val L_RETURNFLAG: Iterable[String]
    ,val L_RECEIPTDATE: Iterable[String]
    ,val L_COMMITDATE: Iterable[String]
    ,val L_SHIPINSTRUCT: Iterable[String]
    ,val L_COMMENT: Iterable[String]
    ,val L_LINESTATUS: Iterable[String]
    ,val L_EXTENDEDPRICE: Iterable[Int]
    ,val L_SUPPKEY: Iterable[Int]
    ,val L_SHIPDATE: Iterable[String]
    ,val L_ORDERKEY: Iterable[Int]
    ,val L_DISCOUNT: Iterable[Int]
    ,val L_LINENUMBER: Iterable[Int]
    ,val L_QUANTITY: Iterable[Int]
  ) extends Tuple16( L_PARTKEY, L_SHIPMODE, L_TAX, L_RETURNFLAG, L_RECEIPTDATE, L_COMMITDATE, L_SHIPINSTRUCT, L_COMMENT, L_LINESTATUS, L_EXTENDEDPRICE, L_SUPPKEY, L_SHIPDATE, L_ORDERKEY, L_DISCOUNT, L_LINENUMBER, L_QUANTITY ) with SchemaBase{
    override def toString = "LINEITEMGrouped"+super.toString
  }

  class PARTGrouped(
    val P_NAME: Iterable[String]
    ,val P_TYPE: Iterable[String]
    ,val P_RETAILPRICE: Iterable[Int]
    ,val P_MFGR: Iterable[String]
    ,val P_COMMENT: Iterable[String]
    ,val P_PARTKEY: Iterable[Int]
    ,val P_SIZE: Iterable[Int]
    ,val P_CONTAINER: Iterable[String]
    ,val P_BRAND: Iterable[String]
  ) extends Tuple9( P_NAME, P_TYPE, P_RETAILPRICE, P_MFGR, P_COMMENT, P_PARTKEY, P_SIZE, P_CONTAINER, P_BRAND ) with SchemaBase{
    override def toString = "PARTGrouped"+super.toString
  }

  class AmountsGrouped(
    val country: Iterable[String]
    ,val branch: Iterable[String]
    ,val amount: Iterable[Int]
  ) extends Tuple3( country, branch, amount ) with SchemaBase{
    override def toString = "AmountsGrouped"+super.toString
  }

// --
  class SUPPLIERSchemaGrouped(
    val S_NAME: Rep[Iterable[String]]
    ,val S_PHONE: Rep[Iterable[String]]
    ,val S_SUPPKEY: Rep[Iterable[Int]]
    ,val S_COMMENT: Rep[Iterable[String]]
    ,val S_ACCTBAL: Rep[Iterable[Int]]
    ,val S_ADDRESS: Rep[Iterable[String]]
    ,val S_NATIONKEY: Rep[Iterable[Int]]
  ) extends Tuple7( S_NAME, S_PHONE, S_SUPPKEY, S_COMMENT, S_ACCTBAL, S_ADDRESS, S_NATIONKEY ) with SchemaBase{
    override def toString = "SUPPLIERSchemaGrouped"+super.toString
  }

  class EmployeeSchemaGrouped(
    val id: Rep[Iterable[Int]]
    ,val name: Rep[Iterable[String]]
    ,val workgroup_id: Rep[Iterable[Int]]
  ) extends Tuple3( id, name, workgroup_id ) with SchemaBase{
    override def toString = "EmployeeSchemaGrouped"+super.toString
  }

  class REGIONSchemaGrouped(
    val R_REGIONKEY: Rep[Iterable[Int]]
    ,val R_NAME: Rep[Iterable[String]]
    ,val R_COMMENT: Rep[Iterable[String]]
  ) extends Tuple3( R_REGIONKEY, R_NAME, R_COMMENT ) with SchemaBase{
    override def toString = "REGIONSchemaGrouped"+super.toString
  }

  class CUSTOMERSchemaGrouped(
    val C_MKTSEGMENT: Rep[Iterable[String]]
    ,val C_COMMENT: Rep[Iterable[String]]
    ,val C_PHONE: Rep[Iterable[String]]
    ,val C_CUSTKEY: Rep[Iterable[Int]]
    ,val C_NAME: Rep[Iterable[String]]
    ,val C_ADDRESS: Rep[Iterable[String]]
    ,val C_NATIONKEY: Rep[Iterable[Int]]
    ,val C_ACCTBAL: Rep[Iterable[Int]]
  ) extends Tuple8( C_MKTSEGMENT, C_COMMENT, C_PHONE, C_CUSTKEY, C_NAME, C_ADDRESS, C_NATIONKEY, C_ACCTBAL ) with SchemaBase{
    override def toString = "CUSTOMERSchemaGrouped"+super.toString
  }

  class ORDERSSchemaGrouped(
    val O_COMMENT: Rep[Iterable[String]]
    ,val O_ORDERKEY: Rep[Iterable[Int]]
    ,val O_ORDERPRIORITY: Rep[Iterable[String]]
    ,val O_CUSTKEY: Rep[Iterable[Int]]
    ,val O_CLERK: Rep[Iterable[String]]
    ,val O_ORDERSTATUS: Rep[Iterable[String]]
    ,val O_SHIPPRIORITY: Rep[Iterable[Int]]
    ,val O_ORDERDATE: Rep[Iterable[String]]
    ,val O_TOTALPRICE: Rep[Iterable[Int]]
  ) extends Tuple9( O_COMMENT, O_ORDERKEY, O_ORDERPRIORITY, O_CUSTKEY, O_CLERK, O_ORDERSTATUS, O_SHIPPRIORITY, O_ORDERDATE, O_TOTALPRICE ) with SchemaBase{
    override def toString = "ORDERSSchemaGrouped"+super.toString
  }

  class PARTSUPPSchemaGrouped(
    val PS_AVAILQTY: Rep[Iterable[Int]]
    ,val PS_PARTKEY: Rep[Iterable[Int]]
    ,val PS_SUPPLYCOST: Rep[Iterable[Int]]
    ,val PS_SUPPKEY: Rep[Iterable[Int]]
    ,val PS_COMMENT: Rep[Iterable[String]]
  ) extends Tuple5( PS_AVAILQTY, PS_PARTKEY, PS_SUPPLYCOST, PS_SUPPKEY, PS_COMMENT ) with SchemaBase{
    override def toString = "PARTSUPPSchemaGrouped"+super.toString
  }

  class WorkgroupSchemaGrouped(
    val id: Rep[Iterable[Int]]
    ,val name: Rep[Iterable[String]]
  ) extends Tuple2( id, name ) with SchemaBase{
    override def toString = "WorkgroupSchemaGrouped"+super.toString
  }

  class NATIONSchemaGrouped(
    val N_NATIONKEY: Rep[Iterable[Int]]
    ,val N_NAME: Rep[Iterable[String]]
    ,val N_REGIONKEY: Rep[Iterable[Int]]
    ,val N_COMMENT: Rep[Iterable[String]]
  ) extends Tuple4( N_NATIONKEY, N_NAME, N_REGIONKEY, N_COMMENT ) with SchemaBase{
    override def toString = "NATIONSchemaGrouped"+super.toString
  }

  class LINEITEMSchemaGrouped(
    val L_PARTKEY: Rep[Iterable[Int]]
    ,val L_SHIPMODE: Rep[Iterable[String]]
    ,val L_TAX: Rep[Iterable[Int]]
    ,val L_RETURNFLAG: Rep[Iterable[String]]
    ,val L_RECEIPTDATE: Rep[Iterable[String]]
    ,val L_COMMITDATE: Rep[Iterable[String]]
    ,val L_SHIPINSTRUCT: Rep[Iterable[String]]
    ,val L_COMMENT: Rep[Iterable[String]]
    ,val L_LINESTATUS: Rep[Iterable[String]]
    ,val L_EXTENDEDPRICE: Rep[Iterable[Int]]
    ,val L_SUPPKEY: Rep[Iterable[Int]]
    ,val L_SHIPDATE: Rep[Iterable[String]]
    ,val L_ORDERKEY: Rep[Iterable[Int]]
    ,val L_DISCOUNT: Rep[Iterable[Int]]
    ,val L_LINENUMBER: Rep[Iterable[Int]]
    ,val L_QUANTITY: Rep[Iterable[Int]]
  ) extends Tuple16( L_PARTKEY, L_SHIPMODE, L_TAX, L_RETURNFLAG, L_RECEIPTDATE, L_COMMITDATE, L_SHIPINSTRUCT, L_COMMENT, L_LINESTATUS, L_EXTENDEDPRICE, L_SUPPKEY, L_SHIPDATE, L_ORDERKEY, L_DISCOUNT, L_LINENUMBER, L_QUANTITY ) with SchemaBase{
    override def toString = "LINEITEMSchemaGrouped"+super.toString
  }

  class PARTSchemaGrouped(
    val P_NAME: Rep[Iterable[String]]
    ,val P_TYPE: Rep[Iterable[String]]
    ,val P_RETAILPRICE: Rep[Iterable[Int]]
    ,val P_MFGR: Rep[Iterable[String]]
    ,val P_COMMENT: Rep[Iterable[String]]
    ,val P_PARTKEY: Rep[Iterable[Int]]
    ,val P_SIZE: Rep[Iterable[Int]]
    ,val P_CONTAINER: Rep[Iterable[String]]
    ,val P_BRAND: Rep[Iterable[String]]
  ) extends Tuple9( P_NAME, P_TYPE, P_RETAILPRICE, P_MFGR, P_COMMENT, P_PARTKEY, P_SIZE, P_CONTAINER, P_BRAND ) with SchemaBase{
    override def toString = "PARTSchemaGrouped"+super.toString
  }

  class AmountsSchemaGrouped(
    val country: Rep[Iterable[String]]
    ,val branch: Rep[Iterable[String]]
    ,val amount: Rep[Iterable[Int]]
  ) extends Tuple3( country, branch, amount ) with SchemaBase{
    override def toString = "AmountsSchemaGrouped"+super.toString
  }


  implicit def rep2SUPPLIER( r:Rep[SUPPLIER] ) : SUPPLIERSchema
  implicit def rep2SUPPLIERgrouped( r:Rep[SUPPLIERGrouped] ) : SUPPLIERSchemaGrouped
  trait ISUPPLIERGroupable{
    def groupBy( f:SUPPLIERSchema => Rep[_] ) : Rep[Iterable[SUPPLIERGrouped]]
  }
  implicit def rep2SUPPLIERgroupable( r: Rep[Iterable[SUPPLIER]] ) : ISUPPLIERGroupable
  implicit def rep2employee( r:Rep[Employee] ) : EmployeeSchema
  implicit def rep2employeegrouped( r:Rep[EmployeeGrouped] ) : EmployeeSchemaGrouped
  trait IEmployeeGroupable{
    def groupBy( f:EmployeeSchema => Rep[_] ) : Rep[Iterable[EmployeeGrouped]]
  }
  implicit def rep2employeegroupable( r: Rep[Iterable[Employee]] ) : IEmployeeGroupable
  implicit def rep2REGION( r:Rep[REGION] ) : REGIONSchema
  implicit def rep2REGIONgrouped( r:Rep[REGIONGrouped] ) : REGIONSchemaGrouped
  trait IREGIONGroupable{
    def groupBy( f:REGIONSchema => Rep[_] ) : Rep[Iterable[REGIONGrouped]]
  }
  implicit def rep2REGIONgroupable( r: Rep[Iterable[REGION]] ) : IREGIONGroupable
  implicit def rep2CUSTOMER( r:Rep[CUSTOMER] ) : CUSTOMERSchema
  implicit def rep2CUSTOMERgrouped( r:Rep[CUSTOMERGrouped] ) : CUSTOMERSchemaGrouped
  trait ICUSTOMERGroupable{
    def groupBy( f:CUSTOMERSchema => Rep[_] ) : Rep[Iterable[CUSTOMERGrouped]]
  }
  implicit def rep2CUSTOMERgroupable( r: Rep[Iterable[CUSTOMER]] ) : ICUSTOMERGroupable
  implicit def rep2ORDERS( r:Rep[ORDERS] ) : ORDERSSchema
  implicit def rep2ORDERSgrouped( r:Rep[ORDERSGrouped] ) : ORDERSSchemaGrouped
  trait IORDERSGroupable{
    def groupBy( f:ORDERSSchema => Rep[_] ) : Rep[Iterable[ORDERSGrouped]]
  }
  implicit def rep2ORDERSgroupable( r: Rep[Iterable[ORDERS]] ) : IORDERSGroupable
  implicit def rep2PARTSUPP( r:Rep[PARTSUPP] ) : PARTSUPPSchema
  implicit def rep2PARTSUPPgrouped( r:Rep[PARTSUPPGrouped] ) : PARTSUPPSchemaGrouped
  trait IPARTSUPPGroupable{
    def groupBy( f:PARTSUPPSchema => Rep[_] ) : Rep[Iterable[PARTSUPPGrouped]]
  }
  implicit def rep2PARTSUPPgroupable( r: Rep[Iterable[PARTSUPP]] ) : IPARTSUPPGroupable
  implicit def rep2workgroup( r:Rep[Workgroup] ) : WorkgroupSchema
  implicit def rep2workgroupgrouped( r:Rep[WorkgroupGrouped] ) : WorkgroupSchemaGrouped
  trait IWorkgroupGroupable{
    def groupBy( f:WorkgroupSchema => Rep[_] ) : Rep[Iterable[WorkgroupGrouped]]
  }
  implicit def rep2workgroupgroupable( r: Rep[Iterable[Workgroup]] ) : IWorkgroupGroupable
  implicit def rep2NATION( r:Rep[NATION] ) : NATIONSchema
  implicit def rep2NATIONgrouped( r:Rep[NATIONGrouped] ) : NATIONSchemaGrouped
  trait INATIONGroupable{
    def groupBy( f:NATIONSchema => Rep[_] ) : Rep[Iterable[NATIONGrouped]]
  }
  implicit def rep2NATIONgroupable( r: Rep[Iterable[NATION]] ) : INATIONGroupable
  implicit def rep2LINEITEM( r:Rep[LINEITEM] ) : LINEITEMSchema
  implicit def rep2LINEITEMgrouped( r:Rep[LINEITEMGrouped] ) : LINEITEMSchemaGrouped
  trait ILINEITEMGroupable{
    def groupBy( f:LINEITEMSchema => Rep[_] ) : Rep[Iterable[LINEITEMGrouped]]
  }
  implicit def rep2LINEITEMgroupable( r: Rep[Iterable[LINEITEM]] ) : ILINEITEMGroupable
  implicit def rep2PART( r:Rep[PART] ) : PARTSchema
  implicit def rep2PARTgrouped( r:Rep[PARTGrouped] ) : PARTSchemaGrouped
  trait IPARTGroupable{
    def groupBy( f:PARTSchema => Rep[_] ) : Rep[Iterable[PARTGrouped]]
  }
  implicit def rep2PARTgroupable( r: Rep[Iterable[PART]] ) : IPARTGroupable
  implicit def rep2amounts( r:Rep[Amounts] ) : AmountsSchema
  implicit def rep2amountsgrouped( r:Rep[AmountsGrouped] ) : AmountsSchemaGrouped
  trait IAmountsGroupable{
    def groupBy( f:AmountsSchema => Rep[_] ) : Rep[Iterable[AmountsGrouped]]
  }
  implicit def rep2amountsgroupable( r: Rep[Iterable[Amounts]] ) : IAmountsGroupable

  def zip( r:Rep[SUPPLIERGrouped] )(implicit o0: Overloaded0, o1: Overloaded0 ) : Rep[Iterable[SUPPLIER]]
  def zip( r:Rep[EmployeeGrouped] )(implicit o0: Overloaded0, o1: Overloaded1 ) : Rep[Iterable[Employee]]
  def zip( r:Rep[REGIONGrouped] )(implicit o0: Overloaded0, o1: Overloaded2 ) : Rep[Iterable[REGION]]
  def zip( r:Rep[CUSTOMERGrouped] )(implicit o0: Overloaded0, o1: Overloaded3 ) : Rep[Iterable[CUSTOMER]]
  def zip( r:Rep[ORDERSGrouped] )(implicit o0: Overloaded0, o1: Overloaded4 ) : Rep[Iterable[ORDERS]]
  def zip( r:Rep[PARTSUPPGrouped] )(implicit o0: Overloaded0, o1: Overloaded5 ) : Rep[Iterable[PARTSUPP]]
  def zip( r:Rep[WorkgroupGrouped] )(implicit o0: Overloaded0, o1: Overloaded6 ) : Rep[Iterable[Workgroup]]
  def zip( r:Rep[NATIONGrouped] )(implicit o0: Overloaded0, o1: Overloaded7 ) : Rep[Iterable[NATION]]
  def zip( r:Rep[LINEITEMGrouped] )(implicit o0: Overloaded0, o1: Overloaded8 ) : Rep[Iterable[LINEITEM]]
  def zip( r:Rep[PARTGrouped] )(implicit o0: Overloaded0, o1: Overloaded9 ) : Rep[Iterable[PART]]
  def zip( r:Rep[AmountsGrouped] )(implicit o0: Overloaded0, o1: Overloaded10 ) : Rep[Iterable[Amounts]]
}

trait Schema extends ISchema with Comprehensions{

  case class ZipSUPPLIER( target:Rep[SUPPLIERGrouped] ) extends Generator[SUPPLIER] with Zip{
    val element = toAtom( new LiftedSchema[SUPPLIERSchema,SUPPLIER](
      new SUPPLIERSchema(
        toAtom(Zipped(target._1))
        ,toAtom(Zipped(target._2))
        ,toAtom(Zipped(target._3))
        ,toAtom(Zipped(target._4))
        ,toAtom(Zipped(target._5))
        ,toAtom(Zipped(target._6))
        ,toAtom(Zipped(target._7))
    )))
  }
  def zip( r:Rep[SUPPLIERGrouped] )(implicit o0: Overloaded0, o1: Overloaded0 )  = ZipSUPPLIER(r)
  case class ZipEmployee( target:Rep[EmployeeGrouped] ) extends Generator[Employee] with Zip{
    val element = toAtom( new LiftedSchema[EmployeeSchema,Employee](
      new EmployeeSchema(
        toAtom(Zipped(target._1))
        ,toAtom(Zipped(target._2))
        ,toAtom(Zipped(target._3))
    )))
  }
  def zip( r:Rep[EmployeeGrouped] )(implicit o0: Overloaded0, o1: Overloaded1 )  = ZipEmployee(r)
  case class ZipREGION( target:Rep[REGIONGrouped] ) extends Generator[REGION] with Zip{
    val element = toAtom( new LiftedSchema[REGIONSchema,REGION](
      new REGIONSchema(
        toAtom(Zipped(target._1))
        ,toAtom(Zipped(target._2))
        ,toAtom(Zipped(target._3))
    )))
  }
  def zip( r:Rep[REGIONGrouped] )(implicit o0: Overloaded0, o1: Overloaded2 )  = ZipREGION(r)
  case class ZipCUSTOMER( target:Rep[CUSTOMERGrouped] ) extends Generator[CUSTOMER] with Zip{
    val element = toAtom( new LiftedSchema[CUSTOMERSchema,CUSTOMER](
      new CUSTOMERSchema(
        toAtom(Zipped(target._1))
        ,toAtom(Zipped(target._2))
        ,toAtom(Zipped(target._3))
        ,toAtom(Zipped(target._4))
        ,toAtom(Zipped(target._5))
        ,toAtom(Zipped(target._6))
        ,toAtom(Zipped(target._7))
        ,toAtom(Zipped(target._8))
    )))
  }
  def zip( r:Rep[CUSTOMERGrouped] )(implicit o0: Overloaded0, o1: Overloaded3 )  = ZipCUSTOMER(r)
  case class ZipORDERS( target:Rep[ORDERSGrouped] ) extends Generator[ORDERS] with Zip{
    val element = toAtom( new LiftedSchema[ORDERSSchema,ORDERS](
      new ORDERSSchema(
        toAtom(Zipped(target._1))
        ,toAtom(Zipped(target._2))
        ,toAtom(Zipped(target._3))
        ,toAtom(Zipped(target._4))
        ,toAtom(Zipped(target._5))
        ,toAtom(Zipped(target._6))
        ,toAtom(Zipped(target._7))
        ,toAtom(Zipped(target._8))
        ,toAtom(Zipped(target._9))
    )))
  }
  def zip( r:Rep[ORDERSGrouped] )(implicit o0: Overloaded0, o1: Overloaded4 )  = ZipORDERS(r)
  case class ZipPARTSUPP( target:Rep[PARTSUPPGrouped] ) extends Generator[PARTSUPP] with Zip{
    val element = toAtom( new LiftedSchema[PARTSUPPSchema,PARTSUPP](
      new PARTSUPPSchema(
        toAtom(Zipped(target._1))
        ,toAtom(Zipped(target._2))
        ,toAtom(Zipped(target._3))
        ,toAtom(Zipped(target._4))
        ,toAtom(Zipped(target._5))
    )))
  }
  def zip( r:Rep[PARTSUPPGrouped] )(implicit o0: Overloaded0, o1: Overloaded5 )  = ZipPARTSUPP(r)
  case class ZipWorkgroup( target:Rep[WorkgroupGrouped] ) extends Generator[Workgroup] with Zip{
    val element = toAtom( new LiftedSchema[WorkgroupSchema,Workgroup](
      new WorkgroupSchema(
        toAtom(Zipped(target._1))
        ,toAtom(Zipped(target._2))
    )))
  }
  def zip( r:Rep[WorkgroupGrouped] )(implicit o0: Overloaded0, o1: Overloaded6 )  = ZipWorkgroup(r)
  case class ZipNATION( target:Rep[NATIONGrouped] ) extends Generator[NATION] with Zip{
    val element = toAtom( new LiftedSchema[NATIONSchema,NATION](
      new NATIONSchema(
        toAtom(Zipped(target._1))
        ,toAtom(Zipped(target._2))
        ,toAtom(Zipped(target._3))
        ,toAtom(Zipped(target._4))
    )))
  }
  def zip( r:Rep[NATIONGrouped] )(implicit o0: Overloaded0, o1: Overloaded7 )  = ZipNATION(r)
  case class ZipLINEITEM( target:Rep[LINEITEMGrouped] ) extends Generator[LINEITEM] with Zip{
    val element = toAtom( new LiftedSchema[LINEITEMSchema,LINEITEM](
      new LINEITEMSchema(
        toAtom(Zipped(target._1))
        ,toAtom(Zipped(target._2))
        ,toAtom(Zipped(target._3))
        ,toAtom(Zipped(target._4))
        ,toAtom(Zipped(target._5))
        ,toAtom(Zipped(target._6))
        ,toAtom(Zipped(target._7))
        ,toAtom(Zipped(target._8))
        ,toAtom(Zipped(target._9))
        ,toAtom(Zipped(target._10))
        ,toAtom(Zipped(target._11))
        ,toAtom(Zipped(target._12))
        ,toAtom(Zipped(target._13))
        ,toAtom(Zipped(target._14))
        ,toAtom(Zipped(target._15))
        ,toAtom(Zipped(target._16))
    )))
  }
  def zip( r:Rep[LINEITEMGrouped] )(implicit o0: Overloaded0, o1: Overloaded8 )  = ZipLINEITEM(r)
  case class ZipPART( target:Rep[PARTGrouped] ) extends Generator[PART] with Zip{
    val element = toAtom( new LiftedSchema[PARTSchema,PART](
      new PARTSchema(
        toAtom(Zipped(target._1))
        ,toAtom(Zipped(target._2))
        ,toAtom(Zipped(target._3))
        ,toAtom(Zipped(target._4))
        ,toAtom(Zipped(target._5))
        ,toAtom(Zipped(target._6))
        ,toAtom(Zipped(target._7))
        ,toAtom(Zipped(target._8))
        ,toAtom(Zipped(target._9))
    )))
  }
  def zip( r:Rep[PARTGrouped] )(implicit o0: Overloaded0, o1: Overloaded9 )  = ZipPART(r)
  case class ZipAmounts( target:Rep[AmountsGrouped] ) extends Generator[Amounts] with Zip{
    val element = toAtom( new LiftedSchema[AmountsSchema,Amounts](
      new AmountsSchema(
        toAtom(Zipped(target._1))
        ,toAtom(Zipped(target._2))
        ,toAtom(Zipped(target._3))
    )))
  }
  def zip( r:Rep[AmountsGrouped] )(implicit o0: Overloaded0, o1: Overloaded10 )  = ZipAmounts(r)

  implicit def rep2SUPPLIERgroupable( r: Rep[Iterable[SUPPLIER]] ) = SUPPLIERGroupable(rep2generator(r))

  case class SUPPLIERGroupable( g:Generator[SUPPLIER] ) extends ISUPPLIERGroupable{
    def groupBy( f:SUPPLIERSchema => Rep[_] ) : Rep[Iterable[SUPPLIERGrouped]] = {
        val cols = rep2tuple7(replace_with_references(g.element, g))
        val groups = Tuple7(
          toAtom(Grouped(cols._1))
          ,toAtom(Grouped(cols._2))
          ,toAtom(Grouped(cols._3))
          ,toAtom(Grouped(cols._4))
          ,toAtom(Grouped(cols._5))
          ,toAtom(Grouped(cols._6))
          ,toAtom(Grouped(cols._7))
        )
        new Comprehension[ SUPPLIERGrouped ](
          List[Rep[Iterable[_]]](toAtom(g))
          , replace_with_references(groups, g)
          , groupBy = Some(f( new SUPPLIERSchema(cols._1,cols._2,cols._3,cols._4,cols._5,cols._6,cols._7) ))
        )
    }
  }
  implicit def rep2employeegroupable( r: Rep[Iterable[Employee]] ) = EmployeeGroupable(rep2generator(r))

  case class EmployeeGroupable( g:Generator[Employee] ) extends IEmployeeGroupable{
    def groupBy( f:EmployeeSchema => Rep[_] ) : Rep[Iterable[EmployeeGrouped]] = {
        val cols = rep2tuple3(replace_with_references(g.element, g))
        val groups = Tuple3(
          toAtom(Grouped(cols._1))
          ,toAtom(Grouped(cols._2))
          ,toAtom(Grouped(cols._3))
        )
        new Comprehension[ EmployeeGrouped ](
          List[Rep[Iterable[_]]](toAtom(g))
          , replace_with_references(groups, g)
          , groupBy = Some(f( new EmployeeSchema(cols._1,cols._2,cols._3) ))
        )
    }
  }
  implicit def rep2REGIONgroupable( r: Rep[Iterable[REGION]] ) = REGIONGroupable(rep2generator(r))

  case class REGIONGroupable( g:Generator[REGION] ) extends IREGIONGroupable{
    def groupBy( f:REGIONSchema => Rep[_] ) : Rep[Iterable[REGIONGrouped]] = {
        val cols = rep2tuple3(replace_with_references(g.element, g))
        val groups = Tuple3(
          toAtom(Grouped(cols._1))
          ,toAtom(Grouped(cols._2))
          ,toAtom(Grouped(cols._3))
        )
        new Comprehension[ REGIONGrouped ](
          List[Rep[Iterable[_]]](toAtom(g))
          , replace_with_references(groups, g)
          , groupBy = Some(f( new REGIONSchema(cols._1,cols._2,cols._3) ))
        )
    }
  }
  implicit def rep2CUSTOMERgroupable( r: Rep[Iterable[CUSTOMER]] ) = CUSTOMERGroupable(rep2generator(r))

  case class CUSTOMERGroupable( g:Generator[CUSTOMER] ) extends ICUSTOMERGroupable{
    def groupBy( f:CUSTOMERSchema => Rep[_] ) : Rep[Iterable[CUSTOMERGrouped]] = {
        val cols = rep2tuple8(replace_with_references(g.element, g))
        val groups = Tuple8(
          toAtom(Grouped(cols._1))
          ,toAtom(Grouped(cols._2))
          ,toAtom(Grouped(cols._3))
          ,toAtom(Grouped(cols._4))
          ,toAtom(Grouped(cols._5))
          ,toAtom(Grouped(cols._6))
          ,toAtom(Grouped(cols._7))
          ,toAtom(Grouped(cols._8))
        )
        new Comprehension[ CUSTOMERGrouped ](
          List[Rep[Iterable[_]]](toAtom(g))
          , replace_with_references(groups, g)
          , groupBy = Some(f( new CUSTOMERSchema(cols._1,cols._2,cols._3,cols._4,cols._5,cols._6,cols._7,cols._8) ))
        )
    }
  }
  implicit def rep2ORDERSgroupable( r: Rep[Iterable[ORDERS]] ) = ORDERSGroupable(rep2generator(r))

  case class ORDERSGroupable( g:Generator[ORDERS] ) extends IORDERSGroupable{
    def groupBy( f:ORDERSSchema => Rep[_] ) : Rep[Iterable[ORDERSGrouped]] = {
        val cols = rep2tuple9(replace_with_references(g.element, g))
        val groups = Tuple9(
          toAtom(Grouped(cols._1))
          ,toAtom(Grouped(cols._2))
          ,toAtom(Grouped(cols._3))
          ,toAtom(Grouped(cols._4))
          ,toAtom(Grouped(cols._5))
          ,toAtom(Grouped(cols._6))
          ,toAtom(Grouped(cols._7))
          ,toAtom(Grouped(cols._8))
          ,toAtom(Grouped(cols._9))
        )
        new Comprehension[ ORDERSGrouped ](
          List[Rep[Iterable[_]]](toAtom(g))
          , replace_with_references(groups, g)
          , groupBy = Some(f( new ORDERSSchema(cols._1,cols._2,cols._3,cols._4,cols._5,cols._6,cols._7,cols._8,cols._9) ))
        )
    }
  }
  implicit def rep2PARTSUPPgroupable( r: Rep[Iterable[PARTSUPP]] ) = PARTSUPPGroupable(rep2generator(r))

  case class PARTSUPPGroupable( g:Generator[PARTSUPP] ) extends IPARTSUPPGroupable{
    def groupBy( f:PARTSUPPSchema => Rep[_] ) : Rep[Iterable[PARTSUPPGrouped]] = {
        val cols = rep2tuple5(replace_with_references(g.element, g))
        val groups = Tuple5(
          toAtom(Grouped(cols._1))
          ,toAtom(Grouped(cols._2))
          ,toAtom(Grouped(cols._3))
          ,toAtom(Grouped(cols._4))
          ,toAtom(Grouped(cols._5))
        )
        new Comprehension[ PARTSUPPGrouped ](
          List[Rep[Iterable[_]]](toAtom(g))
          , replace_with_references(groups, g)
          , groupBy = Some(f( new PARTSUPPSchema(cols._1,cols._2,cols._3,cols._4,cols._5) ))
        )
    }
  }
  implicit def rep2workgroupgroupable( r: Rep[Iterable[Workgroup]] ) = WorkgroupGroupable(rep2generator(r))

  case class WorkgroupGroupable( g:Generator[Workgroup] ) extends IWorkgroupGroupable{
    def groupBy( f:WorkgroupSchema => Rep[_] ) : Rep[Iterable[WorkgroupGrouped]] = {
        val cols = rep2tuple2(replace_with_references(g.element, g))
        val groups = Tuple2(
          toAtom(Grouped(cols._1))
          ,toAtom(Grouped(cols._2))
        )
        new Comprehension[ WorkgroupGrouped ](
          List[Rep[Iterable[_]]](toAtom(g))
          , replace_with_references(groups, g)
          , groupBy = Some(f( new WorkgroupSchema(cols._1,cols._2) ))
        )
    }
  }
  implicit def rep2NATIONgroupable( r: Rep[Iterable[NATION]] ) = NATIONGroupable(rep2generator(r))

  case class NATIONGroupable( g:Generator[NATION] ) extends INATIONGroupable{
    def groupBy( f:NATIONSchema => Rep[_] ) : Rep[Iterable[NATIONGrouped]] = {
        val cols = rep2tuple4(replace_with_references(g.element, g))
        val groups = Tuple4(
          toAtom(Grouped(cols._1))
          ,toAtom(Grouped(cols._2))
          ,toAtom(Grouped(cols._3))
          ,toAtom(Grouped(cols._4))
        )
        new Comprehension[ NATIONGrouped ](
          List[Rep[Iterable[_]]](toAtom(g))
          , replace_with_references(groups, g)
          , groupBy = Some(f( new NATIONSchema(cols._1,cols._2,cols._3,cols._4) ))
        )
    }
  }
  implicit def rep2LINEITEMgroupable( r: Rep[Iterable[LINEITEM]] ) = LINEITEMGroupable(rep2generator(r))

  case class LINEITEMGroupable( g:Generator[LINEITEM] ) extends ILINEITEMGroupable{
    def groupBy( f:LINEITEMSchema => Rep[_] ) : Rep[Iterable[LINEITEMGrouped]] = {
        val cols = rep2tuple16(replace_with_references(g.element, g))
        val groups = Tuple16(
          toAtom(Grouped(cols._1))
          ,toAtom(Grouped(cols._2))
          ,toAtom(Grouped(cols._3))
          ,toAtom(Grouped(cols._4))
          ,toAtom(Grouped(cols._5))
          ,toAtom(Grouped(cols._6))
          ,toAtom(Grouped(cols._7))
          ,toAtom(Grouped(cols._8))
          ,toAtom(Grouped(cols._9))
          ,toAtom(Grouped(cols._10))
          ,toAtom(Grouped(cols._11))
          ,toAtom(Grouped(cols._12))
          ,toAtom(Grouped(cols._13))
          ,toAtom(Grouped(cols._14))
          ,toAtom(Grouped(cols._15))
          ,toAtom(Grouped(cols._16))
        )
        new Comprehension[ LINEITEMGrouped ](
          List[Rep[Iterable[_]]](toAtom(g))
          , replace_with_references(groups, g)
          , groupBy = Some(f( new LINEITEMSchema(cols._1,cols._2,cols._3,cols._4,cols._5,cols._6,cols._7,cols._8,cols._9,cols._10,cols._11,cols._12,cols._13,cols._14,cols._15,cols._16) ))
        )
    }
  }
  implicit def rep2PARTgroupable( r: Rep[Iterable[PART]] ) = PARTGroupable(rep2generator(r))

  case class PARTGroupable( g:Generator[PART] ) extends IPARTGroupable{
    def groupBy( f:PARTSchema => Rep[_] ) : Rep[Iterable[PARTGrouped]] = {
        val cols = rep2tuple9(replace_with_references(g.element, g))
        val groups = Tuple9(
          toAtom(Grouped(cols._1))
          ,toAtom(Grouped(cols._2))
          ,toAtom(Grouped(cols._3))
          ,toAtom(Grouped(cols._4))
          ,toAtom(Grouped(cols._5))
          ,toAtom(Grouped(cols._6))
          ,toAtom(Grouped(cols._7))
          ,toAtom(Grouped(cols._8))
          ,toAtom(Grouped(cols._9))
        )
        new Comprehension[ PARTGrouped ](
          List[Rep[Iterable[_]]](toAtom(g))
          , replace_with_references(groups, g)
          , groupBy = Some(f( new PARTSchema(cols._1,cols._2,cols._3,cols._4,cols._5,cols._6,cols._7,cols._8,cols._9) ))
        )
    }
  }
  implicit def rep2amountsgroupable( r: Rep[Iterable[Amounts]] ) = AmountsGroupable(rep2generator(r))

  case class AmountsGroupable( g:Generator[Amounts] ) extends IAmountsGroupable{
    def groupBy( f:AmountsSchema => Rep[_] ) : Rep[Iterable[AmountsGrouped]] = {
        val cols = rep2tuple3(replace_with_references(g.element, g))
        val groups = Tuple3(
          toAtom(Grouped(cols._1))
          ,toAtom(Grouped(cols._2))
          ,toAtom(Grouped(cols._3))
        )
        new Comprehension[ AmountsGrouped ](
          List[Rep[Iterable[_]]](toAtom(g))
          , replace_with_references(groups, g)
          , groupBy = Some(f( new AmountsSchema(cols._1,cols._2,cols._3) ))
        )
    }
  }

  implicit def rep2SUPPLIER( r:Rep[SUPPLIER] ) = {
    val t = rep2tuple7(r);
    new SUPPLIERSchema(t._1,t._2,t._3,t._4,t._5,t._6,t._7)
  }
  implicit def rep2SUPPLIERgrouped( r:Rep[SUPPLIERGrouped] ) = {
    val t = rep2tuple7(r);
    new SUPPLIERSchemaGrouped(t._1,t._2,t._3,t._4,t._5,t._6,t._7)
  }
  implicit def rep2employee( r:Rep[Employee] ) = {
    val t = rep2tuple3(r);
    new EmployeeSchema(t._1,t._2,t._3)
  }
  implicit def rep2employeegrouped( r:Rep[EmployeeGrouped] ) = {
    val t = rep2tuple3(r);
    new EmployeeSchemaGrouped(t._1,t._2,t._3)
  }
  implicit def rep2REGION( r:Rep[REGION] ) = {
    val t = rep2tuple3(r);
    new REGIONSchema(t._1,t._2,t._3)
  }
  implicit def rep2REGIONgrouped( r:Rep[REGIONGrouped] ) = {
    val t = rep2tuple3(r);
    new REGIONSchemaGrouped(t._1,t._2,t._3)
  }
  implicit def rep2CUSTOMER( r:Rep[CUSTOMER] ) = {
    val t = rep2tuple8(r);
    new CUSTOMERSchema(t._1,t._2,t._3,t._4,t._5,t._6,t._7,t._8)
  }
  implicit def rep2CUSTOMERgrouped( r:Rep[CUSTOMERGrouped] ) = {
    val t = rep2tuple8(r);
    new CUSTOMERSchemaGrouped(t._1,t._2,t._3,t._4,t._5,t._6,t._7,t._8)
  }
  implicit def rep2ORDERS( r:Rep[ORDERS] ) = {
    val t = rep2tuple9(r);
    new ORDERSSchema(t._1,t._2,t._3,t._4,t._5,t._6,t._7,t._8,t._9)
  }
  implicit def rep2ORDERSgrouped( r:Rep[ORDERSGrouped] ) = {
    val t = rep2tuple9(r);
    new ORDERSSchemaGrouped(t._1,t._2,t._3,t._4,t._5,t._6,t._7,t._8,t._9)
  }
  implicit def rep2PARTSUPP( r:Rep[PARTSUPP] ) = {
    val t = rep2tuple5(r);
    new PARTSUPPSchema(t._1,t._2,t._3,t._4,t._5)
  }
  implicit def rep2PARTSUPPgrouped( r:Rep[PARTSUPPGrouped] ) = {
    val t = rep2tuple5(r);
    new PARTSUPPSchemaGrouped(t._1,t._2,t._3,t._4,t._5)
  }
  implicit def rep2workgroup( r:Rep[Workgroup] ) = {
    val t = rep2tuple2(r);
    new WorkgroupSchema(t._1,t._2)
  }
  implicit def rep2workgroupgrouped( r:Rep[WorkgroupGrouped] ) = {
    val t = rep2tuple2(r);
    new WorkgroupSchemaGrouped(t._1,t._2)
  }
  implicit def rep2NATION( r:Rep[NATION] ) = {
    val t = rep2tuple4(r);
    new NATIONSchema(t._1,t._2,t._3,t._4)
  }
  implicit def rep2NATIONgrouped( r:Rep[NATIONGrouped] ) = {
    val t = rep2tuple4(r);
    new NATIONSchemaGrouped(t._1,t._2,t._3,t._4)
  }
  implicit def rep2LINEITEM( r:Rep[LINEITEM] ) = {
    val t = rep2tuple16(r);
    new LINEITEMSchema(t._1,t._2,t._3,t._4,t._5,t._6,t._7,t._8,t._9,t._10,t._11,t._12,t._13,t._14,t._15,t._16)
  }
  implicit def rep2LINEITEMgrouped( r:Rep[LINEITEMGrouped] ) = {
    val t = rep2tuple16(r);
    new LINEITEMSchemaGrouped(t._1,t._2,t._3,t._4,t._5,t._6,t._7,t._8,t._9,t._10,t._11,t._12,t._13,t._14,t._15,t._16)
  }
  implicit def rep2PART( r:Rep[PART] ) = {
    val t = rep2tuple9(r);
    new PARTSchema(t._1,t._2,t._3,t._4,t._5,t._6,t._7,t._8,t._9)
  }
  implicit def rep2PARTgrouped( r:Rep[PARTGrouped] ) = {
    val t = rep2tuple9(r);
    new PARTSchemaGrouped(t._1,t._2,t._3,t._4,t._5,t._6,t._7,t._8,t._9)
  }
  implicit def rep2amounts( r:Rep[Amounts] ) = {
    val t = rep2tuple3(r);
    new AmountsSchema(t._1,t._2,t._3)
  }
  implicit def rep2amountsgrouped( r:Rep[AmountsGrouped] ) = {
    val t = rep2tuple3(r);
    new AmountsSchemaGrouped(t._1,t._2,t._3)
  }

  def createModelInstance( m:Manifest[_], values:scala.collection.mutable.Stack[Any] ) : AnyRef = {
    def getn( n:Int, values:scala.collection.mutable.Stack[Any] ) = {
      Range(0,n).map( _ => values.pop )
    }
    m.erasure.toString.dropWhile( c => c != '$' ).drop(1) match{
      case "SUPPLIER" => {
        val v = getn(7,values);
        new SUPPLIER(
          v(0).toString
          ,v(1).toString
          ,v(2).toString.toDouble.toInt
          ,v(3).toString
          ,v(4).toString.toDouble.toInt
          ,v(5).toString
          ,v(6).toString.toDouble.toInt
        )
      }
      case "SUPPLIERGrouped" => {
        val v = getn(7,values);
        new SUPPLIERGrouped(
          v(0).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(1).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(2).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(3).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(4).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(5).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(6).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
        )
      }
      case "Employee" => {
        val v = getn(3,values);
        new Employee(
          v(0).toString.toDouble.toInt
          ,v(1).toString
          ,v(2).toString.toDouble.toInt
        )
      }
      case "EmployeeGrouped" => {
        val v = getn(3,values);
        new EmployeeGrouped(
          v(0).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(1).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(2).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
        )
      }
      case "REGION" => {
        val v = getn(3,values);
        new REGION(
          v(0).toString.toDouble.toInt
          ,v(1).toString
          ,v(2).toString
        )
      }
      case "REGIONGrouped" => {
        val v = getn(3,values);
        new REGIONGrouped(
          v(0).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(1).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(2).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
        )
      }
      case "CUSTOMER" => {
        val v = getn(8,values);
        new CUSTOMER(
          v(0).toString
          ,v(1).toString
          ,v(2).toString
          ,v(3).toString.toDouble.toInt
          ,v(4).toString
          ,v(5).toString
          ,v(6).toString.toDouble.toInt
          ,v(7).toString.toDouble.toInt
        )
      }
      case "CUSTOMERGrouped" => {
        val v = getn(8,values);
        new CUSTOMERGrouped(
          v(0).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(1).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(2).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(3).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(4).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(5).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(6).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(7).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
        )
      }
      case "ORDERS" => {
        val v = getn(9,values);
        new ORDERS(
          v(0).toString
          ,v(1).toString.toDouble.toInt
          ,v(2).toString
          ,v(3).toString.toDouble.toInt
          ,v(4).toString
          ,v(5).toString
          ,v(6).toString.toDouble.toInt
          ,v(7).toString
          ,v(8).toString.toDouble.toInt
        )
      }
      case "ORDERSGrouped" => {
        val v = getn(9,values);
        new ORDERSGrouped(
          v(0).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(1).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(2).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(3).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(4).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(5).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(6).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(7).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(8).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
        )
      }
      case "PARTSUPP" => {
        val v = getn(5,values);
        new PARTSUPP(
          v(0).toString.toDouble.toInt
          ,v(1).toString.toDouble.toInt
          ,v(2).toString.toDouble.toInt
          ,v(3).toString.toDouble.toInt
          ,v(4).toString
        )
      }
      case "PARTSUPPGrouped" => {
        val v = getn(5,values);
        new PARTSUPPGrouped(
          v(0).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(1).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(2).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(3).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(4).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
        )
      }
      case "Workgroup" => {
        val v = getn(2,values);
        new Workgroup(
          v(0).toString.toDouble.toInt
          ,v(1).toString
        )
      }
      case "WorkgroupGrouped" => {
        val v = getn(2,values);
        new WorkgroupGrouped(
          v(0).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(1).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
        )
      }
      case "NATION" => {
        val v = getn(4,values);
        new NATION(
          v(0).toString.toDouble.toInt
          ,v(1).toString
          ,v(2).toString.toDouble.toInt
          ,v(3).toString
        )
      }
      case "NATIONGrouped" => {
        val v = getn(4,values);
        new NATIONGrouped(
          v(0).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(1).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(2).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(3).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
        )
      }
      case "LINEITEM" => {
        val v = getn(16,values);
        new LINEITEM(
          v(0).toString.toDouble.toInt
          ,v(1).toString
          ,v(2).toString.toDouble.toInt
          ,v(3).toString
          ,v(4).toString
          ,v(5).toString
          ,v(6).toString
          ,v(7).toString
          ,v(8).toString
          ,v(9).toString.toDouble.toInt
          ,v(10).toString.toDouble.toInt
          ,v(11).toString
          ,v(12).toString.toDouble.toInt
          ,v(13).toString.toDouble.toInt
          ,v(14).toString.toDouble.toInt
          ,v(15).toString.toDouble.toInt
        )
      }
      case "LINEITEMGrouped" => {
        val v = getn(16,values);
        new LINEITEMGrouped(
          v(0).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(1).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(2).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(3).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(4).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(5).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(6).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(7).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(8).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(9).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(10).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(11).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(12).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(13).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(14).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(15).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
        )
      }
      case "PART" => {
        val v = getn(9,values);
        new PART(
          v(0).toString
          ,v(1).toString
          ,v(2).toString.toDouble.toInt
          ,v(3).toString
          ,v(4).toString
          ,v(5).toString.toDouble.toInt
          ,v(6).toString.toDouble.toInt
          ,v(7).toString
          ,v(8).toString
        )
      }
      case "PARTGrouped" => {
        val v = getn(9,values);
        new PARTGrouped(
          v(0).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(1).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(2).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(3).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(4).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(5).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(6).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
          ,v(7).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(8).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
        )
      }
      case "Amounts" => {
        val v = getn(3,values);
        new Amounts(
          v(0).toString
          ,v(1).toString
          ,v(2).toString.toDouble.toInt
        )
      }
      case "AmountsGrouped" => {
        val v = getn(3,values);
        new AmountsGrouped(
          v(0).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(1).asInstanceOf[List[_]].map(x => x.toString).asInstanceOf[List[String]]
          ,v(2).asInstanceOf[List[_]].map(x => x.toString.toDouble.toInt).asInstanceOf[List[Int]]
        )
      }
    }
  }

  val tables = new Tables{
    var alias_counter = 0
    def SUPPLIER = new TableExp[SUPPLIER]( "SUPPLIER" ){
      val schema = new SUPPLIERSchema(
        toAtom(Column[String]( "S_NAME", "String" ))
        ,toAtom(Column[String]( "S_PHONE", "String" ))
        ,toAtom(Column[Int]( "S_SUPPKEY", "Int" ))
        ,toAtom(Column[String]( "S_COMMENT", "String" ))
        ,toAtom(Column[Int]( "S_ACCTBAL", "Int" ))
        ,toAtom(Column[String]( "S_ADDRESS", "String" ))
        ,toAtom(Column[Int]( "S_NATIONKEY", "Int" ))
      )
      val element = toAtom( new LiftedSchema[SUPPLIERSchema,SUPPLIER](schema) )
      val columns = Array[Column[_]](
        rep2def(schema.S_NAME).asInstanceOf[Column[_]]
        ,rep2def(schema.S_PHONE).asInstanceOf[Column[_]]
        ,rep2def(schema.S_SUPPKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.S_COMMENT).asInstanceOf[Column[_]]
        ,rep2def(schema.S_ACCTBAL).asInstanceOf[Column[_]]
        ,rep2def(schema.S_ADDRESS).asInstanceOf[Column[_]]
        ,rep2def(schema.S_NATIONKEY).asInstanceOf[Column[_]]
      )
      val keys = Array[Column[_]](
        rep2def(schema.S_SUPPKEY).asInstanceOf[Column[_]]
      )
    }
    def employee = new TableExp[Employee]( "employee" ){
      val schema = new EmployeeSchema(
        toAtom(Column[Int]( "id", "Int" ))
        ,toAtom(Column[String]( "name", "String" ))
        ,toAtom(Column[Int]( "workgroup_id", "Int" ))
      )
      val element = toAtom( new LiftedSchema[EmployeeSchema,Employee](schema) )
      val columns = Array[Column[_]](
        rep2def(schema.id).asInstanceOf[Column[_]]
        ,rep2def(schema.name).asInstanceOf[Column[_]]
        ,rep2def(schema.workgroup_id).asInstanceOf[Column[_]]
      )
      val keys = Array[Column[_]](
        rep2def(schema.id).asInstanceOf[Column[_]]
      )
    }
    def REGION = new TableExp[REGION]( "REGION" ){
      val schema = new REGIONSchema(
        toAtom(Column[Int]( "R_REGIONKEY", "Int" ))
        ,toAtom(Column[String]( "R_NAME", "String" ))
        ,toAtom(Column[String]( "R_COMMENT", "String" ))
      )
      val element = toAtom( new LiftedSchema[REGIONSchema,REGION](schema) )
      val columns = Array[Column[_]](
        rep2def(schema.R_REGIONKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.R_NAME).asInstanceOf[Column[_]]
        ,rep2def(schema.R_COMMENT).asInstanceOf[Column[_]]
      )
      val keys = Array[Column[_]](
        rep2def(schema.R_REGIONKEY).asInstanceOf[Column[_]]
      )
    }
    def CUSTOMER = new TableExp[CUSTOMER]( "CUSTOMER" ){
      val schema = new CUSTOMERSchema(
        toAtom(Column[String]( "C_MKTSEGMENT", "String" ))
        ,toAtom(Column[String]( "C_COMMENT", "String" ))
        ,toAtom(Column[String]( "C_PHONE", "String" ))
        ,toAtom(Column[Int]( "C_CUSTKEY", "Int" ))
        ,toAtom(Column[String]( "C_NAME", "String" ))
        ,toAtom(Column[String]( "C_ADDRESS", "String" ))
        ,toAtom(Column[Int]( "C_NATIONKEY", "Int" ))
        ,toAtom(Column[Int]( "C_ACCTBAL", "Int" ))
      )
      val element = toAtom( new LiftedSchema[CUSTOMERSchema,CUSTOMER](schema) )
      val columns = Array[Column[_]](
        rep2def(schema.C_MKTSEGMENT).asInstanceOf[Column[_]]
        ,rep2def(schema.C_COMMENT).asInstanceOf[Column[_]]
        ,rep2def(schema.C_PHONE).asInstanceOf[Column[_]]
        ,rep2def(schema.C_CUSTKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.C_NAME).asInstanceOf[Column[_]]
        ,rep2def(schema.C_ADDRESS).asInstanceOf[Column[_]]
        ,rep2def(schema.C_NATIONKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.C_ACCTBAL).asInstanceOf[Column[_]]
      )
      val keys = Array[Column[_]](
        rep2def(schema.C_CUSTKEY).asInstanceOf[Column[_]]
      )
    }
    def ORDERS = new TableExp[ORDERS]( "ORDERS" ){
      val schema = new ORDERSSchema(
        toAtom(Column[String]( "O_COMMENT", "String" ))
        ,toAtom(Column[Int]( "O_ORDERKEY", "Int" ))
        ,toAtom(Column[String]( "O_ORDERPRIORITY", "String" ))
        ,toAtom(Column[Int]( "O_CUSTKEY", "Int" ))
        ,toAtom(Column[String]( "O_CLERK", "String" ))
        ,toAtom(Column[String]( "O_ORDERSTATUS", "String" ))
        ,toAtom(Column[Int]( "O_SHIPPRIORITY", "Int" ))
        ,toAtom(Column[String]( "O_ORDERDATE", "String" ))
        ,toAtom(Column[Int]( "O_TOTALPRICE", "Int" ))
      )
      val element = toAtom( new LiftedSchema[ORDERSSchema,ORDERS](schema) )
      val columns = Array[Column[_]](
        rep2def(schema.O_COMMENT).asInstanceOf[Column[_]]
        ,rep2def(schema.O_ORDERKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.O_ORDERPRIORITY).asInstanceOf[Column[_]]
        ,rep2def(schema.O_CUSTKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.O_CLERK).asInstanceOf[Column[_]]
        ,rep2def(schema.O_ORDERSTATUS).asInstanceOf[Column[_]]
        ,rep2def(schema.O_SHIPPRIORITY).asInstanceOf[Column[_]]
        ,rep2def(schema.O_ORDERDATE).asInstanceOf[Column[_]]
        ,rep2def(schema.O_TOTALPRICE).asInstanceOf[Column[_]]
      )
      val keys = Array[Column[_]](
        rep2def(schema.O_ORDERKEY).asInstanceOf[Column[_]]
      )
    }
    def PARTSUPP = new TableExp[PARTSUPP]( "PARTSUPP" ){
      val schema = new PARTSUPPSchema(
        toAtom(Column[Int]( "PS_AVAILQTY", "Int" ))
        ,toAtom(Column[Int]( "PS_PARTKEY", "Int" ))
        ,toAtom(Column[Int]( "PS_SUPPLYCOST", "Int" ))
        ,toAtom(Column[Int]( "PS_SUPPKEY", "Int" ))
        ,toAtom(Column[String]( "PS_COMMENT", "String" ))
      )
      val element = toAtom( new LiftedSchema[PARTSUPPSchema,PARTSUPP](schema) )
      val columns = Array[Column[_]](
        rep2def(schema.PS_AVAILQTY).asInstanceOf[Column[_]]
        ,rep2def(schema.PS_PARTKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.PS_SUPPLYCOST).asInstanceOf[Column[_]]
        ,rep2def(schema.PS_SUPPKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.PS_COMMENT).asInstanceOf[Column[_]]
      )
      val keys = Array[Column[_]](
        rep2def(schema.PS_PARTKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.PS_SUPPKEY).asInstanceOf[Column[_]]
      )
    }
    def workgroup = new TableExp[Workgroup]( "workgroup" ){
      val schema = new WorkgroupSchema(
        toAtom(Column[Int]( "id", "Int" ))
        ,toAtom(Column[String]( "name", "String" ))
      )
      val element = toAtom( new LiftedSchema[WorkgroupSchema,Workgroup](schema) )
      val columns = Array[Column[_]](
        rep2def(schema.id).asInstanceOf[Column[_]]
        ,rep2def(schema.name).asInstanceOf[Column[_]]
      )
      val keys = Array[Column[_]](
        rep2def(schema.id).asInstanceOf[Column[_]]
      )
    }
    def NATION = new TableExp[NATION]( "NATION" ){
      val schema = new NATIONSchema(
        toAtom(Column[Int]( "N_NATIONKEY", "Int" ))
        ,toAtom(Column[String]( "N_NAME", "String" ))
        ,toAtom(Column[Int]( "N_REGIONKEY", "Int" ))
        ,toAtom(Column[String]( "N_COMMENT", "String" ))
      )
      val element = toAtom( new LiftedSchema[NATIONSchema,NATION](schema) )
      val columns = Array[Column[_]](
        rep2def(schema.N_NATIONKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.N_NAME).asInstanceOf[Column[_]]
        ,rep2def(schema.N_REGIONKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.N_COMMENT).asInstanceOf[Column[_]]
      )
      val keys = Array[Column[_]](
        rep2def(schema.N_NATIONKEY).asInstanceOf[Column[_]]
      )
    }
    def LINEITEM = new TableExp[LINEITEM]( "LINEITEM" ){
      val schema = new LINEITEMSchema(
        toAtom(Column[Int]( "L_PARTKEY", "Int" ))
        ,toAtom(Column[String]( "L_SHIPMODE", "String" ))
        ,toAtom(Column[Int]( "L_TAX", "Int" ))
        ,toAtom(Column[String]( "L_RETURNFLAG", "String" ))
        ,toAtom(Column[String]( "L_RECEIPTDATE", "String" ))
        ,toAtom(Column[String]( "L_COMMITDATE", "String" ))
        ,toAtom(Column[String]( "L_SHIPINSTRUCT", "String" ))
        ,toAtom(Column[String]( "L_COMMENT", "String" ))
        ,toAtom(Column[String]( "L_LINESTATUS", "String" ))
        ,toAtom(Column[Int]( "L_EXTENDEDPRICE", "Int" ))
        ,toAtom(Column[Int]( "L_SUPPKEY", "Int" ))
        ,toAtom(Column[String]( "L_SHIPDATE", "String" ))
        ,toAtom(Column[Int]( "L_ORDERKEY", "Int" ))
        ,toAtom(Column[Int]( "L_DISCOUNT", "Int" ))
        ,toAtom(Column[Int]( "L_LINENUMBER", "Int" ))
        ,toAtom(Column[Int]( "L_QUANTITY", "Int" ))
      )
      val element = toAtom( new LiftedSchema[LINEITEMSchema,LINEITEM](schema) )
      val columns = Array[Column[_]](
        rep2def(schema.L_PARTKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.L_SHIPMODE).asInstanceOf[Column[_]]
        ,rep2def(schema.L_TAX).asInstanceOf[Column[_]]
        ,rep2def(schema.L_RETURNFLAG).asInstanceOf[Column[_]]
        ,rep2def(schema.L_RECEIPTDATE).asInstanceOf[Column[_]]
        ,rep2def(schema.L_COMMITDATE).asInstanceOf[Column[_]]
        ,rep2def(schema.L_SHIPINSTRUCT).asInstanceOf[Column[_]]
        ,rep2def(schema.L_COMMENT).asInstanceOf[Column[_]]
        ,rep2def(schema.L_LINESTATUS).asInstanceOf[Column[_]]
        ,rep2def(schema.L_EXTENDEDPRICE).asInstanceOf[Column[_]]
        ,rep2def(schema.L_SUPPKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.L_SHIPDATE).asInstanceOf[Column[_]]
        ,rep2def(schema.L_ORDERKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.L_DISCOUNT).asInstanceOf[Column[_]]
        ,rep2def(schema.L_LINENUMBER).asInstanceOf[Column[_]]
        ,rep2def(schema.L_QUANTITY).asInstanceOf[Column[_]]
      )
      val keys = Array[Column[_]](
        rep2def(schema.L_ORDERKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.L_LINENUMBER).asInstanceOf[Column[_]]
      )
    }
    def PART = new TableExp[PART]( "PART" ){
      val schema = new PARTSchema(
        toAtom(Column[String]( "P_NAME", "String" ))
        ,toAtom(Column[String]( "P_TYPE", "String" ))
        ,toAtom(Column[Int]( "P_RETAILPRICE", "Int" ))
        ,toAtom(Column[String]( "P_MFGR", "String" ))
        ,toAtom(Column[String]( "P_COMMENT", "String" ))
        ,toAtom(Column[Int]( "P_PARTKEY", "Int" ))
        ,toAtom(Column[Int]( "P_SIZE", "Int" ))
        ,toAtom(Column[String]( "P_CONTAINER", "String" ))
        ,toAtom(Column[String]( "P_BRAND", "String" ))
      )
      val element = toAtom( new LiftedSchema[PARTSchema,PART](schema) )
      val columns = Array[Column[_]](
        rep2def(schema.P_NAME).asInstanceOf[Column[_]]
        ,rep2def(schema.P_TYPE).asInstanceOf[Column[_]]
        ,rep2def(schema.P_RETAILPRICE).asInstanceOf[Column[_]]
        ,rep2def(schema.P_MFGR).asInstanceOf[Column[_]]
        ,rep2def(schema.P_COMMENT).asInstanceOf[Column[_]]
        ,rep2def(schema.P_PARTKEY).asInstanceOf[Column[_]]
        ,rep2def(schema.P_SIZE).asInstanceOf[Column[_]]
        ,rep2def(schema.P_CONTAINER).asInstanceOf[Column[_]]
        ,rep2def(schema.P_BRAND).asInstanceOf[Column[_]]
      )
      val keys = Array[Column[_]](
        rep2def(schema.P_PARTKEY).asInstanceOf[Column[_]]
      )
    }
    def amounts = new TableExp[Amounts]( "countries_budgets" ){
      val schema = new AmountsSchema(
        toAtom(Column[String]( "country", "String" ))
        ,toAtom(Column[String]( "branch", "String" ))
        ,toAtom(Column[Int]( "amount", "Int" ))
      )
      val element = toAtom( new LiftedSchema[AmountsSchema,Amounts](schema) )
      val columns = Array[Column[_]](
        rep2def(schema.country).asInstanceOf[Column[_]]
        ,rep2def(schema.branch).asInstanceOf[Column[_]]
        ,rep2def(schema.amount).asInstanceOf[Column[_]]
      )
      val keys = Array[Column[_]](
        rep2def(schema.country).asInstanceOf[Column[_]]
        ,rep2def(schema.branch).asInstanceOf[Column[_]]
      )
    }
  }
}
