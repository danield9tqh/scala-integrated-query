package siq
trait SIQ2FerryCore extends FerryCore with Modules{
  import scala.collection.immutable.ListMap
  def siq2ferrycore( from:Rep[_], expected : ferry.ImplementationTypes.ImplementationType, symboltypes : List[ (String, ( ferry.FerryCoreTypes.FerryCoreType, ferry.ImplementationTypes.ImplementationType )) ] = List() ) : ferry.Expression = {
    import ferry._
    import ImplementationTypes._
    // manages the boxing and closures the current symboltable
    def t( from:Rep[_], expected : ImplementationType, symboltypes_ : List[ (String, ( ferry.FerryCoreTypes.FerryCoreType, ferry.ImplementationTypes.ImplementationType )) ] = symboltypes ) =
      siq2ferrycore(from,expected,symboltypes_)
    val e = from match {
      case Const(value)     => Literal(value)
      case _:Sym[_] => {
        val def_ = rep2def(from)
        def_ match{
          case _:TableExp[_] => {
            val table = def_.asInstanceOf[TableExp[_ <: Product]]
            TableReference(
              name = table.name,
              columns = table.columns.map( c => (c.name,c.type_.toLowerCase) ).toList,
              keys    = List( table.keys.map( _.name ).toList ),
              order   = table.columns.map( _.name ).toList
            )
          }
          case _:Comprehension[_] => {
            val comprehension = def_.asInstanceOf[Comprehension[_]] // workaround virtualized scala bug
            val in_ = t( comprehension.tables(0), TABLE )
            val variable = 'i'+rep2generator[Any](comprehension.tables(0)).key
            assert( !symboltypes.map(_._1).contains(variable) )
            val new_symboltypes = ( variable ->  ( in_.type_.asInstanceOf[FerryCoreTypes.list].element, ROW ) ) :: symboltypes
            val return_ = t( comprehension.element, ROW, new_symboltypes )
            For(
              variable
              , in_
              , if(comprehension.filter.isDefined) If(t(comprehension.filter.get, ROW, new_symboltypes),return_) else return_ // fix implementation type, when adding then, see both ferry thesis
              , comprehension.orderBy.map{
                  case Ascending(rep)  => (t(rep,ROW, new_symboltypes),"ASC")
                  case Descending(rep)  => (t(rep,ROW, new_symboltypes),"DESC")
              }
            )
          }
          case n:Node[_] => OperatorApplication( n.operator match{
            case "&&" => "and"
            case "||" => "or"
            case "==" => "="
            case "!=" => "<>"
            case _ => n.operator
          }, t(n.x, ROW), t(n.y, ROW) )
          case tup:LiftedTuple[_] => {
            def flatten_tuple(a: Def[Any]): List[Def[Any]] = a match{
              case t:LiftedTuple[_] => t.p.productIterator.map(_.asInstanceOf[Rep[_]]).map(rep2def _).map(flatten_tuple _).toList.flatten
              case _ => List(a)
            }
            val flattened = flatten_tuple(tup).map(toAtom _)
            FerryTuple( flattened.map( v => t(v, ROW) ).toList )
          }
          case _:FieldReference => {
            val ref = def_.asInstanceOf[FieldReference]
            val name = "i"+{
              val it = ref.asInstanceOf[FieldReference].iterable
              rep2def(it) match {
                case _:Generator[_] => rep2def(it).asInstanceOf[Generator[_]].key
              }
            }
            val access = VariableAccess( name, ListMap(symboltypes:_*)(name)._1, ListMap(symboltypes:_*)(name)._2 )
//            rep2def(ref.referree) match{
 //             case _ if ref.position == 0 => access
//               case _:LiftedTuple[_] => t(ref.referree, ROW)
//               case _ => {
                  ListMap(symboltypes:_*)(name)._1 match {
                    case FerryCoreTypes.atomic => access// ??
                    case _:FerryCoreTypes.tuple =>{
                      //println(access)
                      //println(ref.position-1)
                      //val z = access.type_.asInstanceOf[ferry.FerryCoreTypes.tuple].elements(ref.position-1)
                      //println(z)
                      PositionalAccess(
                        access
                        , ref.position
                      )
                    }
//                  }
//              }
            }
          }

          case LiteralTable( values ) => FerryList(
            values.toList,
            if( values.toList.size > 0 && values.toList(0).isInstanceOf[Product]) FerryCoreTypes.tuple( values.toList(0).asInstanceOf[Product].productIterator.map(x=>FerryCoreTypes.atomic).toList ) else FerryCoreTypes.atomic
          )//.map( x => FerryList(Some(x)) ).reduceRight[Expression]( Append(_,_) )
          //case LiteralTable( values ) => FerryList( values.map(t(_,ROW)) )
          /*
          case _:[_] => {
            val  = def_.asInstanceOf[[_]]

          }
          */

          case Flatten( list ) => Concat(
            t(list,TABLE)
          )
        }
      }
    }
    // this pattern match corresponds to the Helper Function C in Tom's Thesis
    (e.implementation_type, expected) match {
      case (ROW,ROW)     => e
      case (TABLE,ROW)   => Box(e)
      case (TABLE,TABLE) => e
      case (ROW,TABLE)   => Unbox(e)
    }
  }
}