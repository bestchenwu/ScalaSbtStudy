package akkaStudy.unit6_akka_cluster

object TransformationBackendMain {

  def main(args: Array[String]): Unit = {
    import akka.actor.ActorSystem
    import akka.actor.Props
    import com.typesafe.config.ConfigFactory
    val port = if (args.length > 0) args(0)
    else "0"
    val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).withFallback(ConfigFactory.parseString("akka.cluster.roles = [backend]")).withFallback(ConfigFactory.load("unit6/cluster"))

    val system = ActorSystem.create("ClusterSystem", config)

    system.actorOf(Props.create(classOf[TransformationBackend]), "backend")
  }
}
