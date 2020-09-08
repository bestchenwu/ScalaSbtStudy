package akkaStudy.unit5_parallize

import akka.actor.Actor

import scala.concurrent.Promise
import scala.util.Try

object TestHelper {

  class TestCamerActor(p: Promise[String]) extends Actor {

    var count = 0


    override def preStart(): Unit = {
      println("startTime:"+System.currentTimeMillis())
    }

    override def receive: Receive = {
      case _ => {
        count += 1
        if (count >= 2000)
          try{
            println(s"count=$count")
            println("endTime:"+System.currentTimeMillis())
            p.complete(Try("ok"))
          }catch{
            case e:Exception => println(e.getMessage)
          }
      }
    }
  }

  def profile(func: () => AnyRef, name: String = ""): Unit = {
    val startTime = System.currentTimeMillis()
    val res = func()
    val elapsedTime = System.currentTimeMillis() - startTime
    println(s"$name need $elapsedTime")
  }

}
