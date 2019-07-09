package diveIntoScala.unit7_implicit_typeSystem

import java.io.InputStream


trait FileLike[T <: FileLike[T]] {
  def name: String

  def exists: Boolean

  def isDirectory: Boolean

  /**
    * child获取子文件列表
    *
    * @return
    */
  def children: Seq[T]

  /**
    * child用于获取子目录
    *
    * @param name
    * @return
    */
  def child(name: String): T

  def mkdirs(): Unit

  def touch(): Unit

  def content: InputStream

  def writeContent(otherContent: InputStream): Boolean

  def getPath:String
}

object SyncUtil {

  def sync[F <: FileLike[F], T <: FileLike[T]](from: F, to: T): Unit = {

    //def sync(from: FileLike, to: FileLike):Unit = {

    def syncFile(file1: F, file2: T): Unit = {
      if (file2.isDirectory) {

      } else {
        file2.writeContent(file1.content)
      }
    }

    def syncDirectory(dir1: F, dir2: T): Unit = {
      if (!dir2.exists || !dir2.isDirectory) {
        dir2.mkdirs()
      }

      def findFile(file: FileLike[F], dir: FileLike[T]) = {
        (for {
          file2 <- dir.children
          if file.name == file2.name
        } yield file2).headOption
      }

      for (file1 <- dir1.children) {
        //如果没有在dir2里找到同名的文件,则表明file1是一个文件对象
        val file = findFile(file1, dir2)
        val file0 = dir2.child(file1.name)
        val file2 = file getOrElse file0
        if (file1.isDirectory) {
          file2.mkdirs()
        } else {
          file2.touch()
        }
        //todo:故意写一个错误的类型
        //def sync(from: FileLike, to: FileLike) 在这种声明下可以编译通过
        //sync[F,T](file2,file1)
        //在对FileLike加了类型声明后,现在调换file1、file2顺序会导致编译失败
        sync[F, T](file1, file2)
        //sync(file1,file2)
      }


    }

    if (from.isDirectory) {
      syncDirectory(from, to)
    } else {
      syncFile(from, to)
    }
  }


}
