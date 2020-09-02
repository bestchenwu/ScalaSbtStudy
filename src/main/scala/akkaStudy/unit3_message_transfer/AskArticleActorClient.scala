package akkaStudy.unit3_message_transfer

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import akka.pattern._

class AskArticleActorClient(val remoteAddress: String) {

  val system = ActorSystem.create("askArticleClient", ConfigFactory.load("unit3/AskArticleClient"))
  val remoteDB = system.actorSelection(s"akka://akkademy@$remoteAddress/user/akkademy-db-askArticle")

  def getUrl(url: String) = {
    remoteDB ? url
  }

}
