package FunctionProgramming.test

import java.util.Random

import FunctionProgramming.state.RNG
import FunctionProgramming.state.RNG.SimpleRng

object PropTest {

  def main(args: Array[String]): Unit = {
    //val prop = new SimpleProp()
    val rng = SimpleRng(45l)
    val prop = new SimpleProp()
    val result = prop.unit(RNG.nonNegativeInt(rng))
    val (intValue, rng2) = result.sample.run(rng)
    println(intValue)
    println(rng2)
    //val result = prop.choose(23, 50)
    //    val (intValue, rng2) = result.sample.run(rng)
    //    println(intValue)
    //    println(rng2)
    //println(prop.unit(rng.nextInt._1))
  }
}
