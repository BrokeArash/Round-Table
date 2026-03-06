package controllers;

import models.App;
import models.Player;
import models.Result;
import models.enums.SignupCommands;

import java.util.regex.Matcher;

public class SignupController {
    public Result register(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String email = matcher.group("email");

        Matcher usernameMatcher = SignupCommands.CheckUsername.getMatcher(username);
        Matcher passwordMatcher = SignupCommands.CheckPass.getMatcher(password);
        Matcher emailMatcher = SignupCommands.CheckEmail.getMatcher(email);

        if(usernameMatcher == null)
            return new Result(false, "Invalid username!");
        else if (App.getPlayerByUsername(username) != null)
            return new Result(false, "Username already exists!");
        else if (passwordMatcher == null)
            return new Result(false, "Invalid password!");
        else if (emailMatcher == null)
            return new Result(false, "Invalid email!");
        else {
            Player newPlayer = new Player(username, password, email);
            App.getPlayers().add(newPlayer);
            return new Result(true, "Signup Successful!");
        }
    }

    public Result gotoLogin() {
        App.setGotoLogin(true);
        return new Result(true, "You're in Login Menu!");
    }
}
