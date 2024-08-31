package com.mycom.futures.callbacks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Launcher;

public class CompositeFutureVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Launcher.executeCommand("run", CompositeFutureVerticle.class.getName());

  }

  public Future<String> startDbServer() {
    System.out.println("DB server started");
    return Future.succeededFuture("DB server is up");
  }

  public Future<String> startHttpServer() {
    System.out.println("HTTP server started");
    return Future.succeededFuture("HttpServer  is up");
  }

  public Future<String> startConfigServer() {
    System.out.println("Config server started");
    return Future.succeededFuture("ConfigServer  is up");
  }

  @Override
  public void start() throws Exception {
    super.start();
    Future<String> dbServer = startDbServer();
    Future<String> webServer = startHttpServer();
    Future<String> configServer = startConfigServer();
    //
    CompositeFuture.all(dbServer, webServer, configServer).onComplete(ar -> {
      if (ar.succeeded()) {
        System.out.println("All servers are up");
        //do something else
      } else {
        System.out.println(ar.cause().getMessage());
      }
    });
    //Using Future:
    Future.all(dbServer, webServer, configServer).onComplete(ar -> {
      if (ar.succeeded()) {
        System.out.println("All servers are up");
        //do something else
      } else {
        System.out.println(ar.cause().getMessage());
      }
    });

    Future.all(dbServer, webServer, configServer)
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
  }
}

