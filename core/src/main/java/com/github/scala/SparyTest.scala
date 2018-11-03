/*
 * Copyright (c) 2017.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more.
 */

package com.github.scala

import akka.actor.{Actor, ActorSystem, Props}
import akka.util.Timeout

import scala.concurrent.duration._


class MyServiceActor extends Actor {
  override def receive: Receive = ???
}

object SparyTest {

  def main(args: Array[String]): Unit = {
    // we need an ActorSystem to host our application in
    implicit val system = ActorSystem("on-spray-can")
    // create and start our service actor
    val service = system.actorOf(Props[MyServiceActor], "demo-service")
    implicit val timeout = Timeout(5.seconds)
    // start a new HTTP server on port 8080 with our service actor as the handler
    //    IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)

  }

}
