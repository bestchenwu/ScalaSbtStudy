package akkaStudy.unit4_life_period

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.pattern._
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.must.Matchers

import scala.concurrent.Await
import scala.concurrent.duration._


class HotSwapClientActorTest extends AnyFunSpecLike with Matchers {

  implicit val timeout = Timeout(5 seconds)
  //implicit val system = ActorSystem()
  val system = ActorSystem.create("akkademy", ConfigFactory.load("unit4/HotSwapClient"))
  val akkademyDbServer: ActorRef = system.actorOf(Props[DatabaseServer], "akkademy-db")
  val hotSwapClientProps = Props(classOf[HotSwapClientActor], akkademyDbServer.path.toString)
  val hotSwapClient = system.actorOf(hotSwapClientProps)

  describe("test server client hot swap") {
    it("test get set something ,then disconnect db,then get set something,finnaly make db connected") {
      Thread.sleep(2000)
      hotSwapClient ! SetRequest("key1", "value1", hotSwapClient)
      Thread.sleep(2000)
      hotSwapClient ! DisConnected_message
      Thread.sleep(2000)
      hotSwapClient ! SetRequest("key2", "value2", hotSwapClient)
//      hotSwapClient ! GetRequest("key1", hotSwapClient)
//      hotSwapClient ! GetRequest("key2", hotSwapClient)
//      //给server 发一条disconnect的消息
//      akkademyDbServer ! DisConnected_message
//      Thread.sleep(2000)
//      hotSwapClient ! SetRequest("key3", "value3", hotSwapClient)
    }

  }
}
