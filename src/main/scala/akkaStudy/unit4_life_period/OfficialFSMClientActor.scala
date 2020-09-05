package akkaStudy.unit4_life_period

import akka.actor.FSM
import akkaStudy.unit4_life_period.StateContainerTypes.RequestQueue


case object Flush

object StateContainerTypes {
  type RequestQueue = List[Request]
}

/**
  * 有限状态机(官方版本)
  *
  * @param address
  */
class OfficialFSMClientActor(address: String) extends FSM[State, RequestQueue] {
  private val remoteDb = context.system.actorSelection(address)

  startWith(Disconnected, List.empty[Request])

  when(Disconnected) {
    case Event(Connected, container: RequestQueue) => //If we get back a ping from db, change state
      if (container.headOption.isEmpty)
        goto(Connected)
      else
        goto(ConnectedAndPending)
    case Event(x: Request, container: RequestQueue) =>
      remoteDb ! Connected //Ping remote db to see if we're connected if not yet marked online.
      stay using (container :+ x) //Stash the msg
    case x =>
      println("uhh didn't quite get that: " + x)
      stay()
  }

  when(Connected) {
    case Event(x: Request, container: RequestQueue) =>
      goto(ConnectedAndPending) using (container :+ x)
    case other => {
      log.info("I am connected ,and I get result:" + other)
      stay()
    }
  }

  when(ConnectedAndPending) {
    case Event(Flush, container) =>
      remoteDb ! container;
      goto(Connected) using Nil
    case Event(x: Request, container: RequestQueue) =>
      stay using (container :+ x)
  }

  initialize()
}
