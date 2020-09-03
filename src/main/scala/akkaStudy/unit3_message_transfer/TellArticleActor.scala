package akkaStudy.unit3_message_transfer

import java.util.concurrent.TimeoutException

import akka.actor.Status.Failure
import akka.actor.{Actor, ActorRef, Props}
import akkaStudy.unit1_firstOneDemo.{GetRequest, SetRequest}

/**
  * 使用Tell而非ask 模式有以下几种优点:
  * 1、少创建了Future，而使用了extraActor来作为中转，逻辑上也清晰了不少
  * 2、控制超时更方便，全局控制一次即可
  *
  * @param cacheActorPath
  * @param parseHtmlActorPath
  */
class TellArticleActor(val cacheActorPath: String, val parseHtmlActorPath: String) extends Actor {

  val cacheActor = context.actorSelection(cacheActorPath)
  val parseHtmlActor = context.actorSelection(parseHtmlActorPath)
  implicit val ec = context.dispatcher

  override def receive: Receive = {
    case ParseArticle(url) => {
      //sender()表示原始发送者
      val extraActor = buildExtraActor(sender(), url)
      cacheActor tell(GetRequest(url), extraActor)
      parseHtmlActor tell(ParseArticleHtml(url), extraActor)
      //对于每次消息处理都统一设置一个超时参数
      context.system.scheduler.scheduleOnce(timeout.duration, extraActor, "timeout")
    }
    case other => sender() ! Failure(new IllegalArgumentException("uncognized message:"+other.getClass))
  }

  private def buildExtraActor(senderRef: ActorRef, url: String) = {
    context.actorOf(Props(new Actor {
      override def receive: Receive = {
        case "timeout" => {
          senderRef ! Failure(new TimeoutException("timeout"))
          context.stop(self)
        }
        case value: String => {
          println(s"get $url from cache")
          senderRef ! value
          context.stop(self)
        }
        case ArticleBody(url, body) => {
          cacheActor ! SetRequest(url, body)
          senderRef ! body
          context.stop(self)
        }
        case t => println("ignore msg:" + t.getClass)
      }
    }))
  }
}
