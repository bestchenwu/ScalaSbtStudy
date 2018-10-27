package unitThirteen

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.gracefulStop
import scala.concurrent.duration._
import scala.concurrent.{Await, Future, ExecutionContext}
import scala.language.postfixOps

class GraceActor extends Actor {
  override def receive: Receive = {
    case anyString => println(s"get $anyString")
  }

  override def postStop(): Unit = {
    println("postStop")
  }
}

object GracefulStopTest extends App {
  val actorSystem = ActorSystem("test")
  val graceActor = actorSystem.actorOf(Props[GraceActor], "grace");
  //graceActor ! "hello grace"
  try {
    val stopped: Future[Boolean] = gracefulStop(graceActor, 2 seconds)
    Await.result(stopped, 3 seconds)
  } catch {
    case e: Exception => e.printStackTrace()
  } finally {
    actorSystem.terminate()
  }
}
