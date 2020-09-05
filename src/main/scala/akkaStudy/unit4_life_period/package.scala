package akkaStudy

import akka.actor.ActorRef
import akka.util.Timeout
import scala.concurrent.duration._

package object unit4_life_period {

  implicit val timeout:Timeout = Timeout(1 seconds)
  trait Request

  case class SetRequest(key: String, value: Object,implicit val sender: ActorRef = ActorRef.noSender) extends Request
  case class GetRequest(key: String, implicit val sender: ActorRef = ActorRef.noSender) extends Request

  case class KeyNotFoundException(key: String) extends Exception


  //所有的状态定义类
  sealed trait State
  case object Disconnected extends State //dbSever不可链接
  case object Connected extends State //dbSever连接上,但消息队列为空
  case object ConnectedAndPending extends State //dbSever连接上，但消息队列不为空
  case object Start extends State //启动消息
}
