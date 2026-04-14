package controllers;

import models.App;
import models.Player;
import models.Result;
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



    public Result Logout(){
        App.setMainPlayer(null);
        App.setGotoSignup(true);
        return new Result(true,  "Logout successful");
    }
}
