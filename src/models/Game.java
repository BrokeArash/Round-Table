package models;

import models.enums.KnightType;

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

    private Player winner;
    private Player loser;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = null;
        this.loser = null;
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

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getLoser() {
        return loser;
    }

    public void setLoser(Player loser) {
        this.loser = loser;
    }

    public KnightType findKnightByName(String name) {
        for (KnightType knight : KnightType.values()) {
            if(knight.getName().equalsIgnoreCase(name)) {
                return knight;
            }
        }
        return null;
    }

    public Knight findEnemyKnightByName(String name) {
        return getKnight(name, knights2, knights1);
    }

    public Knight findTeamKnightByName(String name) {
        return getKnight(name, knights1, knights2);
    }

    private Knight getKnight(String name, ArrayList<Knight> knights1, ArrayList<Knight> knights2) {
        if(player1.equals(App.getGame().currentKnight.getOwner())) {
            for (Knight knight : knights1) {
                if (knight.getKnight().getName().equalsIgnoreCase(name))
                    return knight;
            }
        }
        else {
            for (Knight knight : knights2) {
                if (knight.getKnight().getName().equalsIgnoreCase(name))
                    return knight;
            }
        }
        return null;
    }

    public Knight getEnemyKnight() {
        if(player1.equals(App.getGame().currentKnight.getOwner())) return knights2.get(0);
        return  knights1.get(0);
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
                tmp.setStunned(false);
                continue;
            }
            flag = true;
        }
        this.setCurrentKnight(tmp);
        this.getQueue().offer(tmp);
    }

    public boolean checkEnd() {
        if (knights1.get(0).isDead() && knights1.get(1).isDead()) {
            setLoser(player1);
            setWinner(player2);
            return true;
        }
        else if (knights2.get(0).isDead() && knights2.get(1).isDead()) {
            setLoser(player2);
            setWinner(player1);
            return true;
        }
        return false;
    }

}
