package FunctionProgramming.state

/**
  *  随机数生成器
  *
  *  @author chenwu on 2019.3.6
  */
trait RNG {

  def nextInt: (Int, RNG)
}

case class SimpleRng(seed: Long) extends RNG {

  /**
    *
    * @return
    */
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val newRng = SimpleRng(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, newRng)
  }
}
