package akkaStudy.unit3_message_transfer

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import akka.pattern._

class TellArticleActorClient(val remoteAddress: String) {

  val system = ActorSystem.create("akkademy-client", ConfigFactory.load("unit3/TellArticleClient"))
  val remoteDB = system.actorSelection(s"akka://akkademy@$remoteAddress/user/akkademy-db-tellArticle")

  def getUrl(url: String) = {
    remoteDB ? ParseArticle(url)
  }

}
