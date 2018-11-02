package unitThirteen

import scala.collection.parallel.immutable.ParVector

/**
  * 并发集合
  */
object ParVectorTest extends App{

  val vector = ParVector.range(0,10)
  vector.foreach{e => Thread.sleep(10);print(e)};
}
