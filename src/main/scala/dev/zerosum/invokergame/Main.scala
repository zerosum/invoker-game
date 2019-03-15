package dev.zerosum.invokergame

import akka.actor.{ActorRef, ActorSystem, Props}
import dev.zerosum.invokergame.actors.Game
import dev.zerosum.invokergame.actors.Game.Start

object Main extends App {

  implicit val system: ActorSystem = ActorSystem("InvokerGame")
  private val gameRef: ActorRef = system.actorOf(Props[Game], "game")

  gameRef ! Start
}
