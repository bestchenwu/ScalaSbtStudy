package akkaStudy.unit5_future_parallelize

import akka.actor.Actor

import scala.concurrent.ExecutionContext

class ClientActor extends Actor{

  val ec:ExecutionContext = context.system.dispatchers.lookup("blocking-io-dispatcher")

  override def receive: Receive = ???
}
