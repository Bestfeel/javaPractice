package com.github.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;


public class Server extends AbstractVerticle {
    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!");
        }).listen(8080);


    }

    public static void main(String[] args) {
        VertxOptions vo = new VertxOptions();
        vo.setEventLoopPoolSize(16);
        Vertx vertx = Vertx.vertx(vo);
        DeploymentOptions options = new DeploymentOptions();
        options.setInstances(100);
        vertx.deployVerticle(Server.class.getName(), options, e -> {
            System.out.println(e.succeeded());
            System.out.println(e.failed());
            System.out.println(e.cause());
            System.out.println(e.result());
        });

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);

                    System.out.println("sleep...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}