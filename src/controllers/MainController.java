package controllers;

import models.*;
import models.enums.KnightType;
import models.enums.Menu;
import models.enums.Skill;

import java.util.ArrayList;

public class MainController {

    public Result showCurrentMenu() {
        return new Result(true, "current menu: " + App.getCurrentMenu().toString());
    }

    public Result exit() {
        App.setCurrentMenu(Menu.ExitMenu);
        return new  Result(true, "");
    }

    public Result showKnightsDetails(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("characters:").append("\n").append("--------------------").append("\n");
        for(KnightType knight : KnightType.values()){
            stringBuilder.append("name: ").append(knight.toString()).append(" - class: ").append(knight.getKnightClass().getKnightName()).append("\n")
                    .append("HP: ").append(knight.getHP()).append(" - attack: ").append(knight.getAttack())
                    .append(" - magic attack: ").append(knight.getMagicAttack()).append(" - defense: ").append(knight.getDefense())
                    .append(" - speed: ").append(knight.getSpeed()).append("\n").append("skills: ");
            for(Skill skill : knight.getSkills()){
                stringBuilder.append(skill.getName()).append(" - ");
            }
            stringBuilder.delete(stringBuilder.length()-3, stringBuilder.length());
            stringBuilder.append("\n").append("--------------------").append("\n");
        }
        return new Result(true,  stringBuilder.toString());
    }

    public Result gotoScoreboard() {
        App.setCurrentMenu(Menu.ScoreBoardMenu);
        return new Result(true, "you're now in " + App.getCurrentMenu().toString());
    }

    public Result Logout(){
        App.setCurrentPlayer(null);
        App.setCurrentMenu(Menu.SignupMenu);
        return new Result(true,  "logged out successfully");
    }

    public Result Play(String username){
        Player otherPlayer = App.getPlayerByUsername(username);
        if (username.equals(App.getCurrentPlayer().toString())) {
            return new Result(false,  "you can't play with yourself");
        }
        if (otherPlayer == null) {
            return new Result(false, "invalid player name");
        }
        ArrayList<Player> tmp = new ArrayList<>();
        tmp.add(App.getCurrentPlayer());
        tmp.add(otherPlayer);
        App.setGame(new Game(tmp));
        return new Result(true,  "you're playing with " + otherPlayer.toString() + "\n");
    }

    public Result chooseKnight(Game game, String inputName, int index) {
        KnightType chosen = null;

        for (KnightType knight : KnightType.values()) {
            if(knight.toString().equalsIgnoreCase(inputName.trim())) {
                chosen =  knight;
            }
        }

        if (chosen == null) {
            return new Result(false, "invalid knight name");
        }

        if (index == 1 &&
                App.getCurrentPlayer().getKnights().get(0).getKnight().equals(chosen)) {
            return new Result(false, "you've already chosen this knight");
        }

        if (index == 3 &&
                game.getOtherPlayer().getKnights().get(0).getKnight().equals(chosen)) {
            return new Result(false, "you've already chosen this knight");
        }

        Knight newKnight;
        if (index < 2) {
            newKnight = new Knight(chosen);
            App.getCurrentPlayer().getKnights().add(newKnight);
            game.getQueue().add(newKnight);

            if (index == 0) game.getCurrentPlayer().setCurrentKnight(newKnight);

        } else {
            newKnight = new Knight(chosen);
            game.getOtherPlayer().getKnights().add(newKnight);
            game.getQueue().add(newKnight);
            if (index == 2) game.getOtherPlayer().setCurrentKnight(newKnight);
        }

        return new Result(true, "knight selected successfully");
    }

    public void playOutro() {
        Knight knight = App.getGame().getQueue().poll();
        App.getGame().getQueue().offer(knight);
        App.setCurrentMenu(Menu.GameMenu);
    }
}
