package akkaStudy

import akka.actor.{Actor, ActorSystem, Props}
import akka.routing.RoundRobinPool

package object unit5_future_parallelize {

  case class ParseArticle(url: String)

  def getWorkRouter[T <: Actor](system: ActorSystem, clazz: Class[T]) = {
    //使用轮询的方式
    system.actorOf(Props.create(clazz).withRouter(new RoundRobinPool(8)))
  }
}
