package com.mycom;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.Promise;

public class LanucherDeployer extends AbstractVerticle {
  public static void main(String[] args) {
    Launcher.executeCommand("run", LanucherDeployer.class.getName());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    vertx.deployVerticle(new HelloWorldVerticle());
  }
}
