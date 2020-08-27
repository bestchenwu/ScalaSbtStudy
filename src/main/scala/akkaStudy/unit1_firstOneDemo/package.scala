package akkaStudy

package object unit1_firstOneDemo {

  case class GetRequest(key:String)
  case class SetRequest(key:String,value:Object)
  case class KeyNotFoundException(key:String) extends Exception
}
