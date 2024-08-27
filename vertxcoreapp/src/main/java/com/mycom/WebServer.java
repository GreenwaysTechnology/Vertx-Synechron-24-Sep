package com.mycom;

import io.vertx.core.Vertx;

//Write non blocking webserver and return some message
public class WebServer {
  public static void main(String[] args) {
    System.out.println("Thread inside Main Class" + Thread.currentThread().getName());
    Vertx vertx = Vertx.vertx();
    vertx.createHttpServer().requestHandler(req->{
      System.out.println("Thread inside Vertx " + Thread.currentThread().getName());
       req.response().end("Hello Vertx");
    }).listen(8080);
  }
}
