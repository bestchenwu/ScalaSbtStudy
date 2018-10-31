package unitThirteen

import akka.actor.{Actor, ActorSystem, PoisonPill, Props, Terminated}

class Kenny extends Actor{
  override def receive: Receive = {
    case anyObject=>println(s"Kenny get $anyObject")
  }
}

class ParentActor extends Actor{
  //创建一个子actor
  val kenny = context.actorOf(Props[Kenny],"Kenny")
  //监听
  context.watch(kenny)

  override def receive: Receive = {
    case Terminated(`kenny`)=>{
        println("kenny has been killed")
    }
    case anyObject => println(s"Parent get $anyObject")
  }
}

object WatchTest extends App{
  val actorSystem = ActorSystem("watch");
  val parent = actorSystem.actorOf(Props[ParentActor],"parent");
  val kenny = actorSystem.actorSelection("/user/parent/Kenny")
  kenny ! PoisonPill
  Thread.sleep(1000)
  actorSystem.terminate()
}
