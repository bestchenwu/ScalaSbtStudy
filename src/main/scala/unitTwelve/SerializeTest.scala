package unitTwelve

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

@SerialVersionUID(-1L)
class Stock(id:Int,name:String) extends  Serializable{
  override def toString: String = s"id=${id},name=${name}"
}
object SerializeTest extends App {

  val stock = new Stock(1761,"babytree")
  val ops = new ObjectOutputStream(new FileOutputStream("D:\\kumo\\output\\stock.txt"));
  ops.writeObject(stock)
  ops.flush();
  ops.close();
  val ips = new ObjectInputStream(new FileInputStream("D:\\kumo\\output\\stock.txt"))
  val newStock = ips.readObject().asInstanceOf[Stock]
  ips.close()
  println(newStock)
}
