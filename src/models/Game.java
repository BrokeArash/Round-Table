package models;

import models.enums.KnightType;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Game {
    private final ArrayList<Player> players;
    private Player currentPlayer = App.getCurrentPlayer();
    private Queue<Knight> queue =  new ArrayDeque<>();

    public Game(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Queue<Knight> getQueue() {
        return queue;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player checkEnd() {
        if (players.get(0).getKnights().get(0).isDead() && players.get(0).getKnights().get(1).isDead()) {
            return players.get(1);
        }
        else if (players.get(1).getKnights().get(0).isDead() && players.get(1).getKnights().get(1).isDead()) {
            return players.get(0);
        }
        return null;
    }

    public Knight findEnemyKnightByName(String name) {
        Player otherPlayer = getOtherPlayer();
        for (Knight knight : otherPlayer.getKnights()) {
            if (knight.toString().equalsIgnoreCase(name)) {
                return knight;
            }
        }
        return null;
    }

    public Knight findTeamKnightByName(String name) {
        Player otherPlayer = getCurrentPlayer();
        for (Knight knight : otherPlayer.getKnights()) {
            if (knight.toString().equalsIgnoreCase(name)) {
                return knight;
            }
        }
        return null;
    }
    
    public Player getOtherPlayer() {
        for (Player player : players) {
            if (!player.equals(App.getGame().getCurrentPlayer())) {
                return player;
            }
        }
        return null; //must never happen
    }

    public Player getOtherPlayer(Player player) {
        for (Player tmp : players) {
            if (!player.equals(tmp)) {
                return tmp;
            }
        }
        return null; //must never happen
    }

}
