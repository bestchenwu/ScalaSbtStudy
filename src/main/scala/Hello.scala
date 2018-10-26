import akka.actor.{Actor, ActorSystem, Props}

class HelloActor(name:String) extends Actor {


  override def preStart(): Unit = {
    println("before start helloActor")
    super.preStart()
  }


  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("pre restart"+message.get)
    super.preRestart(reason, message)
  }


  override def postRestart(reason: Throwable): Unit = {
    println("post restart")
    super.postRestart(reason)
  }

  override def postStop(): Unit = {
    println("stoped helloActor")
    super.postStop()
  }

  override def receive: Receive = {
    case "hello" => println(s"hello actor,$name")
    case _ => println(s"huh?,$name")
  }
}

case object ForceRestart {
  override def toString: String = {"forceRestart"}
}

object Main extends App {
    val system = ActorSystem("HelloSystem")
    //val helloActor = system.actorOf(Props[HelloActor],name="helloActor")
    val helloActor = system.actorOf(Props(new HelloActor("sweet")),name="helloActor")
    helloActor ! "hello"
   Thread.sleep(1000)
  //helloActor ! ForceRestart

  Thread.sleep(1000)
   system.stop(helloActor)
    //helloActor ! "buenos"
    system.terminate()
}
