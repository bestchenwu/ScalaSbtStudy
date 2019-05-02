package diveIntoScala.unit4_objectProgram

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

trait MessageDispatcher[-T] {

  def sendMessage(msg: T): Unit
}

class ActorDispatcher[-T](receiver: ActorRef) extends MessageDispatcher[T] {

  override def sendMessage(msg: T): Unit = {
    receiver ! msg
  }

}

object NullDispatcher extends MessageDispatcher[Any] {
  override def sendMessage(msg: Any): Unit = {}
}

object MyFactory {

  class MyActor extends Actor {
    override def receive: Receive = {
      case "hello" => println("hello")
      case _ => println("what is this")
    }
  }

  //这种模式下 方法实际上把ActorDispatcher暴露给了外部调用方,而实际应该把MessageDispatcher暴露给外部
  //  def createDispatcher[T](receiver: ActorRef) = {
  //    val system = ActorSystem("helloSystem")
  //    val actorPref = system.actorOf(Props[MyActor])
  //    new ActorDispatcher[T](actorPref)
  //  }

  /**
    * 这种方式就把MessageDispatcher暴露出去了
    *
    * @param reciever
    * @tparam T
    * @return
    */
  def createDispatcher[T](reciever: T) = reciever match {
    case actor: ActorRef => new ActorDispatcher[T](actor)
    case _ => NullDispatcher
  }
}
