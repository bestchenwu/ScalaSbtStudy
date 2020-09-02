package akkaStudy

import akka.util.Timeout

import scala.concurrent.duration._

package object unit3_message_transfer {

    implicit val timeout:Timeout = Timeout(60 seconds)

    //请求解析一个url对应的文章
    case class ParseArticle(url:String)
    //通过第三方插件解析htmlBody对应的content
    case class ParseArticleHtml(url:String)
    //通过httpClient请求url获取的内容
    case class HttpResponse(htmlBody:String)
    //最终的文章结果
    case class ArticleBody(url:String,body:String)
}
