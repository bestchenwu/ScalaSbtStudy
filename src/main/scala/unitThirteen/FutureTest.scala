package unitThirteen

import scala.concurrent.{Await, Future}
import scala.language.postfixOps
import scala.concurrent.duration._
//这是创建一个引入线程池的最简单方式
import scala.concurrent.ExecutionContext.Implicits.global

object FutureTest extends App{

  implicit val baseTime = System.currentTimeMillis()
  var i = 1

   val f = Future{
      Thread.sleep(500)
      i+1
   }
    //阻塞式调用
   //val result = Await.result(f,1 seconds)
   //非阻塞式调用
//   val result = f.onComplete{
//     case scala.util.Success(value)=>println("result="+value)
//     case scala.util.Failure(e)=>e.printStackTrace()
//   }
   //onComplete等同于onSuccess和onFailure的揉合体

  Thread.sleep(1000)
   //println("result="+result)

}
