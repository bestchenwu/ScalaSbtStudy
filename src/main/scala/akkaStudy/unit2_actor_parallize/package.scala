package akkaStudy

import akka.actor.{ActorRef}
import akka.pattern._
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Future

/**
  * 包级默认方法
  *
  * @author
  */
package object unit2_actor_parallize {
  /**
    * 给一个actor发送消息,默认延时5秒
    *
    * @param actor
    * @param message 获取响应
    * @return Future[String]
    * @author chenwu on 2020.8.27
    */
  def askMessage(actor: ActorRef, message: String)(implicit timeout: Timeout = Timeout(5 seconds)): Future[String] = (actor ? message).mapTo[String]
}
