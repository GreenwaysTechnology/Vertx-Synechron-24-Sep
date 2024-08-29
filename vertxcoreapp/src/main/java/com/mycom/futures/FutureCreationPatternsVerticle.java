package com.mycom.futures;

import io.vertx.core.*;

public class FutureCreationPatternsVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Launcher.executeCommand("run", FutureCreationPatternsVerticle.class.getName());
  }
//Way 1:
public Future<String> login(String userName, String password) {
  return Future.future(event -> {
    if (userName.equals("admin") && password.equals("admin")) {
      event.complete("Login success");
    } else {
      event.fail("Login failed");
    }
  });
}
  //way 2:
  public Future<String> auth(String userName, String password) {
    if (userName.equals("admin") && password.equals("admin")) {
      return Future.succeededFuture("Login Success");
    }
    return Future.failedFuture("Login Failed");
  }

  //way 2: passing function as parameter: higher order function
  public void login(String userName, String password, Handler<AsyncResult<String>> asyncResultHandler) {
    if (userName.equals("admin") && password.equals("admin")) {
      asyncResultHandler.handle(Future.succeededFuture("Login success"));
    } else {
      asyncResultHandler.handle(Future.failedFuture("Login failed"));
    }

  }

  @Override
  public void start() throws Exception {
    super.start();
    auth("admin", "admin").onSuccess(System.out::println).onFailure(System.out::println);

    login("admin", "admin", handler -> {
      if (handler.failed()) {
        System.out.println(handler.cause());
      } else {
        System.out.println(handler.result());
      }
    });
  }
}
