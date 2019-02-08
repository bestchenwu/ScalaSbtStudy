package unitThirteen

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

case object StartMessage
case object StopMessage
case object PingMessage
case object PoneMessage

class PingActor(pone:ActorRef) extends Actor{

  var count = 0;

  def increment={
    count+=1;
  }

  override def receive: Receive = {
    case StartMessage => {
      pone ! PingMessage
      //this.increment
    }
    case PoneMessage =>{
       if(count>2){
         println(s"pone count is $count,now stop")
        //结束ping pone信息传递
         sender ! StopMessage
         context.stop(self)
       }else{
          println("pone")
          this.increment
         pone ! PingMessage
       }
    }
    //case _=>println("PingActor can't understand:"+_)
  }
}

class PoneActor extends Actor{
  override def receive: Receive = {
    case StopMessage=>{
       println("now recieve Stop Message from PingActor")
    }
    case PingMessage =>{
      println("ping")
      sender ! PoneMessage
    }
    //case _=>println("PoneActor can't understand:"+_)
  }
}

object PingPongTest extends App{
    val system = ActorSystem("pingPoneSystem")
    val poneActor = system.actorOf(Props[PoneActor],"poneActor")
    val pingActor = system.actorOf(Props(new PingActor(pone = poneActor)),"pingActor")
    pingActor ! StartMessage
}
