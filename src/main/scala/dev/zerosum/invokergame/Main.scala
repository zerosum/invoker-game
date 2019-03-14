package dev.zerosum.invokergame

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.{Done, NotUsed}
import dev.zerosum.invokergame.actors.Invoker

import scala.concurrent.Future

object Main extends App {

  implicit val system: ActorSystem = ActorSystem("InvokerGame")
  implicit val mat: ActorMaterializer = ActorMaterializer()

  private val invokerProps: Props = Props[Invoker]
  private val invokerRef: ActorRef = system.actorOf(invokerProps, "invoker")

  val source: Source[Char, NotUsed] = Source("qwwrdwrd")

  val flow: Flow[Char, Unit, NotUsed] = Flow[Char].map {
    case 'q' =>
      invokerRef ! Invoker.CastElement(Quas)
    case 'w' =>
      invokerRef ! Invoker.CastElement(Wex)
    case 'e' =>
      invokerRef ! Invoker.CastElement(Exort)
    case 'r' =>
      invokerRef ! Invoker.Invoke
    case 'd' =>
      invokerRef ! Invoker.CastPrimarySpell
    case 'f' =>
      invokerRef ! Invoker.CastSecondarySpell
  }

  val sink: Sink[Unit, Future[Done]] = Sink.foreach[Unit]{ _ =>
    Thread.sleep(1000)
  }

  (source via flow to sink).run()
}
