package akkaStudy.unit2_actor_parallize

import akka.actor.ActorSystem
import org.scalatest.matchers.must.Matchers
import org.scalatest.funspec.AnyFunSpecLike
import scala.concurrent.duration._

import scala.concurrent.Await

/**
  * akka future的链式调用方法
  */
class ScalaFutureChaiTest extends AnyFunSpecLike with Matchers {

  val system = ActorSystem()
  val pingActor = ScalaPongActor.createPongActor(system)

  import scala.concurrent.ExecutionContext.Implicits.global

  describe("test akka future flatMap") {
    it("send ping and get response pong") {
      val future = askMessage(pingActor, "ping").flatMap(x => askMessage(pingActor, x)).recover {
        case e: Exception => "get a exception:" + e.getMessage
      }
      val result = Await.result(future, 1 seconds)
      assert(result == "get a exception:unrecoginzed message")
    }
  }

  describe("test akka future combine") {
    it("send ping and ping ,then get ponepone") {
      val future = for (
        future1 <- askMessage(pingActor, "ping");
        future2 <- askMessage(pingActor, "ping")
      ) yield future1 + future2
      val message = Await.result(future, 1 seconds)
      assert(message == "ponepone")
    }
  }
}
