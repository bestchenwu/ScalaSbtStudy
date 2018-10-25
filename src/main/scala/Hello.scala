import akka.actor.{Actor, ActorSystem, Props}

class HelloActor(name:String) extends Actor {
  override def receive: Receive = {
    case "hello" => println(s"hello actor,$name")
    case _ => println(s"huh?,$name")
  }
}

object Main extends App {
    val system = ActorSystem("HelloSystem")
    //val helloActor = system.actorOf(Props[HelloActor],name="helloActor")
    val helloActor = system.actorOf(Props(new HelloActor("sweet")),name="helloActor")
    helloActor ! "hello"
    helloActor ! "buenos"
    system.terminate()
}
