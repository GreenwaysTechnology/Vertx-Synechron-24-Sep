package com.mycom.promises;

import com.mycom.HelloWorldVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.Promise;

public class PromiseStartVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Launcher.executeCommand("run", PromiseStartVerticle.class.getName());
  }
  //start

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    vertx.deployVerticle(new HelloWorldVerticle(), ar -> {
      if (ar.succeeded()) {
        System.out.println(HelloWorldVerticle.class.getName() + " " + "Deployment Id is " + ar.result());
        //startPromise.complete();
        startPromise.future().onSuccess(event -> {
          System.out.println("App is ready");
        });
      } else {
        startPromise.future().onFailure(event -> {
          System.out.println("App is failed");
        });
      }

    });
  }
}
