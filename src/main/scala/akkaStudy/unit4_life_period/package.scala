package akkaStudy

import akka.actor.ActorRef

package object unit4_life_period {

  trait Request

  case class SetRequest(key: String, value: Object,implicit val sender: ActorRef = ActorRef.noSender) extends Request
  case class GetRequest(key: String, implicit val sender: ActorRef = ActorRef.noSender) extends Request

  case class KeyNotFoundException(key: String) extends Exception

  val start_message = new Start()

  val Connected_message = new Connected()

  val DisConnected_message = new DisConnected()

  class Connected //Used as a ping

  class DisConnected

  class Start
}
