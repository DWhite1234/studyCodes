package com.atguigu.day04_面向对象

object 特质_接口 {
  def main(args: Array[String]): Unit = {
    val son = new Son
    println(son.age)
    son.sayHi()
    son.sayHello()
    println(son.name)

    /*
      父类与特质有重名的属性或者函数,该属性或者函数必须被重写
     */

    /*
    特质叠加的实质就是super的执行顺序
    class Son extends Father with Young{}

    super.young -> super.Father
    谁在后面就先执行谁

     */


    /*
    特质混入的特殊用法(mixin),只针对单次创建对象时,混入,原来的class不收影响
     */
    val student = new Father() with Young {
      override def sayHi(): Unit = {
        println("特质混入")
      }
      override val age:Int =11
    }


  }
}

trait Young{
  //可以有抽象的方法和属性,也可以与具体的方法和属性

  //抽象方法和抽象属性,必须被重写
  val name:String
  def sayHi()

  //具体的方法和属性
  val age:Int = 18
  def sayHello() = {
    println("hello")
  }
}

class Father{
  val name:String="zhangsan"
  val age:Int=30

  def sayHi() = {
    println("hi")
  }
  def sayHello1() = {
    println("hello")
  }
}
/*
* 1.如果既继承父类又,实现特质
*   先extends 父类 再with 特质
*
* 2.如果只是实现特质
*   第一个特质用extends 后面的特质用with
*
* */
class Son extends Father with Young{
  override val name: String = "lisi"
  override val age:Int = 25
  override def sayHi(): Unit = {
    println("子类重写")
  }
}


/*
特质的自身类型
 */

trait teacher{

}

trait stu{
  //指定stu特质,依赖于teacher特质,但是自身不extends teacher,但是extends stu的类必须同时withteacher特质
  _:teacher =>
}

class ss extends stu with teacher{}


/*
特质和抽象类的区别
1.抽象类 只能单继承,特质可以多 实现
2.抽象类可以实现带参构造函数,特质只有无参构造
 */