package unitNighteen

import scala.collection.mutable

class StrangeIntQueue extends mutable.Queue[Int]{
   override
   def appendElem(x:Int): Unit ={
      println(Math.sqrt(x))
      super.appendElem(x)
    }
}
object StrangeIntQueueTest {
//    val queue:mutable.Queue[Any] = new StrangeIntQueue[]();
//    queue.appendElem("test")
}
