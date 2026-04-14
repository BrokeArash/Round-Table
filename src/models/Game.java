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

    private Knight currentKnight;

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

    public Knight getCurrentKnight() {
        return currentKnight;
    }

    public void setCurrentKnight(Knight currentKnight) {
        this.currentKnight = currentKnight;
    }

    public Knights findKnightByName(String name) {
        for (Knights knight : Knights.values()) {
            if(knight.getName().equals(name)) {
                return knight;
            }
        }
        return null;
    }

    public Knight findEnemyKnightByName(String name) {
        if(player1.equals(App.getGame().currentKnight.getOwner())) {
            for (Knight knight : knights2) {
                if (knight.getKnight().getName().equals(name))
                    return knight;
            }
        }
        else {
            for (Knight knight : knights1) {
                if (knight.getKnight().getName().equals(name))
                    return knight;
            }
        }
        return null;
    }

    public void nextTurn() {
        App.getGame().getCurrentKnight().addAP(1);
        boolean flag = false;
        Knight tmp = null;
        while (!flag) {
            tmp = this.getQueue().poll();
            if (tmp.isDead()) {
                continue;
            }else if (tmp.isStunned()) {
                this.getQueue().offer(tmp);
                continue;
            }
            flag = true;
        }
        this.setCurrentKnight(tmp);
        this.getQueue().offer(tmp);
    }
}
