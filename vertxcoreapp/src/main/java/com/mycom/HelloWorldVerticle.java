package com.mycom;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class HelloWorldVerticle extends AbstractVerticle {
  //override life cycle method
  @Override
  public void init(Vertx vertx, Context context) {
    super.init(vertx, context);
    System.out.println("Init is called");
  }

  //whenever we deploy the verticle on vertx engine these methods are called auotmatically
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    System.out.println("Start is called");
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    System.out.println("Stop is called");
  }
}
