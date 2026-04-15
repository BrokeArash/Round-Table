package controllers;

import models.App;
import models.Charm;
import models.Knight;
import models.Result;
import models.enums.Skills;

import java.util.Random;
import java.util.regex.Matcher;

import static java.lang.Math.max;

public class GameController {

    public Result Attack(Matcher matcher) {
        Knight myKnight = App.getGame().getCurrentKnight();
        String name = matcher.group("knight");
        Knight enemy = App.getGame().findEnemyKnightByName(name);
        if (myKnight.getKnight().getName().equals(name) && enemy == null) {
            return new Result(false, "you can't attack yourself");
        }
        if(myKnight.getTeammate().getKnight().getName().equals(name) && enemy == null) {
            return new Result(false, "you can't attack your own teammate");
        }

        if (enemy == null) {
            return new Result(false, "enemy doesn't exist");
        }
        int damageDealt = calculateBaseAttack(myKnight, enemy);
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

    public int calculateBaseAttack(Knight me, Knight enemy) {
        int damage = max(0, ((me.getAttack() + me.getMagicAttack())/2) - (int)(enemy.getDefense()*0.3));
        Random rand = new Random();
        int speedRand = rand.nextInt(100);
        if (speedRand < enemy.getSpeed()) {
            return -1; //dodged
        }
        return damage;
    }

    public Result showStats(Matcher matcher, boolean enemy) {
        StringBuilder stringBuilder = new StringBuilder();
        String name = matcher.group("knight");
        Knight knight = null;
        if (enemy) knight = App.getGame().findEnemyKnightByName(name);
        else knight = App.getGame().findTeamKnightByName(name);

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
                    .append(" description: ")
                    .append(skill.getDescription()).append("\n--------------------\n");
        }
        return new Result(true, stringBuilder.toString());
    }


    public Result skill(Matcher matcher) {
        Knight myKnight = App.getGame().getCurrentKnight();
        Knight enemyKnight = null;
        Result result = null;
        String skills = matcher.group("skill");
        Skills skill = myKnight.findSkill(skills.trim());
        if (skill == null) {
            return new Result(false, "skill doesn't exist");
        } else if (!myKnight.getKnight().getSkills().contains(skill)) {
            return new Result(false, "you don't have this skill");
        }
        String enKnight = null;
        if (matcher.group("knight") != null) {
            enKnight = matcher.group("knight");
            if (skill.isEnemy()) enemyKnight = App.getGame().findEnemyKnightByName(enKnight);
            else  enemyKnight = App.getGame().findTeamKnightByName(enKnight);
        }
        else enemyKnight = App.getGame().getEnemyKnight();


        if (myKnight.getAP() >= skill.getAP()) {
            myKnight.subAP(skill.getAP());
            result = skill.perform(myKnight, enemyKnight);
            App.getGame().nextTurn();
        } else {
            return new Result(false, "you don't have enough AP");
        }

        return new Result(true, result.toString());
    }

    public Result showTurn() {
        String name = App.getGame().getCurrentKnight().getOwner().getName();
        return new Result(true, "you are now playing " + name + "'s " + App.getGame().getCurrentKnight().getKnight().getName());
    }

    public Result showCurrentMenu() {
        return new Result(true, "current menu: " + App.getCurrentMenu().toString());
    }

    public Result exit() {
        App.setExit(true);
        return new  Result(true, "");
    }

    public Result showAP() {
        Knight tmp = App.getGame().getCurrentKnight();
        return new Result(true, tmp.getKnight().getName() + "'s AP: " + App.getGame().getCurrentKnight().getAP() + "\n");
    }

    public Result skipTurn() {
        App.getGame().nextTurn();
        return new Result(true, App.getGame().getCurrentKnight() + " is playing...");
    }

    public Result showCharms() {
        Knight myKnight = App.getGame().getCurrentKnight();
        StringBuilder stringBuilder = new StringBuilder();
        Charm myCharm = myKnight.getCharm();
        if (myCharm.getHP() > 1) stringBuilder.append("HP got Buffed by: ").append((int)(myCharm.getHP()*100)-100).append("%\n");
        else if (myCharm.getHP() < 1) stringBuilder.append("HP got Nerfed by: ").append((int)(myCharm.getHP()*100)-100).append("%\n");

        if (myCharm.getAttack() > 1) stringBuilder.append("Attack got Buffed by: ").append((int)(myCharm.getAttack()*100)-100).append("%\n");
        else if (myCharm.getHP() < 1) stringBuilder.append("Attack got Nerfed by: ").append((int)(myCharm.getAttack()*100)-100).append("%\n");

        if (myCharm.getMagic() > 1) stringBuilder.append("Magic Attack got Buffed by: ").append((int)(myCharm.getMagic()*100)-100).append("%\n");
        else if (myCharm.getMagic() < 1) stringBuilder.append("Magic Attack got Nerfed by: ").append((int)(myCharm.getMagic()*100)-100).append("%\n");

        if (myCharm.getDefense() > 1) stringBuilder.append("Defense got Buffed by: ").append((int)(myCharm.getDefense()*100)-100).append("%\n");
        else if (myCharm.getDefense() < 1) stringBuilder.append("Defense got Nerfed by: ").append((int)(myCharm.getDefense()*100)-100).append("%\n");

        if (myCharm.getSpeed() > 1) stringBuilder.append("Speed got Buffed by: ").append((int)(myCharm.getSpeed()*100)-100).append("%\n");
        else if (myCharm.getSpeed() < 1) stringBuilder.append("Speed got Nerfed by: ").append((int)(myCharm.getSpeed()*100)-100).append("%\n");

        if (stringBuilder.isEmpty()) return new  Result(true, "You have no charms on yourself");
        return new Result(true, stringBuilder.toString());
    }
}
