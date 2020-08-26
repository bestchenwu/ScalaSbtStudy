package akkaStudy.unit2_actor_parallize

import akka.actor.Actor
import akka.actor.Status.Failure

class ScalaPongActor extends Actor{
  override def receive: Receive = {
    case "ping" => sender() ! "pone"
    case _ => sender() ! Failure(new Exception("unrecoginzed message"))
  }
}
