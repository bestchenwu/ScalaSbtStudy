package diveIntoScala.unit7_implicit_typeSystem

import java.io.{File, FileInputStream, FileOutputStream, InputStream}

/**
  * 这样改造后,FileLike可以接受任何类型的参数
  *
  * @tparam T
  */
trait FileLike1[T] {
  def name(file: T): String

  def exists(file: T): Boolean

  def isDirectory(file: T): Boolean

  /**
    * child获取子文件列表
    *
    * @return
    */
  def children(file: T): Seq[T]

  /**
    * child用于获取子目录
    *
    * @param name
    * @return
    */
  def child(file: T, name: String): T

  def mkdirs(file: T): Unit

  def touch(file: T): Unit

  def content(file: T): InputStream

  def writeContent(file: T, otherContent: InputStream): Boolean

  def getPath(file: T): String
}


object FileLike1 {
  implicit val ioFileLike = new FileLike1[File] {
    override def name(file: File): String = file.getName

    override def exists(file: File): Boolean = file.exists()

    override def isDirectory(file: File): Boolean = file.isDirectory

    /**
      * child获取子文件列表
      *
      * @return
      */
    override def children(file: File): Seq[File] = file.listFiles()

    /**
      * child用于获取子目录
      *
      * @param name
      * @return
      */
    override def child(file: File, name: String): File = file.listFiles().find(_.getName.equals(name)) getOrElse (new File(name))

    override def mkdirs(file: File): Unit = file.mkdir()

    override def touch(file: File): Unit = file.createNewFile()

    override def content(file: File): InputStream = new FileInputStream(file)

    override def writeContent(file: File, otherContent: InputStream): Boolean = {
      {
        val fileOututStream = new FileOutputStream(file, true)

        var content = otherContent.read()
        while (content > 0) {
          fileOututStream.write(content)
          content = otherContent.read()
        }
        true
      }
    }

    override def getPath(file: File): String = file.getAbsolutePath
  }
}

object SyncUtil1 {

  def sync[F: FileLike1, T: FileLike1](from: F, to: T): Unit = {

    //def sync(from: FileLike, to: FileLike):Unit = {
    //这里implicitly表示需要一个隐式可以包装T类型的FileLike1对象
    val fromHelper = implicitly[FileLike1[F]]
    val toHelper = implicitly[FileLike1[T]]

    def syncFile(file1: F, file2: T): Unit = {
      if (toHelper.isDirectory(file2)) {

      } else {
        toHelper.writeContent(file2, fromHelper.content(file1))
      }
    }

    def syncDirectory(dir1: F, dir2: T): Unit = {
      if (!toHelper.exists(dir2) || !toHelper.isDirectory(dir2)) {
        toHelper.mkdirs(dir2)
      }

      def findFile(file: F, dir: T) = {
        (for {
          file2 <- toHelper.children(dir)
          if fromHelper.name(file) == toHelper.name(file2)
        } yield file2).headOption
      }

      for (file1 <- fromHelper.children(dir1)) {
        //如果没有在dir2里找到同名的文件,则表明file1是一个文件对象
        val file = findFile(file1, dir2)
        val file0 = toHelper.child(dir2, fromHelper.name(file1))
        val file2 = file getOrElse file0
        if (fromHelper.isDirectory(file1)) {
          toHelper.mkdirs(file2)
        }
        //todo:故意写一个错误的类型
        //def sync(from: FileLike, to: FileLike) 在这种声明下可以编译通过
        //sync[F,T](file2,file1)
        //在对FileLike加了类型声明后,现在调换file1、file2顺序会导致编译失败
        sync[F, T](file1, file2)
        //sync(file1,file2)
      }


    }

    if (fromHelper.isDirectory(from)) {
      syncDirectory(from, to)
    } else {
      syncFile(from, to)
    }
  }


}