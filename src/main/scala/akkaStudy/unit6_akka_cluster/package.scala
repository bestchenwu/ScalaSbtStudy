package akkaStudy

import java.net.URL

import akka.util.Timeout
import de.l3s.boilerpipe.extractors.ArticleExtractor
import de.l3s.boilerpipe.sax.{BoilerpipeSAXInput, HTMLFetcher}
import scala.concurrent.duration._
package object unit6_akka_cluster {

  case class ParseArticle(htmlString: String)
  case object BackendRegistration // 后台具体执行任务节点注册事件
  implicit val timeout:Timeout = Timeout(10 seconds)
  object ArticleParser {

    def apply(url: String) = {
      val htmlDoc = HTMLFetcher.fetch(new URL(url))
      val doc = new BoilerpipeSAXInput(htmlDoc.toInputSource).getTextDocument
      val content = ArticleExtractor.INSTANCE.getText(doc);
      val title = doc.getTitle
      title
    }
  }
}
