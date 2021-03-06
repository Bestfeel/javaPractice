package com.github.scala

object TypeVariance {

  def main(args: Array[String]): Unit = {
    val empty = new Empty[String]
    val ints = new Empty[Int]
    val any = new Empty[Any]
    empty.++(any)
    println((empty.++(ints)))
  }
}

trait MyList[+T] {
  def ++[O >: T](other: MyList[O]): MyList[O]
}

class Empty[T] extends MyList[T] {
  override def ++[O >: T](other: MyList[O]): MyList[O] = other
}