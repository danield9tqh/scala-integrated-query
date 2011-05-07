package siq
trait SIQ2FerryCore extends FerryCore with Modules{
  type SymbolTypes = Map[ String, ( ferry.FerryCoreTypes.FerryCoreType, ferry.ImplementationTypes.ImplementationType ) ]
  def siq2ferrycore( from:Rep[_], symboltypes : SymbolTypes = Map() ) : ferry.Expression = {
    import ferry._
    import ImplementationTypes._
    // manages the boxing and closures the current symboltable
    def t( from:Rep[_], expected : ImplementationType, symboltypes_ : SymbolTypes = symboltypes ) = {
      val e = siq2ferrycore(from,symboltypes_)
      // this pattern match corresponds to the Helper Function C in Tom's Thesis
      (e.implementation_type, expected) match {
        case (ROW,ROW)     => e
        case (TABLE,ROW)   => Box(e)
        case (TABLE,TABLE) => e
        case (ROW,TABLE)   => Unbox(e)
      }
    }
    from match {
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
            For(
              variable,
              in_,
              t( comprehension.element, ROW, symboltypes ++
                    Map( variable ->  ( in_.type_.asInstanceOf[FerryCoreTypes.list].element, ROW ) )
              )
            )
          }
          case _:FieldReference => {
            val ref = def_.asInstanceOf[FieldReference]
            val name = "i"+{
              val it = ref.asInstanceOf[FieldReference].iterable
              rep2def(it) match {
                case _:Generator[_] => rep2def(it).asInstanceOf[Generator[_]].key
              }
            }
            val access = VariableAccess( name, symboltypes(name)._1, symboltypes(name)._2 )
            symboltypes(name)._1 match {
              case FerryCoreTypes.atomic => access
              case _:FerryCoreTypes.tuple =>
                PositionalAccess(
                  access
                  , ref.position
                )
            }
          }
          case LiteralTable( values ) => values.map( x => FerryList(Some(x)) ).reduceRight[Expression]( Append(_,_) )
          //case LiteralTable( values ) => FerryList( values.map(t(_,ROW)) )
          /*
          case _:[_] => {
            val  = def_.asInstanceOf[[_]]

          }
          */
        }
      }
    }
  }
}