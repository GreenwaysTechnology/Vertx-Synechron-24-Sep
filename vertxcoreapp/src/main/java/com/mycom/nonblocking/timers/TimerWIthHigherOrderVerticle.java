package com.mycom.nonblocking.timers;

import io.vertx.core.*;


public class TimerWIthHigherOrderVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Launcher.executeCommand("run", TimerWIthHigherOrderVerticle.class.getName());
  }

  private void blockMe(String message) {
    System.out.println(message);
  }

  public void getMessage(Handler<AsyncResult<String>> aHandler) {
    vertx.setTimer(1000, timerHandler -> {
      String res = "Hello How are you";
      aHandler.handle(Future.succeededFuture(res));
    });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    //timer
    blockMe("start");
    getMessage(aRes -> {
      System.out.println(aRes.result());
    });
    blockMe("end");
  }
}


