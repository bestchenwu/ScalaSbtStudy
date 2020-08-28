package akkaStudy.unit2_actor_parallize

import akka.actor.ActorSystem
import akka.util.Timeout
import akkaStudy.unit1_firstOneDemo.{GetRequest, ReverseRequest, SetRequest}
import com.typesafe.config.ConfigFactory
import akka.pattern._

import scala.concurrent.duration._

/**
  * 客户端
  *
  * @param remoteAddress
  */
class RecieverClient(remoteAddress:String) {

    private implicit val timeout = Timeout(2 seconds)
    private implicit val system = ActorSystem.create("clientSystem",ConfigFactory.load("client"))
    private val remoteDb = system.actorSelection(s"akka://akkademy@$remoteAddress/user/akkademy-db")

    def set(key:String,value:Object): Unit ={
        remoteDb ? SetRequest(key,value)
    }

    def get(key:String) = {
        remoteDb ? GetRequest(key)
    }

    def reverse(value:Any) ={
        remoteDb ? ReverseRequest(value)
    }
}
