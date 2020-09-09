package akkaStudy.unit9_logging

import akka.actor.{Actor, ActorLogging}

/**
  * 测试akka的Log功能
  *
  * @author chenwu on 2020.9.9
  */
class AkkaLogActor extends Actor with ActorLogging {

  override def postStop(): Unit = {
    log.info("I stop at {}", System.currentTimeMillis())
  }

  override def receive: Receive = {
    case msg: String => {
      log.info("I get message:{}", msg)
    }
  }
}
