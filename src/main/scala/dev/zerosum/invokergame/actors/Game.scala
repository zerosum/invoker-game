package dev.zerosum.invokergame.actors

import akka.actor.Actor

import scala.io.StdIn

class Game extends Actor {

  import Game._

  override def receive: Receive = {
    case Start =>
      println("select game mode >")

      StdIn.readLine match {
        case "gg" => context.self ! GG
        case _ => context.self ! Start
      }

    case GG =>
      println("Good game, well played!")
      context.system.terminate()
  }
}

object Game {

  case object Start

  case object GG

}
