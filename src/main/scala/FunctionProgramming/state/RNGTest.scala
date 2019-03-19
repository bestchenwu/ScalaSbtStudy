package FunctionProgramming.state

import FunctionProgramming.state.RNG

object RNGTest {

  def main(args: Array[String]): Unit = {
    val rng = RNG.SimpleRng(11)
    //val rand = rng.unit(15)
    //      val rand = rng.rand_int
    //      val randDouble = rng.randomDoubleViaRand(rand)
    //      print(randDouble(rng)._1)
    val rng1 = RNG.rand_int
    println(rng1(rng)._1)
    val rng2 = RNG.rand_int
    println(rng2(rng)._1)
    val rng3 = RNG.map2(rng1, rng2)(_ + _)
    print(rng3(rng)._1)
  }
}
