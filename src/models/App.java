package models;

import models.enums.Menu;

import java.util.ArrayList;

public class App {
    private static ArrayList<Player> players;
    private static Menu currentMenu;
    private static Player currentPlayer;

    private static Game game;

    public static ArrayList<Player> getPlayers() {
        if (players == null) players = new ArrayList<>();

        return players;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        App.currentPlayer = currentPlayer;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        App.game = game;
    }

    public static Player getPlayerByUsername(String username) {
        for (Player player : App.getPlayers()) {
            if(player.toString().equals(username))
                return player;
        }
        return null;
    }
}
