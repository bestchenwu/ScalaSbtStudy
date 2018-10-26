import akka.actor.{Actor, ActorSystem, Props}

class HelloActor(name:String) extends Actor {


  override def preStart(): Unit = {
    println("before start helloActor")
    super.preStart()
  }

  /**
    * actor只有在发生异常的时候才会重启
    *
    * @param reason
    * @param message
    */
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println(s"because of reason=${reason.getMessage()},before restart message is:"+message.get)
    super.preRestart(reason, message)
  }

  override def postRestart(reason: Throwable): Unit = {
    println(s"post restart,reason=${reason.getMessage()}")
    super.postRestart(reason)
  }

  override def postStop(): Unit = {
    println("stoped helloActor")
    super.postStop()
  }

  override def receive: Receive = {
    case "hello" => println(s"hello actor,$name")
    case _ => throw new Exception("boom")
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
   helloActor ! ForceRestart

  Thread.sleep(1000)
   system.stop(helloActor)
    //helloActor ! "buenos"
    system.terminate()
}
