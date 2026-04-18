package models;

import java.util.ArrayList;

public class Player {
    private final String name;
    private final String password;

    private ArrayList<Knight> knights;
    private Knight currentKnight;

    private int point;
    private int damageDealt;
    private int gamesPlayed;
    private int gamesWon;

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
        this.point = 0;
        this.damageDealt = 0;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Knight> getKnights() {
        if (knights == null) {
            knights = new ArrayList<>();
        }
        return knights;
    }

    public int getPoint() {
        return point;
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public Knight getCurrentKnight() {
        return currentKnight;
    }

    public void setCurrentKnight(Knight currentKnight) {
        this.currentKnight = currentKnight;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public void addDamageDealt(int damageDealt) {
        this.damageDealt += damageDealt;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void addGamesPlayed() {
        this.gamesPlayed ++;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void addGamesWon() {
        this.gamesWon ++;
    }
}
