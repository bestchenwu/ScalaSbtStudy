package diveIntoScala.unit6_type_system

import scala.collection.mutable.HashMap

/**
  * 观察者模式
  *
  * @author chenwu on 2019.6.1
  */
trait Observable {
  type Handler
  val callbacks = HashMap[Handler, Function1[this.type, Unit]]()

  def observe(callBack: this.type => Unit): Handler = {
    val handler = createHandler(callBack)
    callbacks += (handler -> callBack)
    handler
  }

  def unobserve(handler: Handler): Unit = {
    callbacks -= handler
  }

  def notifyListeners(): Unit = {
    for (callback <- callbacks.values) {
      callback(this)
    }
  }

  /**
    * callback:接受当前对象类型并处理的回调函数
    *
    * @param callBack
    * @return { @link Handler}
    * @author chenwu on 2019.6.1
    */
  protected def createHandler(callBack: this.type => Unit): Handler
}

trait DefaultHandlers extends Observable {
  type Handler = (this.type => Unit)

  override def createHandler(callBack: DefaultHandlers.this.type => Unit): DefaultHandlers.this.type => Unit = callBack
}

class IntStore(private var value: Int) extends Observable with DefaultHandlers {

  def get: Int = value

  def set(newValue: Int): Unit = {
    value = newValue
    notifyListeners()
  }

  override def toString: String = s"IntStore ${value}"
}
