package akkaStudy.unit3_message_transfer

import akka.actor.Actor
import akka.actor.Status.Failure
import akka.util.Timeout
import akkaStudy.unit1_firstOneDemo.{GetRequest, KeyNotFoundException, SetRequest}
import akka.pattern._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * 请求一个url ,获取url对应的文章内容<br/>
  * 先从cache里取，如果cache里取不到,就根据url取得htmlBody再解析正文
  *
  * @param cacheActorPath
  * @param httpClientActorPath
  * @param parseHtmlActorPath
  * @param timeout
  * @author chenwu on 2020.9.2
  */
class AskArticleActor(val cacheActorPath: String, val parseHtmlActorPath: String) extends Actor {

  val cacheActor = context.actorSelection(cacheActorPath)
  //val httpClientActor = context.actorSelection(httpClientActorPath)
  val parseHtmlActor = context.actorSelection(parseHtmlActorPath)

  override def receive: Receive = {

    case url: String => {
      val senderRef = sender()
      val future = cacheActor ? GetRequest(url)
      val result = future.recoverWith {
        case e: KeyNotFoundException => {
          val httpResponseFuture = parseHtmlActor ? ParseArticleHtml(url)
          httpResponseFuture
        }
      }
      result.onComplete {
        case scala.util.Success(value:String) => {
          println(s"find $url from cache ")
          senderRef ! value
        }
        case scala.util.Success(ArticleBody(url, body)) => {
          println(s"find $url from parse ")
          cacheActor ! SetRequest(url,body)
          senderRef ! body
        }
        case util.Failure(exception) => senderRef ! Failure(exception)
        case x => senderRef ! Failure(new IllegalArgumentException("unkonwn message:" + x))
      }
    }
    case other => sender() ! Failure(new IllegalArgumentException("can't recoginzed other class:" + other.getClass))
  }
}
