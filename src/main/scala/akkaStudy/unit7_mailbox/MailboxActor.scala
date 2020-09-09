package akkaStudy.unit7_mailbox

import akka.actor.Actor

/**
  * 测试邮箱Actor
  *
  * @author chenwu on 2020.9.9
  */
class MailboxActor extends Actor{

  override def receive: Receive = {
    case msg:String => {
      var result = msg.toUpperCase()
      println("I get msg:"+msg)
      sender() ! result
    }
  }
}
