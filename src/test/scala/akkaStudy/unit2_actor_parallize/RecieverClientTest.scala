package akkaStudy.unit2_actor_parallize

import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.must.Matchers

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class RecieverClientTest extends AnyFunSpecLike with Matchers {

  val client = new RecieverClient("127.0.0.1:2552")

  import scala.concurrent.ExecutionContext.Implicits.global

  describe("RecieverClient test") {
    it("should set key hello to value sweet") {
      client.set("hello", "sweet")
      val futureResult = client.get("hello").mapTo[String]
      val result = Await.result(futureResult, 1 seconds)
      result must equal("sweet")
    }
  }

  describe("RecieverClient test") {
    it("should reverse abc to cba") {
      val futureResult = client.reverse("abc").mapTo[String]
      val result = Await.result(futureResult, 1 seconds)
      result must equal("cba")
    }
  }

  describe("RecieverClient test") {
    it("reverse Integer(1) should throw IllegalArgumentException") {
      val futureResult = client.reverse(1)
      val errorFutureResult = futureResult.recover {
        case e: IllegalArgumentException => e.getMessage
      }
      val result = Await.result(errorFutureResult, 1 seconds)
      result must equal("unkown message:1")
    }
  }

  describe("RecieverClient test") {
    it("reverse List abc,ghi,def should get List cba,ihg,fed ") {
      val futureList = List("abc", "ghi", "def").map(item => client.reverse(item))
      val eventualList = Future.sequence(futureList).mapTo[List[String]]
      val result = Await.result(eventualList, 1 seconds)
      assert(result.size == 3)
      assert(result(0) == "cba")
      assert(result(1) == "ihg")
      assert(result(2) == "fed")
    }
  }
}
