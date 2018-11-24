package implictTest

import akka.io.Tcp.Event


case class LoveEvent(var message:String)

//隐式转换
trait Lover{

  def sendLove(love:Love)={love.takeAction(love.loveEvent)}
}

trait Love{

  //val love = LoveEvent("Valetine is comming,dear to remoteLover")

  val loveEvent:LoveEvent

  //val show = takeAction(love)

  def takeAction(love:LoveEvent)

}

class RemoteLover extends Lover{


}

object LoverTest extends App{

  //funtion2love接受一个函数，该函数入参是LoveEvent
  //funtion2love然后返回Love对象
  implicit def funtion2love(f:LoveEvent=>Unit)={
      new Love {

        override val loveEvent: LoveEvent = LoveEvent("Valetine is comming,dear to remoteLover")

        override def takeAction(love: LoveEvent) = f(loveEvent)
      }
  }

  //期待可写成这样
  val remoteLover = new RemoteLover()
  //当编译器遇到类型不匹配的时候，它会去寻找能否将该类型转换的函数
  remoteLover.sendLove((event: LoveEvent)=>{println(event.message)})


//  remoteLover.sendLove(new Love {
//    override def takeAction(love: LoveEvent): Unit = {
//      println(love.message)
//    }
//  })
}
