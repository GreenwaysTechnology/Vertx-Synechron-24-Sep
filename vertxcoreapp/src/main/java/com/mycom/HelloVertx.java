package com.mycom;

import io.vertx.core.Vertx;

public class HelloVertx {
  public static void main(String[] args) {
    //create vertx Engine
    Vertx instance =Vertx.vertx();
    System.out.println(instance.getClass());

  }
}
