package unitTwenty

import scala.util.Try

object NormalUsageMethod extends App{

//  def toInt(x:String):Option[Int]={
//    try{
//      Some(x.toInt)
//    }catch{
//      case e:Exception => None
//    }
//  }
//
//  toInt("haha") match{
//    case Some(some) => println(some)
//    case None => println("not string")
//  }
//
//  val value = toInt("35")
//  println(value.getOrElse(0))
//  value.foreach(i =>{
//      println(s"i=$i")
//  })

  def tryTest(x:String,y:String)={
     val z = for{
        a <- Try(x.toInt)
        b <- Try(y.toInt)
     } yield a*b
     z.getOrElse(0).intValue()
  }

  println(tryTest("test1","test2"))
  println(tryTest("35","3"))
}
