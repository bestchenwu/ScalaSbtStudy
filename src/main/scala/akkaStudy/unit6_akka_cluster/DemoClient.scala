package akkaStudy.unit6_akka_cluster

import akka.actor.{ActorSystem, Props}

/**
  * https://segmentfault.com/a/1190000011098236
  *
  * https://github.com/54chen/akka_cluster_learn/blob/master/src/main/java/sample/cluster/transformation/TransformationBackend.java
  */
object DemoClient {

  def main(args: Array[String]): Unit = {
    //启动集群客户端
    TransformationFrontendMain.main(Array("2551"))
    //启动服务端
    TransformationBackendMain.main(Array("8001"))
    TransformationBackendMain.main(Array("8002"))
    TransformationBackendMain.main(Array("8003"))

    val system = ActorSystem("otherSystem")

    val clientJobTransformationSendingActor =
      system.actorOf(Props[ClientJobTransformationSendingActor],
        name = "clientJobTransformationSendingActor")
    val url = "https://blog.csdn.net/GarfieldEr007/article/details/50172195"
    import scala.concurrent.duration._
    import system.dispatcher
    system.scheduler.schedule(2 seconds, 2 seconds) {
      clientJobTransformationSendingActor ! url
    }

  }
}
