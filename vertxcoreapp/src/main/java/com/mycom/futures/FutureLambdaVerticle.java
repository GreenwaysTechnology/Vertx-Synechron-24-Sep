package com.mycom.futures;

import io.vertx.core.*;

public class FutureLambdaVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Launcher.executeCommand("run", FutureLambdaVerticle.class.getName());

  }

  //without data
  public Future<Void> getEmptyFuture() {
    Future future = Future.future(event -> {
      event.complete();
    });
    return future;
  }

  @Override
  public void start() throws Exception {
    super.start();
    getEmptyFuture().onComplete(event -> {
      if (event.succeeded()) {
        System.out.println("Success");
        event.result();
      } else {
        System.out.println("Failed");
        event.cause();
      }
    });
  }

}
