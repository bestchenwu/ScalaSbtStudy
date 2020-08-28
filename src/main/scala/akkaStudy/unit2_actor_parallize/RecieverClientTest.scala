package akkaStudy.unit2_actor_parallize

import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.must.Matchers
import scala.concurrent.duration._
import scala.concurrent.Await

class RecieverClientTest extends AnyFunSpecLike with Matchers {

  val client = new RecieverClient("127.0.0.1:2552")

  import scala.concurrent.ExecutionContext.Implicits.global

  describe("RecieverClient") {
    it("should set key hello to value sweet") {
      client.set("hello", "sweet")
      //     client.get("hello").onComplete(result => result match {
      //        case scala.util.Success(value) => futureResult = value.toString
      //        case scala.util.Failure(exception) => futureResult = exception.getMessage
      //      })
      val futureResult = client.get("hello").mapTo[String]
      val result = Await.result(futureResult, 1 seconds)
      result must equal("sweet")
    }
  }
}
