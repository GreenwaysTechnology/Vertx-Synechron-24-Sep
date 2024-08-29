package com.mycom.futures;

import io.vertx.core.*;

public class FutureAnonymousVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Launcher.executeCommand("run", FutureAnonymousVerticle.class.getName());

  }

  //return new Empty Future:
  public Future<Void> getEmptyFuture() {
    //create Future Object and encapsulate data and return
    Future future = Future.future(new Handler<Promise<Void>>() {
      @Override
      public void handle(Promise<Void> promise) {
        //write logic to send data
        promise.complete();
      }
    });
    return future;
  }

  @Override
  public void start() throws Exception {
    //handle response which was returned by future due async operation :Caller
    // need to attach callback/listener/handler to read data
    getEmptyFuture().onComplete(new Handler<AsyncResult<Void>>() {
      @Override
      public void handle(AsyncResult<Void> asyncResult) {
        //read data which was given
        if (asyncResult.succeeded()) {
          System.out.println("Success");
          asyncResult.result();
        } else {
          System.out.println("Failed");
          asyncResult.cause();
        }
      }
    });
  }
}
