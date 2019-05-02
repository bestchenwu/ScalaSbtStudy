package diveIntoScala.unit4_objectProgram

trait Property {
  val name:String

  override def toString: String = s"property=${name}"
}

