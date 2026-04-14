package controllers;

import models.App;
import models.Player;
import models.Result;

import java.util.regex.Matcher;

public class LoginController {
    public Result login(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        Player player = App.getPlayerByUsername(username);

        if (player == null)
            return new Result(false, "Username not found!");
        else if (!password.equals(player.getPassword()))
            return new Result(false, "Password incorrect!");
        else {
            App.setMainPlayer(player);
            return new Result(true, "Logged in successfully!");
        }
    }

//    public Result forgetPass(Matcher matcher) {
//        String username = matcher.group("username");
//        String email = matcher.group("email");
//        Player player = App.getPlayerByUsername(username);
//
//        if (player == null) {
//            return new Result(false, "username doesn't exist!");
//        } else if (!player.getEmail().equals(email)) {
//            return new Result(false, "email doesn't match!");
//        } else {
//            return new Result(true, "password: " + player.getPassword());
//        }
//    }
}
