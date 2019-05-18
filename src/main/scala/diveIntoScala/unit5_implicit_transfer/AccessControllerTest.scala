package diveIntoScala.unit5_implicit_transfer

import java.security.PrivilegedAction
import java.security.AccessController
import java.io.{File => JFile}

object AccessControllerTest {

  class FileWrapper(val file: JFile) {
    //获得一个新的文件对象
    def \(text: String) = new FileWrapper(new JFile(file, text))

    def getFileLength()={
      file.length()
    }

    override def toString: String = file.getCanonicalPath
  }

  object FileWrapper {
    implicit def wrapAsScalaFile(file: JFile) = new FileWrapper(file)
  }

  object ScalaSecurity {
    //当下的隐式转换接受一个无参函数
    implicit def function[A](func: Function0[A]) = new PrivilegedAction[A] {
      override def run(): A = func()
    }
  }

  def main(args: Array[String]): Unit = {
    //    import ScalaSecurity._
    //    AccessController.doPrivileged(() => println("run function"))
    import FileWrapper._
    val scalaFile = new JFile("/data/problem")
    val file1 = scalaFile \ "7.9.txt"
    println(file1.getFileLength())
    val file2 = scalaFile \ "7.4.txt"
    println(file2.getFileLength())
  }
}
