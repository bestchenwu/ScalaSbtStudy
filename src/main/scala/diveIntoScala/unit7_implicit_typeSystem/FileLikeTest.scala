package diveIntoScala.unit7_implicit_typeSystem

import java.io.{File, FileInputStream, FileOutputStream, InputStream}

/**
  * 使用java本地文件来模拟FileLike及其数据同步
  *
  * @param fileName
  */
class LocalFileLike(var fileName: String) extends FileLike[LocalFileLike] {

  val localFile: File = new File(fileName)

  override def name: String = localFile.getName

  override def exists: Boolean = localFile.exists()

  override def isDirectory: Boolean = localFile.isDirectory


  override def touch(): Unit = localFile.createNewFile()

  /**
    * child获取子文件列表
    *
    * @return
    */
  override def children: Seq[LocalFileLike] = localFile.listFiles().map(file => new LocalFileLike(file.getName))

  /**
    * child用于获取子目录
    *
    * @param name
    * @return
    */
  override def child(name: String): LocalFileLike = {
    val file = children.find(localFileLike => localFileLike.fileName.equals(name)).getOrElse(new LocalFileLike(name))
    if (!file.exists) {
      file.touch()
    }
    file
  }

  override def mkdirs(): Unit = localFile.mkdir()

  override def content: InputStream = {
    val fileInputStream = new FileInputStream(localFile)
    fileInputStream
  }

  override def writeContent(otherContent: InputStream): Boolean = {
    val fileOututStream = new FileOutputStream(localFile, true)

    var content = otherContent.read()
    while (content > 0) {
      fileOututStream.write(content)
      content = otherContent.read()
    }
    true
  }
}

object FileLikeTest {

  def main(args: Array[String]): Unit = {
    val srcFile = new LocalFileLike("/data/problem/dir")
    val objectFile = new LocalFileLike("/data/problem1/dir")
    SyncUtil.sync(srcFile, objectFile)
  }
}
