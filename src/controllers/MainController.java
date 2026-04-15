package controllers;

import models.*;
import models.enums.Knights;
import models.enums.Skills;

import java.util.regex.Matcher;

public class MainController {
    public Result SeeCharacters(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("characters:").append("\n").append("--------------------").append("\n");
        for(Knights knight : Knights.values()){
            stringBuilder.append("Name: ").append(knight.getName()).append(" - Class: ").append(knight.getKnightClass().getKnightName()).append("\n")
                    .append("HP: ").append(knight.getStats().HP()).append(" - Attack: ").append(knight.getStats().attack())
                    .append(" - Magic Attack: ").append(knight.getStats().magicAttack()).append(" - Defense: ").append(knight.getStats().defense())
                    .append(" - Speed: ").append(knight.getStats().speed()).append("\n").append("Skills:");
            for(Skills skill : knight.getSkills()){
                stringBuilder.append(skill.getName()).append(" - ");
            }
            stringBuilder.delete(stringBuilder.length()-3, stringBuilder.length());
            stringBuilder.append("\n").append("--------------------").append("\n");
        }
        return new Result(true,  stringBuilder.toString()); //TODO: add customized
    }

    public Result Play(Matcher matcher){
        Player otherPlayer = App.getPlayerByUsername(matcher.group("username"));
        if (matcher.group("username").equals(App.getMainPlayer().getName())) {
            return new Result(false,  "You can't play with yourself");
        }
        if (otherPlayer == null) {
            return new Result(false, "Invalid player name!");
        }
        App.setOtherPlayer(otherPlayer);
        return new Result(true,  "You're playing with " + otherPlayer.getName() + "!\n");
    }

    public Result chooseKnight(Game game, String inputName, int index) {
        Knights chosen = game.findKnightByName(inputName.trim());

        if (chosen == null) {
            return new Result(false, "Invalid knight name!");
        }

        if (index == 1 &&
                game.getKnights1().get(0).getKnight().equals(chosen)) {
            return new Result(false, "You already chosen this knight!");
        }

        if (index == 3 &&
                game.getKnights2().get(0).getKnight().equals(chosen)) {
            return new Result(false, "You already chosen this knight!");
        }

        Knight newKnight;
        if (index < 2) {
            newKnight = new Knight(chosen, game.getPlayer1());
            game.getKnights1().add(newKnight);
            game.getQueue().add(newKnight);

            if (index == 1) {
                game.getKnights1().get(0).setTeammate(newKnight);
                newKnight.setTeammate(game.getKnights1().get(0));
            } else {
                game.setCurrentKnight(newKnight);
            }

        } else {
            newKnight = new Knight(chosen, game.getPlayer2());
            game.getKnights2().add(newKnight);
            game.getQueue().add(newKnight);

            if (index == 3) {
                game.getKnights2().get(0).setTeammate(newKnight);
                newKnight.setTeammate(game.getKnights2().get(0));
            }
        }

        return new Result(true, "Knight selected successfully.");
    }

    public void playOutro() {
        App.getGame().getQueue().poll();
        App.setGotoGame(true);
    }



    public Result Logout(){
        App.setMainPlayer(null);
        App.setGotoSignup(true);
        return new Result(true,  "Logout successful");
    }

    public Result showCurrentMenu() {
        return new Result(true, "current menu: " + App.getCurrentMenu().toString());
    }

    public Result exit() {
        App.setExit(true);
        return new  Result(true, "");
    }
}
