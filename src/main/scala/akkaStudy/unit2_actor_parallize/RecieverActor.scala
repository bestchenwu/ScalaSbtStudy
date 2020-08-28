package akkaStudy.unit2_actor_parallize

import akkaStudy.unit1_firstOneDemo._
import akka.actor.Actor
import akka.actor.Status._

class RecieverActor extends Actor {

  private val map = scala.collection.mutable.Map[String, Object]()

  override def receive: Receive = {

    case SetRequest(key, value) => {
      println(s"recivied {$key}->{$value}")
      map += ((key, value))
      sender() ! Success
    }
    case GetRequest(key) => {
      println(s"request to get {$key}")
      val value = map.get(key)
      value match {
        case Some(x) => sender() ! x
        case None => sender() ! Failure(KeyNotFoundException(key+" not found"))
      }
    }
    case other => Failure(new IllegalArgumentException("unkown message:" + other))
  }
}
