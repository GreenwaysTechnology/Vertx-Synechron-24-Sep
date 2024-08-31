package com.mycom.nonblocking.timers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Launcher;
import io.vertx.core.Promise;

public class TimerWithDataVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Launcher.executeCommand("run", TimerWithDataVerticle.class.getName());
  }

  private void blockMe(String message) {
    System.out.println(message);
  }

  public Future<String> getMessage() {
    return Future.future(ar -> {
      String response = "Hello Timer!";
      vertx.setTimer(1000, handler -> {
        ar.complete(response);
      });
    });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    //timer
    blockMe("start");
    getMessage().onSuccess(System.out::println);
    blockMe("end");
  }
}

