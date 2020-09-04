package akkaStudy.unit4_life_period

import akka.actor.FSM
import akkaStudy.unit4_life_period
import akkaStudy.unit4_life_period.StateContainerTypes.RequestQueue
import akkaStudy.unit4_life_period._

sealed trait State
case object Disconnected extends State
case object Connected extends State
case object ConnectedAndPending extends State

case object Flush
case object ConnectedMsg

object StateContainerTypes {
  type RequestQueue = List[Request]
}

/**
  * 有限状态机
  *
  * @param address
  */
class FSMClientActor(address: String) extends FSM[State, RequestQueue]{
  private val remoteDb = context.system.actorSelection(address)

  startWith(Disconnected, List.empty[Request])

  when(Disconnected){
    case Event(_: Connected, container: RequestQueue) => //If we get back a ping from db, change state
      if (container.headOption.isEmpty)
        goto(Connected)
      else
        goto(ConnectedAndPending)
    case Event(x: Request, container: RequestQueue) =>
      remoteDb ! Connected_message //Ping remote db to see if we're connected if not yet marked online.
      stay using (container :+ x) //Stash the msg
    case x =>
      println("uhh didn't quite get that: " + x)
      stay()
  }

  when (Connected) {
    case Event(x: Request, container: RequestQueue) =>
      goto(ConnectedAndPending) using(container :+ x)
  }

  when (ConnectedAndPending) {
    case Event(Flush, container) =>
      remoteDb ! container;
      goto(Connected) using Nil
    case Event(x: Request, container: RequestQueue) =>
      stay using(container :+ x)
  }

  initialize()
}
