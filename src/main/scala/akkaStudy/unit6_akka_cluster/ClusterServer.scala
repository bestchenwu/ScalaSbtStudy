package akkaStudy.unit6_akka_cluster

import akka.actor.{ActorSystem, Props}
import akka.cluster.client.ClusterClientReceptionist
import akka.routing.BalancingPool
import com.typesafe.config.ConfigFactory

object ClusterServer {

  def main(args: Array[String]): Unit = {
//    val system = ActorSystem.create("Akkademy", ConfigFactory.load("unit6/cluster"))
//    val cluster = system.actorOf(Props(classOf[TransformationBackend]), "cluster")
//    val workerPool = system.actorOf(BalancingPool(8).props(Props.create(classOf[ArticleParseActor])), "workers")
//    ClusterClientReceptionist(system).registerService(workerPool)
  }
}
