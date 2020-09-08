package akkaStudy.unit6_akka_cluster

import akka.actor.{Actor, ActorPath}
import akka.cluster.client.{ClusterClient, ClusterClientSettings}
import akka.pattern.Patterns
import akka.util.Timeout

class ClientJobTransformationSendingActor extends Actor {

  val initialContacts = Set(
    ActorPath.fromString("akka.tcp://ClusterSystem@127.0.0.1:2551/system/receptionist"))
  val settings = ClusterClientSettings(context.system)
    .withInitialContacts(initialContacts)

  val c = context.system.actorOf(ClusterClient.props(settings), "demo-client")


  def receive = {
    case url: String => {
      import scala.concurrent.duration._
      implicit val timeout = Timeout(5 seconds)
      val result = Patterns.ask(c, ClusterClient.Send("/user/frontend", url, false), timeout)
      result.onComplete {
        case scala.util.Success(value) => println(s"get $value")
        case scala.util.Failure(exception) => println(s"get exception ${exception.getLocalizedMessage}")
      }(context.dispatcher)
    }
  }

}
