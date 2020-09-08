package akkaStudy.unit5_parallize

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.RoundRobinPool
import akkaStudy.unit5_future_parallelize.{ArticleParseActor, ParseArticle}
import akkaStudy.unit5_parallize.TestHelper.TestCamerActor
import com.typesafe.config.ConfigFactory
import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.must.Matchers
import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise}

class ArticleParseWithActorTest extends AnyFunSpecLike with Matchers {

  val system = ActorSystem.create("ArticleParse", ConfigFactory.load("unit5/patcher"))
  val workRouter: ActorRef = system.actorOf(Props.create(classOf[ArticleParseActor]).withDispatcher("my-dispatcher").withRouter(new RoundRobinPool(8)), "workerRouter")

  val url = "https://blog.csdn.net/GarfieldEr007/article/details/50172195"

  describe("work by router") {
    it("should work concurrently") {
      val p = Promise[String]()
      Future {
        p.success("ok")
      }(system.dispatcher)
      val cameoActor = system.actorOf(Props(new TestCamerActor(p)))

      (0 to 2000).foreach(x => {
        workRouter.tell(new ParseArticle(url), cameoActor)
      })
      TestHelper.profile(() => Await.ready(p.future, 10 seconds), "ArticleParseWithActor with router")
    }
  }

//  describe("work one by one"){
//      it("should work chainly"){
//        val p = Promise[String]()
//        Future {
//          p.success("ok")
//        }(system.dispatcher)
//        val cameoActor = system.actorOf(Props(new TestCamerActor(p)))
//        (0 to 2000 ) foreach(x=>{
//          cameoActor ! new ParseArticle(url)
//        })
        //耗时33毫秒
//        TestHelper.profile(() => {
//          Await.ready(p.future, 20 seconds)
//        }, "ArticleParseWithActor with chain")
//      }
//  }

}
