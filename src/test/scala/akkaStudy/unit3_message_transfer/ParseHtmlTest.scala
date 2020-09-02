package akkaStudy.unit3_message_transfer

import java.net.URL
import de.l3s.boilerpipe.extractors.ArticleExtractor
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput
import de.l3s.boilerpipe.sax.HTMLFetcher
import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.must.Matchers

class ParseHtmlTest extends AnyFunSpecLike with Matchers{

    describe("test parse html"){
        it("test parse https://blog.csdn.net/GarfieldEr007/article/details/50172195"){
            //val url = "https://blog.csdn.net/GarfieldEr007/article/details/50172195"
          val url = "https://tool.oschina.net/apidocs/apidoc?api=jdk_7u4"
          val htmlDoc = HTMLFetcher.fetch(new URL(url))
          val doc = new BoilerpipeSAXInput(htmlDoc.toInputSource).getTextDocument
          val title = doc.getTitle
          val content = ArticleExtractor.INSTANCE.getText(doc)
          println(s"title=$title")
          println(s"content=$content")
        }

    }

}
