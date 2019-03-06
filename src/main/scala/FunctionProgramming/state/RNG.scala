package FunctionProgramming.state


/**
  * 随机数生成器
  *
  * @author chenwu on 2019.3.6
  */
trait RNG {

  def nextInt: (Int, RNG)

  /** 一个状态到另一个状态,称为状态转移 **/
  type RAND[+A] = RNG=>(A,RNG) //组合子

  val randInt:RAND[Int] = _.nextInt

  def unit[A](a:A):RAND[A]= rng=>(a,rng)

}


case class SimpleRng(seed: Long) extends RNG {

  /**
    * 返回一个随机数(并把下一次随机的状态给调用方)
    *
    * @return (Int,RNG)
    * @author chenwu on 2019.3.6
    */
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val newRng = SimpleRng(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, newRng)
  }

  /**
    * 返回一个随机数pair
    *
    * @param rng
    * @return (随机数pair,RNG)
    * @author chenwu on 2019.3.6
    */
  def randomPair(rng: RNG): ((Int, Int), RNG) = {
    val (int1, rng1) = rng.nextInt
    val (int2, rng2) = rng1.nextInt
    ((int1, int2), rng2)
  }

  /**
    * 返回0到Int.maxValue之间的数字<br/>
    * 由于Int.minValue比-Int.maxValue小1
    *
    * @param rng
    * @return
    * @author chenwu on 2019.3.6
    */
  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (int1, rng2) = rng.nextInt
    (if (int1 < 0) -(int1 + 1) else int1, rng2)
  }

  /**
    * 获取0-1之间的double数
    *
    * @param rng
    * @return (Double,RNG)
    * @author chenwu on 2019.3.6
    */
  def randomDouble(rng: RNG): (Double, RNG) = {
    val (int, rng2) = rng.nextInt
    (int.toDouble / Int.MaxValue, rng2)
  }
}
