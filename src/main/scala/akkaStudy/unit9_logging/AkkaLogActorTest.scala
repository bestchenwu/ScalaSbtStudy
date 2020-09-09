package akkaStudy.unit9_logging

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object AkkaLogActorTest {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem.create("akkaLogSystem", ConfigFactory.load("unit9/logback"))
    val akkaLogRef = system.actorOf(Props(classOf[AkkaLogActor]), "akkaLog")
    Thread.sleep(2000)
    system.stop(akkaLogRef)
  }
}
