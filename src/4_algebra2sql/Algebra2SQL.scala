package siq
trait Algebra2SQL extends RelationalAlgebra{
  import algebra._
//  val t = algebra2sql _
  def algebra2sql( from:Node ) : String = {
    def flatten( relation:Relation ) : List[Relation] = (relation match{
        case bin:BinaryRelationOperator => relation :: flatten(bin.left) ::: flatten(bin.right)
        case un:UnaryRelationOperator => relation :: flatten(un.relation)
        case _:Table => List()
      })
    from match {
//FIXME: where to place this? //       case Constant( value ) => value.toString // FIXME: escape Strings
      case relation:Relation =>
        val relations = flatten(relation)
          "WITH\n\n%s\n\n%s".format(
            relations.tail.map( relation => "%s(%s) AS\n(%s)".format(
              relation.name
              ,relation.schema.mkString(",")
              ,relation2sql(relation)
            )).mkString(",\n\n")
            ,relation2sql( relations.head )
          )
    }
  }

  def relation2sql( from: Relation ) : String = {
    from match {
      case Projection( relation, new_column_names ) => "SELECT %s FROM %s".format(
                                (relation.qualified_columns,new_column_names).zipped.map{case(from,to) => from+" AS "+to}.mkString(", ")
                                , relation.name
      )
      case RowNumber(relation,column_name,partitionByColumns,orderByColumns) =>
        "SELECT %s, ROW_NUMBER() OVER (PARTITION BY %s) AS %s FROM %s".format(
          relation.qualified_columns.mkString(","),
          partitionByColumns.mkString(",") + (orderByColumns match {
            case Some(x) => " ORDER BY "+x+" ASC"
            case _ => ""
          }),
          column_name,
          relation.name
       )
/*
              var temp = (name + " (" + rn.schema.mkString(", ") + ") AS\n" +
        "(SELECT " + getQualNames(resLeft, rn.schema.dropRight(1)) + ", ROW_NUMBER() OVER (PARTITION BY " + grouping)
      if (key != null)
        temp += " ORDER BY " + key
      temp += ") AS " + newName + " FROM " + resLeft + ")"
*/
/*            TableReference(
              columns = table.columns.map( c => (c.name,c.type_.toLowerCase) ).toList,
              keys    = List( table.keys.map( _.name ).toList ),
              order   = table.columns.map( _.name ).toList
            )
          }
          case _:Comprehension[_] => {
            val comprehension = def_.asInstanceOf[Comprehension[_]]
            For(
              'i'+comprehension.key,
              t( comprehension.tables(0) ),
              t( comprehension.element )
            )
          }
          case _:FieldReference => {
            val ref = def_.asInstanceOf[FieldReference]
            PositionalAccess(
              Variable( "i"+{
                val it = ref.asInstanceOf[FieldReference].iterable
                rep2def(it) match {
                  case _:Generator[_] => rep2def(it).asInstanceOf[Generator[_]].key
                  case _ => t(it)//"<"+rep2def(it).toString+">"
                }
              })
              , ref.position
            )


          }
          /*
          case _:[_] => {
            val  = def_.asInstanceOf[[_]]

          }
          */
        }
      }
 */
    }
  }
}