package controllers;

import models.App;
import models.Knight;
import models.Result;
import models.enums.Skills;

import java.util.regex.Matcher;

public class GameController {

    public Result Attack(Matcher matcher) {
        Knight myKnight = App.getGame().getCurrentKnight();
        String name = matcher.group("knight");
        if (myKnight.getKnight().getName().equals(name)) {
            return new Result(false, "you can't attack yourself");
        }
        if(myKnight.getTeammate().getKnight().getName().equals(name)){
            return new Result(false, "you can't attack your own teammate");
        }
        Knight enemy = App.getGame().findEnemyKnightByName(name);
        if (enemy == null) {
            return new Result(false, "enemy doesn't exist");
        }
        int damageDealt = Knight.calculateBaseAttack(myKnight, enemy);
        if (damageDealt < 0) {
            App.getGame().nextTurn();
            return new Result(true, "enemy dodged!!!");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            enemy.decreaseHP(damageDealt);
            stringBuilder.append("you dealt ").append(damageDealt).append("\n");
            if (enemy.getHP() <= 0) {
                enemy.setDead(true);
                stringBuilder.append(enemy.getKnight().getName()).append(" is dead!!!");
                return new Result(true, stringBuilder.toString());
            }
            App.getGame().nextTurn();
            return new Result(true, stringBuilder.toString());
        }
    }

    public Result showStats(Matcher matcher) {
        StringBuilder stringBuilder = new StringBuilder();
        String name = matcher.group("knight");
        Knight knight = App.getGame().findEnemyKnightByName(name);
        if (knight == null) {
            if (name.equals(App.getGame().getCurrentKnight().getKnight().getName())) knight = App.getGame().getCurrentKnight();
            else if (name.equals(App.getGame().getCurrentKnight().getTeammate().getKnight().getName())) knight = App.getGame().getCurrentKnight().getTeammate();
        }
        if (knight == null) {
            return new Result(false, "knight doesn't exist");
        }
        stringBuilder.append("Name: ").append(knight.getKnight().getName()).append(" - Class: ").append(knight.getKnight().getKnightClass().getKnightName()).append("\n")
                .append("HP: ").append(knight.getHP()).append(" - Attack: ").append(knight.getAttack())
                .append(" - Magic Attack: ").append(knight.getMagicAttack()).append(" - Defense: ").append(knight.getDefense())
                .append(" - Speed: ").append(knight.getSpeed()).append("\n");
        return new Result(true, stringBuilder.toString());
    }

    public Result skillDetails() {
        Knight myKnight = App.getGame().getCurrentKnight();
        StringBuilder stringBuilder = new StringBuilder();
        for(Skills skill : myKnight.getKnight().getSkills()){
            stringBuilder.append(skill.getName()).append("->").append(" AP: ").append(skill.getAP())
                    .append(" target type: ").append(skill.getType()).append(" description: ")
                    .append(skill.getDescription()).append("\n--------------------\n");
        }
        return new Result(true, stringBuilder.toString());
    }


    public Result skill(Matcher matcher) {
        Knight myKnight = App.getGame().getCurrentKnight();
        Knight enemyKnight = null;
        Result result = null;
        String skills = matcher.group("skill");
        Skills skill = myKnight.findSkill(skills);
        if (skill == null) {
            return new Result(false, "skill doesn't exist");
        } else if (!myKnight.getKnight().getSkills().contains(skill)) {
            return new Result(false, "you don't have this skill");
        }
        String enKnight = null;
        try {
            enKnight = matcher.group("knight");
            enemyKnight = App.getGame().findEnemyKnightByName(enKnight);

        } catch (NullPointerException e) {}

        if (enemyKnight == null) { //type group
            if (myKnight.getAP() >= skill.getAP()) {
               result = skill.perform(myKnight, null); //TODO: FIX
            } else {
                return new Result(false, "you don't have enough AP");
            }
        } else { //type single
            if (myKnight.getAP() >= skill.getAP()) {
                result = skill.perform(myKnight, enemyKnight);
            } else {
                return new Result(false, "you don't have enough AP");
            }
        }
        return new Result(true, result.toString());
    }
}
