package unitThirteen

import akka.actor.{Actor, ActorSelection, ActorSystem, PoisonPill, Props}

case class createChild(name: String);

case class Name(name: String)


class child extends Actor {

  var childName = "no name"

  override def receive: Receive = {
    case Name(name) => this.childName = name
    case anyObject => println(s"I get message $anyObject")
  }

  override def postStop(): Unit = {
    println(s"$childName killed ,path=${self.path}")
  }
}

class Parent extends Actor {
  override def receive: Receive = {
    case createChild(name) => {
      val child = context.actorOf(Props[child],name=s"$name");
      child ! Name(name)
    }
    case _ => println("get a illegal message")
  }
}

object ChildTest extends App {

  val system = ActorSystem("childTest")
  //name代表了ActionPath
  val parentActor = system.actorOf(Props[Parent],name="Parent");
  parentActor ! createChild("jack")
  parentActor ! createChild("mary")
  Thread.sleep(500)
  val jack = system.actorSelection("/user/Parent/jack")
  //PoisonPill是任何Actor都可以理解的消息，用于立即停止Actor
  jack ! PoisonPill
//  Thread.sleep(500)
//  system.terminate()
}
