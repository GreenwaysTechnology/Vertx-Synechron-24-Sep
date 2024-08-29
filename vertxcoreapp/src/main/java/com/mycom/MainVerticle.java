package com.mycom;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    //you can deploy other verticles

    //vertx.deployVerticle(new HelloWorldVerticle());
    //passing string
    vertx.deployVerticle(HelloWorldVerticle.class.getName());

//    vertx.createHttpServer().requestHandler(req -> {
//      req.response()
//        .putHeader("content-type", "text/plain")
//        .end("Hello from Vert.x!");
//    }).listen(8888).onComplete(http -> {
//      if (http.succeeded()) {
//        startPromise.complete();
//        System.out.println("HTTP server started on port 8888");
//      } else {
//        startPromise.fail(http.cause());
//      }
//    });
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    vertx.undeploy(HelloWorldVerticle.class.getName());
  }
}
