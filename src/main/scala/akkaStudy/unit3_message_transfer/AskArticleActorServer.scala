package akkaStudy.unit3_message_transfer

import akka.actor.{ActorRef, ActorSystem, Props}
import akkaStudy.unit2_actor_parallize.CacheActor
import com.typesafe.config.ConfigFactory

object AskArticleActorServer {

  def main(args: Array[String]): Unit = {
    //默认加载application.conf
    val remoteAddress = "127.0.0.1:2550"
    val system = ActorSystem.create("akkademy",ConfigFactory.load("unit3/AskArticle"))
    //val system = ActorSystem.create("akkademy")
    val cacheProps = Props(classOf[CacheActor])
    val cacheActor: ActorRef = system.actorOf(cacheProps, "akkademy-db-cache")
//    val httpProps = Props(classOf[HttpClientActor])
//    val httpClientActor: ActorRef = system.actorOf(httpProps, "akkademy-db-httpClient")
    val parseArticleProps = Props(classOf[ParseHmtlActor])
    val parseArticleActor: ActorRef = system.actorOf(parseArticleProps, "akkademy-db-parseArticle")
    //val askArticleProps = Props(classOf[AskArticleActor],s"akka://akkademy@$remoteAddress/user/akkademy-db-cache",s"akka://akkademy@$remoteAddress/user/akkademy-db-httpClient",s"akka://akkademy@$remoteAddress/user/akkademy-db-parseArticle")
    val askArticleProps = Props(classOf[AskArticleActor],s"akka://akkademy@$remoteAddress/user/akkademy-db-cache",s"akka://akkademy@$remoteAddress/user/akkademy-db-parseArticle")
    val askArticleActor : ActorRef = system.actorOf(askArticleProps, "akkademy-db-askArticle")

  }
}
