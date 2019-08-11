package diveIntoScala.unit8_scala_collection

import java.io.{BufferedReader, FileInputStream, InputStreamReader}

/**
  * 实现了{@link Traversable}的文件读取循环器
  *
  * @param fileName 文件名称
  * @author chenwu on 2019.8.11
  */
class FileTravserable(val fileName: String) extends Traversable[String] {
  override def foreach[U](f: String => U): Unit = {
    val fileInputStream = new FileInputStream(fileName)
    val bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))
    println("begin read file")
    try {

      var line = bufferedReader.readLine()
      while (line != null) {
        f(line)
        line = bufferedReader.readLine()
      }
      println("finish iterator file")
    } finally {
      try {
        bufferedReader.close()
      } catch {
        case e => println(e)
      }
      try {
        fileInputStream.close()
      } catch {
        case e => println(e)
      }
      println("end read file")
    }
  }

  override def toString(): String = {
    s"filePath=${fileName}"
  }
}
