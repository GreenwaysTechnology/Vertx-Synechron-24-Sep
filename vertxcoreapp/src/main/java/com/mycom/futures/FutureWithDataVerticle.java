package com.mycom.futures;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Launcher;

public class FutureWithDataVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Launcher.executeCommand("run", FutureWithDataVerticle.class.getName());
  }

  //Return data : async way of sending data.
  //Type of data in java can be String,Number,Object,json,List....
  public Future<String> sayHello() {
    //Future future = Future.future();
    return Future.future(event -> {
      event.complete("Hello");
    });
  }

  //Return error : asyn way of sending error/Exception
  public Future<String> getError() {
    return Future.future(event -> event.fail("Something went wrong"));
  }

  @Override
  public void start() throws Exception {
    super.start();
    sayHello().onComplete(handler -> {
      if (handler.succeeded()) {
        System.out.println(handler.result());
      } else {
        System.out.println(handler.cause().getMessage());
      }
    });

    //handle failures
    getError().onComplete(handler -> {
      if (handler.failed()) {
        System.out.println(handler.cause().getMessage());
      }
    });
  }
}
