package controllers;

import models.App;
import models.Player;
import models.Result;
import models.enums.Menu;
import models.enums.SortType;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreboardController {

    public Result showCurrentMenu() {
        return new Result(true, "current menu: " + App.getCurrentMenu().toString());
    }

    public Result exit() {
        App.setCurrentMenu(Menu.ExitMenu);
        return new  Result(true, "");
    }

    public Result sort(String sortType) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Player> temp = new ArrayList<>(App.getPlayers());
        if ((sortType == null) || (sortType.length() == 0)) {
            return new Result(false, "invalid sort type");
        } else if (sortType.equalsIgnoreCase(SortType.point.toString())) {
            temp.sort(Comparator.comparing(Player::getPoint).reversed());
            for (Player player : temp) {
                stringBuilder.append(player.toString()).append("-> ").append("points: ").append(player.getPoint()).append("\n");
            }
        } else if (sortType.equalsIgnoreCase(SortType.GamesPlayed.toString())) {
            temp.sort(Comparator.comparing(Player::getGamesPlayed).reversed());
            for (Player player : temp) {
                stringBuilder.append(player.toString()).append("-> ").append("games played: ").append(player.getGamesPlayed()).append("\n");
            }
        } else if (sortType.equalsIgnoreCase(SortType.GamesWon.toString())) {
            temp.sort(Comparator.comparing(Player::getGamesWon).reversed());
            for (Player player : temp) {
                stringBuilder.append(player.toString()).append("-> ").append("games won: ").append(player.getGamesWon()).append("\n");
            }
        } else {
            return new Result(false, "invalid sort type");
        }
        temp.clear();
        return new Result(true, stringBuilder.toString());
    }

    public Result back() {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "you're now in " + App.getCurrentMenu().toString());
    }
}
