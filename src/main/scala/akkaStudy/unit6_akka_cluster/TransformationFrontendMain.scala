package akkaStudy.unit6_akka_cluster

import java.util.concurrent.TimeUnit

import akka.cluster.client.ClusterClientReceptionist
import akka.pattern.Patterns._
import akka.util.Timeout

object TransformationFrontendMain {

  def main(args: Array[String]): Unit = {
    import akka.actor.ActorSystem
    import akka.actor.Props
    import com.typesafe.config.ConfigFactory
    import scala.concurrent.duration.Duration
    import java.util.concurrent.atomic.AtomicInteger
    val port = if (args.length > 0) args(0)
    else "0"
    val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).withFallback(ConfigFactory.parseString("akka.cluster.roles = [frontend]")).withFallback(ConfigFactory.load("unit6/cluster"))

    val system = ActorSystem.create("ClusterSystem", config)

    val frontend = system.actorOf(Props.create(classOf[TransformationFrontend]), "frontend")
    ClusterClientReceptionist(system).registerService(frontend)
    val interval = Duration.create(2, TimeUnit.SECONDS)
    val timeout = new Timeout(Duration.create(5, TimeUnit.SECONDS))
    val ec = system.dispatcher
    val counter = new AtomicInteger

    system.scheduler.schedule(interval, interval, new Runnable {
      override def run(): Unit = {
        val url = "https://blog.csdn.net/GarfieldEr007/article/details/50172195"
        val future = ask(frontend, url, timeout).mapTo[String]
        future.onComplete {
          case scala.util.Success(value) => {
            println(s"get result $value from $url")
          }
          case scala.util.Failure(exception) => {
            println(s"get exception ${exception.getMessage} from $url")
          }
        }(ec)
      }
    })(system.dispatcher)

  }
}
