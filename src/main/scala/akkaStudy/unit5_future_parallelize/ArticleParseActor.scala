package akkaStudy.unit5_future_parallelize

import java.net.URL

import akka.actor.Actor
import de.l3s.boilerpipe.extractors.ArticleExtractor
import de.l3s.boilerpipe.sax.{BoilerpipeSAXInput, HTMLFetcher}

class ArticleParseActor extends Actor {
  override def receive: Receive = {
    case ParseArticle(url) => {
      val body: String = ArticleParser(url)
      sender() ! body
    }
  }
}

object ArticleParser {

  def apply(url: String): String = {
    val htmlDoc = HTMLFetcher.fetch(new URL(url))
    val doc = new BoilerpipeSAXInput(htmlDoc.toInputSource).getTextDocument
    val content = ArticleExtractor.INSTANCE.getText(doc);
    val title = doc.getTitle
    title
  }
}



