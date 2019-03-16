package dev.zerosum.invokergame.actors

import akka.NotUsed
import akka.actor.{Actor, ActorRef, Props}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import dev.zerosum.invokergame.Invoker

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
      implicit val mat = ActorMaterializer()
      implicit val ec = context.dispatcher

      val invoker = new Invoker()

      val input = Source("eewrqwrdfwwrdqerf")
      input.runForeach(invoker.cast).onComplete { _ =>
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
