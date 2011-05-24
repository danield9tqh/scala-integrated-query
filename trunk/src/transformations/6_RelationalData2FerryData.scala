package siq
import scala.collection.immutable.ListMap
trait RelationalData2FerryData extends FerryCore with SQL2RelationalData{
  case class RelationalDataRow( iter : Int, pos: Int, values:List[Any] )
  def relationaldata2ferrydata( nested_data : NestedRelationalData, ferrytype : ferry.FerryCoreTypes.FerryCoreType, iter: Int = 1 ) : Any = { // FIXME: return type
    import ferry._
    import ferry.FerryCoreTypes._
    def t = relationaldata2ferrydata _

    val iter_index   = nested_data.columns.indexOf( "iter" )
    val pos_index    = nested_data.columns.indexOf( "pos" )
    val data_indices = nested_data.columns.zipWithIndex.filter( _._1.startsWith("item") )
    val data = nested_data.data.map(
      row => RelationalDataRow( row(iter_index).toString.toInt, row(pos_index).toString.toInt, data_indices.map(_._2).map(row(_)) )
    ).filter(_.iter == iter)
    val position2column = data_indices.map(_._1).zipWithIndex.map(_.swap).toMap

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
            relationaldata2ferrydata( ListMap(nested_data.nested:_*)(data_indices.head._1), list(element_type), index+1 )
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