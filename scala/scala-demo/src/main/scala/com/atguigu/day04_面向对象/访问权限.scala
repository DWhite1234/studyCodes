package com.atguigu.day04_面向对象

object 访问权限 {
}

class access{
//  scala的访问权限
//  Scala 中属性和方法的默认访问权限为public，但Scala中无public关键字
  val pub = "默认为public权限"
  private val pri = "私有权限,只在类的内部和伴生对象中可用"
  protected val pro = "同类、子类可以访问，同包无法访问。"
  private[day04_面向对象] val pack = "private[包名]增加包访问权限，包名下的其他类也可以使用"
}
