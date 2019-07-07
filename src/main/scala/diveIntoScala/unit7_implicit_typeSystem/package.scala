package diveIntoScala

package object unit7_implicit_typeSystem {

  implicit object StringNumeric extends Numeric[String] {
    override def plus(x: String, y: String): String = x+y

    override def zero: String = ""

    override def minus(x: String, y: String): String = x.replace(y,"")

    override def times(x: String, y: String): String = ""

    override def negate(x: String): String = ""

    override def fromInt(x: Int): String = ""

    override def toInt(x: String): Int = 0

    override def toLong(x: String): Long = 0

    override def toFloat(x: String): Float = 0

    override def toDouble(x: String): Double = 0

    override def compare(x: String, y: String): Int = 0
  }
}
