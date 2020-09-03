package akkaStudy.unit3_message_transfer

import akka.actor.{ActorRef, ActorSystem, Props}
import akkaStudy.unit2_actor_parallize.CacheActor
import com.typesafe.config.ConfigFactory

object TellArticleActorServer {

  def main(args: Array[String]): Unit = {
    val remoteAddress = "127.0.0.1:2660"
    val system = ActorSystem.create("akkademy", ConfigFactory.load("unit3/TellArticle"))
    val cacheProps = Props(classOf[CacheActor])
    val cacheActor: ActorRef = system.actorOf(cacheProps, "akkademy-db-cache")

    val parseArticleProps = Props(classOf[ParseHmtlActor])
    val parseArticleActor: ActorRef = system.actorOf(parseArticleProps, "akkademy-db-parseArticle")
    val tellArticleProps = Props(classOf[TellArticleActor], s"akka://akkademy@$remoteAddress/user/akkademy-db-cache", s"akka://akkademy@$remoteAddress/user/akkademy-db-parseArticle")
    val tellArticleActor = system.actorOf(tellArticleProps, "akkademy-db-tellArticle")
  }
}
