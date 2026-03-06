package models;

import models.enums.Menu;

import java.util.ArrayList;

public class App {
    private static ArrayList<Player> players;
    private static Menu currentMenu;
    private static Player mainPlayer;

    /* Requests */
    private static boolean gotoLogin;
    private static boolean backToSignup;
    private static boolean exit;



    public static ArrayList<Player> getPlayers() {
        if (players == null) players = new ArrayList<>();

        return players;
    }

    public static void setPlayers(ArrayList<Player> players) {
        App.players = players;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static Player getMainPlayer() {
        return mainPlayer;
    }

    public static void setMainPlayer(Player mainPlayer) {
        App.mainPlayer = mainPlayer;
    }

    public static Player getPlayerByUsername(String username) {
        for (Player player : App.getPlayers()) {
            if(player.getName().equals(username))
                return player;
        }
        return null;
    }

    /* Requests Getters & Setters */
    public static boolean isGotoLogin() {
        return gotoLogin;
    }

    public static void setGotoLogin(boolean gotoLogin) {
        App.gotoLogin = gotoLogin;
    }

    public static boolean isBack() {
        return backToSignup;
    }

    public static void setBack(boolean back) {
        App.backToSignup = back;
    }

    public static boolean isExit() {
        return exit;
    }

    public static void setExit(boolean exit) {
        App.exit = exit;
    }
}
