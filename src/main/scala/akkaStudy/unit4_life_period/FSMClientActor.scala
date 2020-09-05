package akkaStudy.unit4_life_period

import akka.actor.FSM
import akkaStudy.unit4_life_period.StateRequestQueue.MessageRequestQueue
import akka.event.Logging

object StateRequestQueue {
  type MessageRequestQueue = List[Request]
}


/**
  * 有限状态机(可以实现消息批量发送)
  *
  * @param address
  * @author chenwu on 2020.9.5
  */
class FSMClientActor(val address: String) extends FSM[State, MessageRequestQueue] {

  val remoteDB = context.system.actorSelection(address)
  //val log = Logging(context.system,this)
  startWith(Disconnected, List[Request]())


  when(Disconnected) {

    case Event(Connected, container: MessageRequestQueue) => {
      log.info("I am Disconnected,FSMClientActor get Connected message")
      if (container.isEmpty) {
        log.info("I am Disconnected,container is empty,I will go to Connected")
        goto(Connected)
      } else {
        log.info("I am Disconnected,container is not empty,I will go to ConnectedAndPending")
        goto(ConnectedAndPending)
      }
    }
    case Event(x: Request, container: MessageRequestQueue) => {
      log.info(s"FSMClientActor get request:{$x}")
      remoteDB ! Connected
      stay using (container :+ x)
    }
    case other => {
      log.info("uhh I get something but not useful" + other)
      stay()
    }
  }

  when(Connected) {

    case Event(x: Request, container: MessageRequestQueue) => {
      log.info(s"I am Connected , now get new message:$x,then will go to $ConnectedAndPending")
      goto(ConnectedAndPending) using (container :+ x)
    }
    case other => {
      log.info(s"I am connected,I get result:$other")
      stay()
    }
  }

  when(ConnectedAndPending) {
    case Event(Flush, container: MessageRequestQueue) => {
      log.info("I am ConnectedAndPending , and I get flush command")
      remoteDB ! container
      goto(Connected) using (Nil)
    }

    case Event(x: Request, container: MessageRequestQueue) => {
      log.info(s"I am ConnectedAndPending,and add message {$x}")
      stay using (container :+ x)
    }
    case other => {
      log.info(s"I am ConnectedAndPending,I get result:$other")
      stay()
    }
  }

  initialize()
}
