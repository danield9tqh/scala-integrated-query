package siq
import java.io.PrintWriter

trait IEqual extends IModuleBase with OverloadHack {
  def __equal(a: Rep[Any], b: Rep[Any]): Rep[Boolean]
  // == is defined on Any, so no implicits will be used, so the following is necessary
  def __equal(a: Rep[Any], b: Any)(implicit o: Overloaded1): Rep[Boolean] = __equal(a, unit(b))
  def __equal(a: Any, b: Rep[Any])(implicit o: Overloaded2): Rep[Boolean] = __equal(unit(a), b)

  def infix_<>( a:Rep[Any], b:Rep[Any] ) : Rep[Boolean]
}

trait Equal  extends ModuleBase with IEqual {
  case class `==(Any,Any)`(x: Exp[Any], y: Exp[Any]) extends Node[Boolean]( "==" )
  def __equal(a: Exp[Any], b: Exp[Any]): Exp[Boolean] = `==(Any,Any)`(a,b)

  case class Not( r:Exp[Boolean] ) extends Def[Boolean]
  def infix_<>( a:Exp[Any], b:Exp[Any] ) : Exp[Boolean] = Not( __equal(a,b) )

  addIdentityMappings(
    args => args.node match{
      case Not   ( x )    => Not( args.mapper(x) )
      case `==(Any,Any)` ( x, y ) => `==(Any,Any)`( args.mapper(x), args.mapper(y) )
      case _ => throw new NoMappingException()
    }
  )
}
