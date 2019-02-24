package FunctionProgramming.exception

import FunctionProgramming.exception.{Option => MyOption}

object OptionTest {

  def main(args: Array[String]): Unit = {
    //val some = Some(18)
    //    val result = some.map((x:Int)=>x+1)
    //    print(result)
    //val result = some.flatMap((x: Int) => Some(x * 2))
    //print(result)
//    val result = some.getOrElse(15)
//    val result = some.orElse(Some(15))
//    print(result)
//      val list = List(Some(13),Some(15),None)
//      print(Option.sequence(list))


    def tryToInt(a:String):Option[Int]={
      try{
        Some(a.toInt)
      }catch{
        case exception: Exception=>None
      }
    }

    val list = List("11","12","18")
    val result = Option.travese(list)(tryToInt)
    print(result)
  }
}
