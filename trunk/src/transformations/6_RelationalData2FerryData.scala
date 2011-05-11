package siq
import scala.collection.immutable.ListMap
trait RelationalData2FerryData extends FerryCore with SQL2RelationalData{
  case class RelationalDataRow( iter : Int, pos: Int, values:List[Any] )
  def relationaldata2ferrydata( nested_data : NestedRelationalData, ferrytype : ferry.FerryCoreTypes.FerryCoreType, iter: Int = 1 ) : Any = { // FIXME: return type
    import ferry._
    import ferry.FerryCoreTypes._
    def t = relationaldata2ferrydata _
    val data_ = nested_data.data.map{
      case iter::pos::values => RelationalDataRow(iter.toString.toInt,pos.toString.toInt,values)
      case _ => throw new Exception()
    }
    val data = data_.filter(_.iter == iter)
    val position2column = nested_data.columns.zipWithIndex.map(_.swap).toMap
    if( nested_data.nested.size == 0 ){
      ferrytype match {
        case a if a == atomic => data(0).values(0)
        case list(t) if t == atomic => data.map(_.values).flatten.toList
        case list(tuple(_)) => data.map(_.values)
        case tuple(_) => data(0).values
        case _ => throw new Exception()
      }
    }else{
      ferrytype match {
        case tuple(element_types) => data(0).values.zip(element_types).zipWithIndex.map{ case ((value,type_), position) =>
          type_ match {
            case a if a == atomic => value
            case list(element_type) => relationaldata2ferrydata( ListMap(nested_data.nested:_*)(position2column(position)), list(element_type) )
          }
        }.toList
        case list(list(element_type))   => data.map(_.values)/*.flatten*/.zipWithIndex.map{ case (iter,index) =>
            relationaldata2ferrydata( ListMap(nested_data.nested:_*)(position2column(0)), list(element_type), index+1 )
        }.toList
/*        case list(tuple(element_types)) => data.zipWithIndex.map{ case(row,index) =>
          row.values.zip(element_types).zipWithIndex.map{ case ((value,type_), position) =>
            type_ match {
              case a if a == atomic => value
              case list(element_type) => relationaldata2ferrydata( nested_data.nested(position2column(position)), list(element_type), index+1 )
            }
          }.toList
        }.toList*/
      }
    }
  }
}