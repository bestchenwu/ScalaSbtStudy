package akkaStudy.unit2_actor_parallize

import akka.actor.{ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

/**
  * 使用akka-remote
  * https://www.cnblogs.com/jiaan-geng/p/8876822.html
  */
object RecieverServer extends App {

  //默认加载application.conf
  val system = ActorSystem.create("akkademy",ConfigFactory.load("server"))
  //val system = ActorSystem.create("akkademy")
  val props = Props(classOf[RecieverActor])
  val actorRef: ActorRef = system.actorOf(props, "akkademy-db")

}
