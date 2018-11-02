package unitThirteen

import akka.actor.{Actor, ActorSystem, Props}

case object NormalMessage
case object TrySolutionMessage
case object wrongMessage

class PersonActor extends Actor{

  import context._

  def normal:Receive={
    case NormalMessage =>{
      println("now I am normal")
      become(angry)
    }
  }

  def angry:Receive={
    case TrySolutionMessage =>{
      println("oh I should try to solute problem")
    }
    case wrongMessage =>{
      println("now I am wrong")
    become(normal)
    }
  }

  override def receive: Receive = {
    case NormalMessage => become(angry)
    case wrongMessage => become(normal)
  }
}
object BadPersonTest extends App{
//  val actorSystem = ActorSystem("badPersonTest");
//  val actor = actorSystem.actorOf(Props[PersonActor],"personActor");
//  //第一个消息是用于初始化状态,进入后就切换为只能接受angry类型的消息
//  actor ! NormalMessage
//  //如果再继续发该类型的消息，会表现为不接受
//  //actor ! NormalMessage
////  actor ! TrySolutionMessage
////  actor ! wrongMessage
////  actor ! NormalMessage
//  Thread.sleep(3000)
//  actorSystem.terminate()
}
