package akkaStudy

package object unit1_firstOneDemo {

  /**
    * 请求获取指定key的值
    *
    * @param key
    */
  case class GetRequest(key:String)

  /**
    * 请求给key赋值value
    *
    * @param key
    * @param value
    */
  case class SetRequest(key:String,value:Object)

  /**
    * 抛出找不到key的存在
    *
    * @param key
    */
  case class KeyNotFoundException(key:String) extends Exception

  /**
    * 表示成功获取key
    *
    * @param value
    */
  case class SuccessGetResponse(value:Object)

  /**
    * 表示成功set key value
    *
    * @param key
    * @param value
    */
  case class SuccessSetResponse(key:String,value:Object)

  /**
    * 不识别的错误
    *
    * @param other
    */
  case class UnkownException(other:Any) extends Exception
}
