/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.github.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Procedure;

enum Msg {
    HAPPY, ANGRY
}

/**
 * Created by feel on 2016/12/1.
 */
public class BecomeTest {

    public static void main(String[] args) {


        final ActorSystem system = ActorSystem.create("Application");

        ActorRef babyActor = system.actorOf(Props.create(BabyActor.class), "BabyActor");

        babyActor.tell(Msg.HAPPY, ActorRef.noSender());
        babyActor.tell(Msg.ANGRY, ActorRef.noSender());
//        babyActor.tell(Msg.ANGRY, ActorRef.noSender());
        babyActor.tell(Msg.HAPPY, ActorRef.noSender());
        //  babyActor.tell(PoisonPill.getInstance(), ActorRef.noSender());

        // end  main
    }
}


class BabyActor extends UntypedActor {


    Procedure<Object> angry = new Procedure<Object>() {
        @Override
        public void apply(Object param) throws Exception {

            System.out.println("angry.." + param);
            if (param == Msg.ANGRY) {
                System.out.println("i'am  happy ");
                getContext().become(happy);
            } else if (param == Msg.HAPPY) {
                System.out.println("i'am   angry");
            } else {
                unhandled(param);
            }
        }
    };

    Procedure<Object> happy = new Procedure<Object>() {
        @Override
        public void apply(Object param) throws Exception {
            System.out.println("happy..." + param);
            if (param == Msg.ANGRY) {
                System.out.println("...i'am  happy ");
            } else if (param == Msg.HAPPY) {
                System.out.println("i'am  angry....  ");
                getContext().become(angry);
            } else {
                unhandled(param);
            }


        }
    };

    @Override
    public void onReceive(Object message) throws Throwable {

        System.out.println("reveice:" + message);
        if (message == Msg.ANGRY) {
            System.out.println("//////............");
            getContext().become(happy);

        } else if (message == Msg.HAPPY) {
            getContext().become(angry);
        } else {

            System.out.println("unhandled");
            unhandled(message);
        }


    }
}
