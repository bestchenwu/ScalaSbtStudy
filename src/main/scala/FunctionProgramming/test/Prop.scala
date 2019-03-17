package FunctionProgramming.test

trait Prop {


  def check:Boolean
  def &&(p:Prop):Prop=new Prop{
    override def check: Boolean = {
      p.check == Prop.this.check
    }

  }
}
