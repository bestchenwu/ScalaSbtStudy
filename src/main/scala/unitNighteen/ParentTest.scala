package unitNighteen

class grandParent;

class parent extends grandParent;

class child extends parent;

//不变
class AClass[grandParent]

//协变，是子类或者当前类
class BClass[+grandParent]

//逆变,必须是超类
class CClass[-grandParent]


object ParentTest {
  def aMethod(x: AClass[parent]) = {}

  def bMethod(x: BClass[parent]): Unit = {}

  def cMethod(x: CClass[parent]) = {}

  //由于aMethod只能接受不变类型的
  aMethod(new AClass[parent]())
  //后续两个方法都不能编译
  //aMethod(new AClass[child]())
  //aMethod(new AClass[grandParent]())

  bMethod(new BClass[parent])
  bMethod(new BClass[child])
  //协变类型只能接受当前类和其子类
  //bMethod(new BClass[grandParent]);

  cMethod(new CClass[parent]);
  cMethod(new CClass[grandParent])
  //逆变类型只能接受当前类和其父类
  //cMethod(new CClass[child])
}
