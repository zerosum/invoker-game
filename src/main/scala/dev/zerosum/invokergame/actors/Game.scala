package dev.zerosum.invokergame.actors

import akka.NotUsed
import akka.actor.{Actor, ActorRef, Props}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source

import scala.io.StdIn

class Game extends Actor {

  import Game._

  override def receive: Receive = {
    case Start =>
      println("select game mode >")

      StdIn.readLine match {
        case "demo" => context.self ! Demo
        case "gg" => context.self ! GG
        case _ => context.self ! Start
      }

    case Demo =>
      import Invoker._
      import dev.zerosum.invokergame.{Quas => Q, Wex => W, Exort => E}
      implicit val mat = ActorMaterializer()
      implicit val ec = context.dispatcher

      val invoker: ActorRef = context.actorOf(Props[Invoker], "invoker")

      val input = Source(Vector(
        CastElement(E),
        CastElement(E),
        CastElement(W),
        Invoke,
        CastElement(Q),
        CastElement(W),
        Invoke,
        CastPrimarySpell,
        CastSecondarySpell,
        CastElement(W),
        CastElement(W),
        Invoke,
        CastPrimarySpell,
        CastElement(Q),
        CastElement(E),
        Invoke,
        CastPrimarySpell
      ))

      input.runForeach(invoker ! _).onComplete { _ =>
        Thread.sleep(1000)
        context.stop(invoker)
        context.self ! Start
      }

    case GG =>
      println("Good game, well played!")
      context.system.terminate()
  }
}

object Game {

  case object Start

  case object Demo

  case object GG

}
