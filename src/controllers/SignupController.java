package controllers;

import models.App;
import models.Player;
import models.Result;
import models.enums.Menu;
import models.enums.SignupCommands;

import java.util.regex.Matcher;

public class SignupController {
    public Result register(String username, String password) {



        if((SignupCommands.CheckUsername.isMatch(username)) == null)
            return new Result(false, "Invalid username!");
        else if (App.getPlayerByUsername(username) != null)
            return new Result(false, "Username already exists!");
        else if ((SignupCommands.CheckPass.isMatch(password)) == null)
            return new Result(false, "Invalid password!");
        else {
            Player newPlayer = new Player(username, password);
            App.getPlayers().add(newPlayer);
            return new Result(true, "Signup Successful!");
        }
    }

    public Result login(String username, String password) {

        Player player = App.getPlayerByUsername(username);

        if (player == null)
            return new Result(false, "Username not found!");
        else if (!password.equals(player.getPassword()))
            return new Result(false, "Password incorrect!");
        else {
            App.setMainPlayer(player);
            App.setCurrentMenu(Menu.MainMenu);
            return new Result(true, "Logged in successfully!");
        }
    }

    public Result showCurrentMenu() {
        return new Result(true, "current menu: " + App.getCurrentMenu().toString());
    }

    public Result exit() {
        App.setCurrentMenu(Menu.ExitMenu);
        return new  Result(true, "");
    }
}
