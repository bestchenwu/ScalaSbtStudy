package FunctionProgramming.state

import FunctionProgramming.state.RNG

object RNGTest {

  def main(args: Array[String]): Unit = {
      val rng = SimpleRng(11)
      //val rand = rng.unit(15)
      val rand = rng.rand_int
      val randDouble = rng.randomDoubleViaRand(rand)
      print(randDouble(rng)._1)
  }
}
