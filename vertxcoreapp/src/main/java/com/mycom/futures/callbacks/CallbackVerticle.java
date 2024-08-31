package com.mycom.futures.callbacks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Launcher;

public class CallbackVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Launcher.executeCommand("run", CallbackVerticle.class.getName());

  }

  public Future<User> getUser() {
    System.out.println("Get User is called");
    User user = new User("admin", "admin");
    //
   // user = null;
    if (user != null) {
      return Future.succeededFuture(user);
    } else {
      return Future.failedFuture("User not Found");
    }
  }

  //login
  public Future<String> login(String userName, String password) {
    System.out.println("Login is called");
    //biz logic
    if (userName.equals("admin") && password.equals("admin")) {
      return Future.succeededFuture("Login is success");
    } else {
      return Future.failedFuture("Login is failed");
    }
  }

  public Future<String> showDashboard(String status) {
    System.out.println("ShowDashboard is called");
    if (status.equals("Login is success")) {
      return Future.succeededFuture("Welcome to admin");
    } else {
      return Future.failedFuture("Welcome to Guest");
    }
  }

  @Override
  public void start() throws Exception {
    super.start();
    getUser().onComplete(userHandler -> {
      if (userHandler.failed()) {
        System.out.println(userHandler.cause().getMessage());
      } else {
        User user = userHandler.result();
        // System.out.println(user.getUserName() + " " + user.getPassword());
        //call login
        login(user.getUserName(), user.getPassword()).onComplete(loginHandler -> {
          if (loginHandler.failed()) {
            System.out.println(loginHandler.cause().getMessage());
          } else {
            //System.out.println(loginHandler.result());
            showDashboard(loginHandler.result()).onComplete(dashboardHandler -> {
              if (dashboardHandler.failed()) {
                System.out.println(dashboardHandler.cause());
              } else {
                System.out.println(dashboardHandler.result());
              }
            });
          }
        });
      }
    });
  }
}
