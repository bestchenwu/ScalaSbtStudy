package diveIntoScala.unit4_objectProgram

trait Logger {

  def log(category: String, msg: String) = {
    println(s"category=${category},msg=${msg}")
  }
}

trait RemoteLogger extends Logger {

  override def log(category: String, msg: String): Unit = {
    println(s"send to remote as category=${category},msg=${msg}")
  }
}

trait NullLogger extends Logger {
  override def log(category: String, msg: String): Unit = {
    println(s"send to null as category=${category},msg=${msg}")
  }
}

trait HasLogger {
  val logger: Logger = new Logger {}
}

trait HasRemoteLogger extends HasLogger {
  override val logger: Logger = new RemoteLogger {}
}

trait HasNullLogger extends HasLogger {
  override val logger: Logger = new NullLogger {}
}

//is-a 模式
//trait DataAccess extends HasLogger {
//  def query[A](in: String) = {
//    logger.log("query", in)
//  }
//}
//使用成员变量来代替is-a模式
class DataAccess(val logger: Logger = new Logger {}) {
  def query[A](in: String): Unit = {
    logger.log("query", in)
  }
}


//trait DataAccess {
//
//  //这是将另一个特质包含在当前特质的例子
//  //val logger = new Logger {}
//
//  def query[A](in: String) = {
//
//  }
//}

//使用组合代替继承模式
//trait DataLogAccess {
//
//  val log = new Logger {}
//  val dataLogAccess = new DataLogAccess {}
//
//  def logAndQuery[A](in: String): Unit = {
//    log.log("logAndQuery", in)
//    dataLogAccess.logAndQuery(in)
//  }
//
//}
