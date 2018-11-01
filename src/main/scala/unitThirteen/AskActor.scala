package unitThirteen

import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps
import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await

case object AskMessage

class TestActor extends Actor {
  override def receive: Receive = {
    case AskMessage => sender ! "fred"
    case anyObject => println(s"I get $anyObject")
  }
}

object AskActor extends App {

  val system = ActorSystem("testSystem")
  val myActor = system.actorOf(Props[TestActor], "myActor")
  implicit val timeOut = Timeout(5 seconds)
  //给myActor发送消息，并阻塞式等待响应
//  val future = myActor ? AskMessage
//  val result = Await.result(future, timeOut.duration).asInstanceOf[String]
//  println(result)
  //这是另外一种阻塞式等待消息的方式
  val future2 = ask(myActor,AskMessage)(timeOut).mapTo[String]
  val result2 = Await.result(future2,timeOut.duration)
  println(result2)
}
