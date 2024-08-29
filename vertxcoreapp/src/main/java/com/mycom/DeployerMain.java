package com.mycom;

import io.vertx.core.Vertx;

public class DeployerMain {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new HelloWorldVerticle());
  }
}
