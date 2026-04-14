package models;

import models.enums.Knights;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Game {
    private final Player player1;
    private final Player player2;

    private final ArrayList<Knight> knights1 = new ArrayList<>();
    private final ArrayList<Knight> knights2 = new ArrayList<>();

    private Queue<Knight> queue =  new ArrayDeque<>();

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public ArrayList<Knight> getKnights1() {
        return knights1;
    }

    public ArrayList<Knight> getKnights2() {
        return knights2;
    }

    public Queue<Knight> getQueue() {
        return queue;
    }

    public Knights findKnightByName(String name) {
        for (Knights knight : Knights.values()) {
            if(knight.getName().equals(name)) {
                return knight;
            }
        }
        return null;
    }
}
