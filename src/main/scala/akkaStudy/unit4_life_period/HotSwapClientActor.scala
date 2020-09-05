package akkaStudy.unit4_life_period

import java.util.{Timer, TimerTask}

import akka.actor.SupervisorStrategy.Restart
import akka.actor.{Actor, ActorIdentity, AllForOneStrategy, Identify, Stash, SupervisorStrategy}
import akka.event.Logging
import akka.pattern._

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * 具备热切换功能的client
  *
  * @param address
  * @author chenwu on 2020.9.4
  */
class HotSwapClientActor(val address: String) extends Actor with Stash {

  private val remoteDB = context.system.actorSelection(address)
  val log = Logging(context.system, this)




  /**
    * https://blog.csdn.net/sunquan291/article/details/78894786
    * 参考这个
    *
    * @return
    */
  override def supervisorStrategy: SupervisorStrategy = {
    AllForOneStrategy(2, 3 second) {
      case _:Exception => SupervisorStrategy.restart
    }
  }


  override def preStart(): Unit = {
      val timer = new Timer()
      timer.schedule(new TimerTask {

        var count = 0;

        override def run(): Unit = {
          val identity = Identify(1)
          val future = remoteDB ? identity
          val result = Await.result(future.mapTo[ActorIdentity],10 seconds)
          val actorRef = result.getActorRef
          val correlationId = result.correlationId
          if(actorRef == null || correlationId!=1){
            count+=1;
          }
          if(count>=2){
            throw new IllegalArgumentException("db server failure count is more than 2")
          }
        }
      },200,10000);
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
      log.info("HotSwapClientActor pre restart because of :"+reason.getMessage)
  }


  override def postRestart(reason: Throwable): Unit = {
    log.info("HotSwapClientActor post restart because of :"+reason.getMessage)
  }

  override def receive: Receive = {
    case x: Request => {
      log.info(s"get message $x,first to test db is connected")
      remoteDB ! Connected
      //将消息存入到队列中
      stash()
    }
    case Connected => {
      log.info("db now is connected,we can handle message")
      unstashAll()
      //这个方法将receive块中定义的方法修改为一个新的方法online
      context.become(online)
    }
    case Disconnected => {
      log.warning("db now is disconnected,we can't handle message")
    }
    case result => log.info("I get some message:" + result)
  }

  /**
    * online返回的是Receive的偏函数(接受一个任意参数返回unit的函数)
    *
    * @return
    * @author chenwu on 2020.9.4
    */
  private def online(): Receive = {

    case x: Request => remoteDB forward (x)
    //修改为默认的行为
    case Disconnected => {
      log.info("db is disconnected,now revert to the default behavior")
      remoteDB ! Disconnected
      context.unbecome()
    }
    case result => log.info("I get the result:" + result)
  }
}
