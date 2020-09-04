package akkaStudy.unit4_life_period

import akka.actor.{Actor, Stash}
import akka.event.Logging

/**
  * 具备热切换功能的client
  *
  * @param address
  * @author chenwu on 2020.9.4
  */
class HotSwapClientActor(val address: String) extends Actor with Stash {

  private val remoteDB = context.system.actorSelection(address)
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case x: Request => {
      log.info(s"get message $x,first to test db is connected")
      remoteDB ! Connected_message
      //将消息存入到队列中
      stash()
    }
    case x:Connected => {
      log.info("db now is connected,we can handle message")
      unstashAll()
      //这个方法将receive块中定义的方法修改为一个新的方法online
      context.become(online)
    }
    case x:DisConnected =>{
      log.warning("db now is disconnected,we can't handle message")
    }
    case result => log.info("I get some message:"+result)
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
    case _: DisConnected => {
      log.info("db is disconnected,now revert to the default behavior")
      remoteDB ! DisConnected_message
      context.unbecome()
    }
    case result => log.info("I get the result:"+result)
  }
}
