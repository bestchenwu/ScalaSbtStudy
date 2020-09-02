package akkaStudy.unit3_message_transfer

import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.must.Matchers

import scala.concurrent.Await
import scala.concurrent.duration._

class AskArticleActorTest extends AnyFunSpecLike with Matchers {

  val askArticleActorClient: AskArticleActorClient = new AskArticleActorClient("127.0.0.1:2550")

  describe("test get pom from url and parse body") {
    it("should see the pom file"){
      //val url = "https://tool.oschina.net/apidocs/apidoc?api=jdk_7u4"
      val url = "https://blog.csdn.net/GarfieldEr007/article/details/50172195"
      val future = askArticleActorClient.getUrl(url).mapTo[String]
      val result = Await.result(future, 10 seconds)
      println(result)
    }

  }

}
