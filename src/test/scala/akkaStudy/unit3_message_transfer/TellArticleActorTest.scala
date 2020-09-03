package akkaStudy.unit3_message_transfer

import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.must.Matchers

import scala.concurrent.Await
import scala.concurrent.duration._

class TellArticleActorTest extends AnyFunSpecLike with Matchers {

  val tellArticleActorClient: TellArticleActorClient = new TellArticleActorClient("127.0.0.1:2660")

  describe("tell content from url and parse body") {
    it("should see get XX by url parse") {
      val url = "https://blog.csdn.net/GarfieldEr007/article/details/50172195"
      val future = tellArticleActorClient.getUrl(url).mapTo[String]
      val result = Await.result(future, 10 seconds)
      println(result)
    }
  }
}
