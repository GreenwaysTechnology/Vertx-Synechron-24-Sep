package com.mycom.futures.callbacks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Launcher;

public class CallbackHellSolutionVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Launcher.executeCommand("run", CallbackHellSolutionVerticle.class.getName());

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
  public Future<String> login(User user) {
    System.out.println("Login is called");
    //biz logic
    if (user.getUserName().equals("admin") && user.getPassword().equals("admin")) {
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

  //soultion 1:
  private void doSolutionOne() {
    getUser().onSuccess(user -> {
      login(user).onSuccess(status -> {
        showDashboard(status).onSuccess(dashboard -> {
          System.out.println(dashboard);
        }).onFailure(err -> {
          System.out.println(err);
        });
      }).onFailure(err -> {
        System.out.println(err);
      });
    }).onFailure(err -> {
      System.out.println(err.getMessage());
    });
  }

  //Using Future.compose
  private void doSolutionTwo() {
    getUser().compose(user -> {
      return login(user);
    }).compose(status -> {
      return showDashboard(status);
    }).onSuccess(res -> {
      System.out.println(res);
    }).onFailure(err -> {
      System.out.println(err);
    });

    //lambda refactoring
    getUser()
      .compose(user->login(user))
      .compose(status->showDashboard(status))
      .onSuccess(res -> {
        System.out.println(res);
      }).onFailure(err -> {
        System.out.println(err);
      });

    //method Reference
    getUser()
      .compose(this::login)
      .compose(this::showDashboard)
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
  }

  @Override
  public void start() throws Exception {
    super.start();
    //Solution 1:
    //doSolutionOne();
    //Solution 2:
    doSolutionTwo();
  }
}
