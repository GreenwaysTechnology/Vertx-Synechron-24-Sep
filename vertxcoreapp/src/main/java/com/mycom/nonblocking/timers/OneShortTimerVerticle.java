package com.mycom.nonblocking.timers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.Promise;

public class OneShortTimerVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Launcher.executeCommand("run", OneShortTimerVerticle.class.getName());
  }

  private void blockMe(String message) {
    System.out.println(message);
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    //timer
    blockMe("start");
    vertx.setTimer(1000, handler -> {
      System.out.println("I am delayed task");
    });
    blockMe("end");
  }
}
