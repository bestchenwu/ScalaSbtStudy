package akkaStudy.unit3_message_transfer

import java.net.URL

import akka.actor.Actor
import akka.actor.Status.Failure
import de.l3s.boilerpipe.extractors.ArticleExtractor
import de.l3s.boilerpipe.sax.{BoilerpipeSAXInput, HTMLDocument, HTMLFetcher}

/**
  * 从html里面提取出正文内容
  *
  * @author chenwu on 2020.9.2
  */
class ParseHmtlActor extends Actor {
  override def receive: Receive = {
    case ParseArticleHtml(url) => {
      val htmlDoc = HTMLFetcher.fetch(new URL(url))
      val doc = new BoilerpipeSAXInput(htmlDoc.toInputSource).getTextDocument
      val content = ArticleExtractor.INSTANCE.getText(doc);
      sender() ! ArticleBody(url, content)
    }
    case x => sender() ! Failure(new IllegalArgumentException(s"can't recongize x class:" + x.getClass))
  }
}
