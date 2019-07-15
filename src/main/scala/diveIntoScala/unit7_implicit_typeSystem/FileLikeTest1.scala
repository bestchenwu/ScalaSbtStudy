package diveIntoScala.unit7_implicit_typeSystem

import java.io.File


object FileLikeTest1 {
  def main(args: Array[String]): Unit = {
    val srcFile = new File("/data/problem/dir")
    val objectFile = new File("/data/problem1/dir")
    import FileLike1.ioFileLike
    SyncUtil1.sync(srcFile, objectFile)
  }
}
