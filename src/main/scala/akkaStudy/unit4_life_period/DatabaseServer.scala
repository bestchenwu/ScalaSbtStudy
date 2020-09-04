package akkaStudy.unit4_life_period

import akka.actor.Status.{Failure, Success}
import akka.actor.{Actor, ActorRef}

import scala.collection.mutable.{Map => MutableMap}
import akka.event.Logging
import akka.io.UdpConnected.Disconnect

import scala.collection.script.Start

/**
  * 本地缓存数据库
  *
  * @author chenwu on 2020.9.4
  */
class DatabaseServer extends Actor {

  val map = MutableMap[String, Object]()
  val log = Logging(context.system, this)

  override def receive: Receive = {

    case x: Start => {
      log.info("I am start")
      val ref = sender()
      if(ref!=self){
        sender() ! x
      }
      context.become(online)

      def online(): Receive = {
        case x:Connected => {
          log.info("yes,I am connected")
          sender() ! Connected_message
        }
        case list: List[_] => list foreach {
          case SetRequest(key, value, sender) => handleSetRequest(key, value, sender)
          case GetRequest(key, sender) => handleGetRequest(key, sender)
          case other => sender() ! Failure(new IllegalArgumentException("unkown message:" + other))
        }
        case SetRequest(key, value,sender) => handleSetRequest(key, value, sender)
        case GetRequest(key, sender) => handleGetRequest(key, sender)
        case x: DisConnected => {
          log.warning("get disconnected message,now swap to disconnected")
          context.unbecome()
          sender() ! DisConnected_message
        }
        case other => sender() ! Failure(new IllegalArgumentException("unkown message:"+other))
      }
    }

    case other => sender() ! "please make db server start firstly,send message {start_message} to make it start"

  }

  def handleSetRequest(key: String, value: Object, sender: ActorRef): Unit = {
    log.info(s"received set $key -> $value ")
    map += ((key, value))
    sender ! Success
  }

  def handleGetRequest(key: String, sender: ActorRef) = {
    log.info(s"received get $key ")
    val result = map.get(key)
    result match {
      case Some(value) => {
          log.info(s"find $value by $key")
          sender ! value
      }
      case None => sender ! new KeyNotFoundException(s"$key not found")
    }


  }

  override def preStart(): Unit = {
    log.info("prestart ,now send self start message")
    self ! start_message
  }
}
