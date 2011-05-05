package siq
trait SIQ2FerryCore extends FerryCore with Modules{
  def siq2ferrycore( from:Rep[_] ) : ferry.Expression = {
    val t = siq2ferrycore _
    import ferry._
    from match {
      case Const(value:Int)     => IntLiteral(value)
      case Const(value:Boolean) => BoolLiteral(value)
      case Const(value:Double)  => DoubleLiteral(value)
      case Const(value:String)  => StringLiteral(value)
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
    }
  }
}