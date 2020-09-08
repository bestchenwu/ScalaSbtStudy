package akkaStudy.unit6_akka_cluster

import java.net.URL

import akka.actor.{Actor, ActorRef, Terminated}
import de.l3s.boilerpipe.extractors.ArticleExtractor
import de.l3s.boilerpipe.sax.{BoilerpipeSAXInput, HTMLFetcher}
import akka.pattern._

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class TransformationFrontend extends Actor {
  val backends = ArrayBuffer[ActorRef]() //任务后台节点列表

  override def receive: Receive = {
    case url: String => {
      if(backends.isEmpty){
        println("url backends is empty")
      }else{
        backends.foreach(actor=>{
          println("url backend:"+actor.path)
        })
      }

      println(s"url size=${backends.size}")
      if(!backends.isEmpty){
        val index = Random.nextInt(backends.size)
        val backend = backends(index)
        println(s"backend=$backend and get url:$url")
        import context.dispatcher
        val future = (backend ? url).map(x => x.asInstanceOf[String])
        future pipeTo sender()
      }else{
          sender() ! "error"
      }


    }
    // 添加新的后台任务节点

    case BackendRegistration => {
      if(backends.isEmpty){
        println("BackendRegistration backends is empty")
      }else{
        backends.foreach(actor=>{
          println("BackendRegistration backend:"+actor.path)
        })
      }
      //监控相应的任务节点
      context watch sender()
      backends.append(sender())
      println(s"backends size=${backends.size}")
    }
    // 移除已经终止运行的节点
    case Terminated(a) => backends.dropWhile(_ == a)
    case other => println(s"msg:" + other)
  }
}


