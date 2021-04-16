package day01.codes

class Student(name:String,age:Int) {
  def show(): Unit ={
    println(name+" "+age+" "+Student.school)
  }
}

/**
 * 作用:
 * 1.定义静态属性或方法
 * 2.作为程序的入口
 */
object Student{
  val school: String = "atguigu"

  def main(args: Array[String]): Unit = {
    val stu1 = new Student("zhangsan", 18)
    val stu2 = new Student("lisi", 20)
    stu1.show()
    stu2.show()
  }
}
