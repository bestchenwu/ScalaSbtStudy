package akkaStudy.unit1_firstOneDemo

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.util.Timeout
import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.must.Matchers

import scala.concurrent.duration._

class AkkademyDbSpec extends AnyFunSpecLike with Matchers{

  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)

  describe("akkademyDb"){
      describe("given SetRequest"){
          it("should put key/value into map"){
              val actorRef = TestActorRef(new AkkademyDB)
              actorRef ! SetRequest("hello","world")
              actorRef ! SetRequest("hello1","world1")
              val akkademyDB = actorRef.underlyingActor
              akkademyDB.map.get("hello") must equal (Some("world"))
              akkademyDB.map.get("hello1") must equal (Some("world1"))
          }
      }
  }
}
