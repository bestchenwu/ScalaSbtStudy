package akkaStudy.unit4_life_period

import akka.actor.FSM.Event
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.must.Matchers

import scala.concurrent.duration._

class FSMClientActorTest extends AnyFunSpecLike with Matchers {

  implicit val timeout = Timeout(5 seconds)
  val system = ActorSystem.create("akkademy", ConfigFactory.load("unit4/FSMlient"))
  val akkademyDbServer: ActorRef = system.actorOf(Props[DatabaseServer], "akkademy-db")
  val fsmClientProps = Props(classOf[FSMClientActor], akkademyDbServer.path.toString)
  val fsmClient = system.actorOf(fsmClientProps,"fsmClient")

  describe("test fsm client") {
    it("add message multily,then flush all") {
      val messageQueue = List[Request]()
      fsmClient ! SetRequest("key1","value1",fsmClient)
      fsmClient ! SetRequest("key2","value2",fsmClient)
      Thread.sleep(1000)
      //给fsmClient发送链接信息
      fsmClient ! Connected
      fsmClient ! GetRequest("key1",fsmClient)
      fsmClient ! GetRequest("key2",fsmClient)
      Thread.sleep(1000)
      fsmClient ! Flush
    }
  }
}
