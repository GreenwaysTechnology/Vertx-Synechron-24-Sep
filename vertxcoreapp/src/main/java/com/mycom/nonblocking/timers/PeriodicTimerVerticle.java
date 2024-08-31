package com.mycom.nonblocking.timers;

import io.vertx.core.*;

public class PeriodicTimerVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Launcher.executeCommand("run", PeriodicTimerVerticle.class.getName());
  }

  public void tick() {
    vertx.setPeriodic(1000, handler -> {
      System.out.println(Math.random());
    });
  }

  public void poll(Handler<AsyncResult<Double>> aHandler) {
    vertx.setPeriodic(1000, handler -> {
      //Future.succeededFuture(Math.random());
      aHandler.handle(Future.succeededFuture(Math.random()));
    });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    // tick();
    poll(res -> {
      System.out.println(res.result());
    });
  }
}
