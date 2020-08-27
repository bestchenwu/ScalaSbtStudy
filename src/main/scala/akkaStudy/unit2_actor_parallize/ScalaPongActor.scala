package akkaStudy.unit2_actor_parallize

import akka.actor.{Actor, ActorSystem, Props}
import akka.actor.Status.Failure

class ScalaPongActor extends Actor {

  override def receive: Receive = {
    case "ping" => sender() ! "pone"
    case _ => sender() ! Failure(new Exception("unrecoginzed message"))
  }
}

object ScalaPongActor {

  private def props(response: String = ""): Props = {
    if (response.isEmpty) {
      Props(classOf[ScalaPongActor])
    } else {
      Props(classOf[ScalaPongActor], response)
    }
  }

  def createPongActor(actorSystem: ActorSystem, response: String = "", actorName: String = "") = {
    if (actorName.isEmpty) {
      actorSystem.actorOf(props())
    } else {
      actorSystem.actorOf(props(), actorName)
    }

  }
}
