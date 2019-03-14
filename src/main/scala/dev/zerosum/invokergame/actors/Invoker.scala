package dev.zerosum.invokergame.actors

import akka.actor.Actor
import dev.zerosum.invokergame.{Element, Elements, Spell, Void => V}

class Invoker extends Actor {

  import Invoker._

  private var elements: Elements = Elements(V, V, V)
  private var primarySpell: Option[Spell] = None
  private var secondarySpell: Option[Spell] = None

  override def receive: Receive = {
    case CastElement(e) =>
      elements = elements.add(e)
      println(s"Cast [$e]")

    case Invoke =>
      secondarySpell = primarySpell
      primarySpell = Option(Spell.invokedWith(elements))
      primarySpell.fold(println("Nothing has been invoked"))(spell => println(s"Invoke `$spell`"))

    case CastPrimarySpell =>
      primarySpell.fold(println("Nothing has been casted"))(spell => println(s"Cast `$spell`"))

    case CastSecondarySpell =>
      secondarySpell.fold(println("Nothing has been casted"))(spell => println(s"Cast `$spell`"))
  }
}

object Invoker {
  case class CastElement(element: Element)
  case object Invoke
  case object CastPrimarySpell
  case object CastSecondarySpell
}