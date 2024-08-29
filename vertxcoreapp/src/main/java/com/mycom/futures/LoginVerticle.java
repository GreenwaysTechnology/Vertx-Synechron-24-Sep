package com.mycom.futures;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Launcher;

public class LoginVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Launcher.executeCommand("run", LoginVerticle.class.getName());
  }

  //biz api which may return success or failure based on login input
  public Future<String> login(String userName, String password) {
    return Future.future(event -> {
      if (userName.equals("admin") && password.equals("admin")) {
        event.complete("Login success");
      } else {
        event.fail("Login failed");
      }
    });
  }

  @Override
  public void start() throws Exception {
    super.start();
    login("admin", "admin").onComplete(handler -> {
      if (handler.failed()) {
        System.out.println(handler.cause().getMessage());
      } else {
        System.out.println(handler.result());
      }
    });

    login("foo", "bar").onComplete(handler -> {
      if (handler.failed()) {
        System.out.println(handler.cause().getMessage());
      } else {
        System.out.println(handler.result());
      }
    });
    //short cuts:
    login("admin", "admin")
      .onSuccess(res -> {
        System.out.println(res);
      })
      .onFailure(err -> {
        System.out.println(err);
      });

    //method references:
    login("admin", "admin")
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
  }
}
