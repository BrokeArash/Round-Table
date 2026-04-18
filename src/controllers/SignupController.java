package controllers;

import models.App;
import models.Player;
import models.Result;
import models.enums.Menu;
import models.enums.SignupCommands;

public class SignupController {

    public Result showCurrentMenu() {
        return new Result(true, "current menu: " + App.getCurrentMenu().toString());
    }

    public Result exit() {
        App.setCurrentMenu(Menu.ExitMenu);
        return new  Result(true, "");
    }

    public Result register(String username, String password) {
        if((SignupCommands.CheckUsername.isMatch(username)) == null)
            return new Result(false, "invalid username");
        else if (App.getPlayerByUsername(username) != null)
            return new Result(false, "username already exists");
        else if ((SignupCommands.CheckPass.isMatch(password)) == null)
            return new Result(false, "invalid password");

        Player newPlayer = new Player(username, password);
        App.getPlayers().add(newPlayer);
        return new Result(true, "signup Successfully");

    }

    public Result login(String username, String password) {

        Player player = App.getPlayerByUsername(username);

        if (player == null)
            return new Result(false, "username not found");
        else if (!password.equals(player.getPassword()))
            return new Result(false, "password incorrect");

        App.setCurrentPlayer(player);
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "logged in successfully");

    }
}
