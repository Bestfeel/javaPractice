/*
 * Copyright (c) 2017.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more.
 */
package com.github.scala

case class User(name: String)

trait Repository {
  def save(user: User): Unit
}

trait ServiceCompoent {
  this: Repository =>
  val user: User

  def saveUser: Unit = this.save(user)
}

class Component extends ServiceCompoent with Repository {
  override val user = User("feel")

  override def save(user: User): Unit = {
    println("保存..." + user.name)
  }
}


trait Reader {
  type In

  def read(file: String): In
}

class FileReader extends Reader {
  override type In = String

  override def read(file: String): In = {
    s"${file}  is reading"
  }
}

object App {
  def main(args: Array[String]): Unit = {
    println("hello,world...")
    //    val list = for (i <- 1 to 10; j <- 1 to 10; l <- 1 to 10; if (i < j && j < l)) yield (i, j, l)
    //    println(list)
    //    println(list.length)
    //
    //    val stu = new Stu
    //    stu.play.show
    //
    //    appendLines(stu, List("a", "b", "c"))
    //    println(stu.info)

    val component = new Component
    component.saveUser
    val reader = new FileReader
    println(reader.read("io file"))
  }

  def appendLines(target: {def append(str: String): Any}, lines: Seq[String]) = {
    for (line <- lines) {
      target.append(line)
      target.append(",")
    }
  }

  private def test1 = {
    val t = (1400, "Jim", "haha", 3.14)
    val second = t._2
    val (first, seconds, third, fourth) = t
  }


}

class Stu {

  def show: this.type = {
    println("show..")
    this
  }

  def play: this.type = {
    println("this play")
    this
  }

  val info = new StringBuilder

  def append(str: String) = {
    info.append(str)
  }
}



