package akkaStudy.unit1_firstOneDemo

import akka.actor.Actor
import akka.event.Logging
import scala.collection.mutable

/**
  * Actor的接收器
  *
  * @author chenwu on 2020.8.26
  */
class AkkademyDB extends Actor {

  val map = new mutable.HashMap[String, Object]()

  val log = Logging(context.system, this)

  override def receive: Receive = {
    case SetRequest(key, value) => {
      log.info(s"received message {$key}->{$value}")
      map.put(key, value)
    }
    case other => log.info(s"received unkown message $other")
  }
}
