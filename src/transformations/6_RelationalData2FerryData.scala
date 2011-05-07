package siq
trait RelationalData2FerryData extends FerryCore with SQL2RelationalData{
  def relationaldata2ferrydata( nested_data : NestedRelationalData, ferrytype : ferry.FerryCoreTypes.FerryCoreType, iter: Int = 1 ) : Any = { // FIXME: return type
    import ferry._
    import ferry.FerryCoreTypes._
    def t = relationaldata2ferrydata _
    val data =  nested_data.data.filter(_.iter == iter)
    val position2column = nested_data.columns.zipWithIndex.map(_.swap).toMap
    if( nested_data.nested.size == 0 ){
      ferrytype match {
        case a if a == atomic => data(0).values(0)
        case list(t) if t == atomic => data.map(_.values).flatten.toList
        case list(tuple(_)) => data.map(_.values)
        case tuple(_) => data(0).values
      }
    }else{
      ferrytype match {
        case tuple(element_types) => data(0).values.zip(element_types).zipWithIndex.map{ case ((value,type_), position) =>
          type_ match {
            case a if a == atomic => value
            case list(element_type) => relationaldata2ferrydata( nested_data.nested(position2column(position)), list(element_type) )
          }
        }.toList
        case list(list(element_type))   => data.map(_.values).flatten.map( iter => relationaldata2ferrydata( nested_data.nested(position2column(0)), list(element_type) )).toList
        case list(tuple(element_types)) => data.zipWithIndex.map{ case(row,iter) =>
          row.values.zip(element_types).zipWithIndex.map{ case ((value,type_), position) =>
            type_ match {
              case a if a == atomic => value
              case list(element_type) => relationaldata2ferrydata( nested_data.nested(position2column(position)), list(element_type), iter+1 )
            }
          }.toList
        }.toList
      }
    }
  }
}