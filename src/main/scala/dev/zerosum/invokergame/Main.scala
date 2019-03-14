package dev.zerosum.invokergame

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.{Done, NotUsed}

import scala.concurrent.Future

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("InvokerGame")

  val source: Source[Char, NotUsed] = Source("qwwrdwrd")

  val flow: Flow[Char, String, NotUsed] = Flow[Char].map {
    case 'q' => "[Quas]"
    case 'w' => "[Wex]"
    case 'e' => "[Exort]"
    case 'r' => "Invoke!!"
    case 'd' => "Cast primary"
    case 'f' => "Cast secondary"
    case _ => ""
  }

  val sink: Sink[String, Future[Done]] = Sink.foreach[String](println)

  implicit val materializer: ActorMaterializer = ActorMaterializer()
  (source via flow to sink).run()
}
