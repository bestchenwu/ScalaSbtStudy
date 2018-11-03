package unitFourteen

import java.io.IOException

import scala.annotation.varargs
import scala.collection.JavaConverters._

class Printer{
    @varargs
    def print(args : String*): Unit ={
        args.foreach(println)
    }
}

object JavaInteraction extends App{
      //抛出受检查的异常，让JAVA方法在编译阶段就进行处理
        @throws[IOException]
      def decode(): Unit ={
          throw new IllegalArgumentException("test")
      }
//
//      //以下是从scala的类转换成java类
//      val sum1 = CollectionUtil.countSum(seqAsJavaList(Seq(3,3,5)))
//      println("sum1="+sum1)
//以下是从java里的类转换成scala类
//  def nums = {
//    val list = new java.util.ArrayList[String]();
//    list.add("test1")
//    list.add("test2")
//    list
//  }
//  val list = asScalaBuffer(nums);
  //println(list.getClass)
  //现在转换成了class scala.collection.convert.Wrappers$JListWrapper
//  list.foreach(println(_))
  //list.forEach(println)
  //list.foreach(println(_))
  //这是jdk1.8的特性
  //nums.forEach(println(_));
  //nums.foreach(println(_))

}
