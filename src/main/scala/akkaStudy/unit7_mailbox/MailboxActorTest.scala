package akkaStudy.unit7_mailbox

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import akka.pattern._

object MailboxActorTest {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem.create("mySystem", ConfigFactory.load("unit7/mailbox"))
    //val myactorRef = system.actorOf(Props(classOf[MailboxActor]), "myactor")
    val myactorRef = system.actorOf(Props(classOf[MailboxActor]).withMailbox("akka.actor.nonblocking-mailbox"),"myActor")
    val future = myactorRef ? "haha"
    future.onComplete{
      case scala.util.Success(value) => println("mailbox return:"+value)
      case scala.util.Failure(exception) => println("mailbox throw:"+exception.getMessage)
    }(system.dispatcher)
  }
}
