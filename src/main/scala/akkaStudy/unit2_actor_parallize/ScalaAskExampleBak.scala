package akkaStudy.unit2_actor_parallize

import akka.actor.ActorSystem
import akka.util.Timeout
import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.must.Matchers
import akka.pattern._

import scala.concurrent.duration._
import scala.concurrent.Await

class ScalaAskExampleBak extends AnyFunSpecLike with Matchers {

  val system = ActorSystem();
  implicit val timeout = Timeout(5 seconds)
  val pongActor = ScalaPongActor.createPongActor(system, actorName = "pongActor")

  describe("pongActor") {
    it("it should response with pone") {
      //? 是首先通过AskSupport的
      //implicit def ask(actorRef: ActorRef): AskableActorRef = new AskableActorRef(actorRef)方法构建一个AskableActorRef
      //然后调用她的?方法
      val future = pongActor ? "ping"
      val result = Await.result(future.mapTo[String], 1 seconds)
      assert(result == "pone")
      //val future = pongActor ? "pone"
    }
    it("it should response with exception") {
      val future = pongActor ? "nothing"
      intercept[Exception] {
        Await.result(future.mapTo[String], 1 seconds)
      }
    }
  }

  describe("Future example") {
    import scala.concurrent.ExecutionContext.Implicits.global
    it("should print on console") {
      (pongActor ? "ping").onComplete(message => message match {
        case scala.util.Success(value) => println("replied with " + value)
        case scala.util.Failure(exception) => exception.printStackTrace()
      })
      Thread.sleep(100)
    }
    it("should get a exception") {
      //尝试从失败中恢复,获取一个正常的结果
      val messageFuture = (pongActor ? "nothing").recover {
        case e: Exception => "get a exception"
      }.mapTo[String]
      val message = Await.result(messageFuture, 1 seconds)
      assert(message == "get a exception")
      Thread.sleep(100)
    }
  }

}
