package controllers;

import models.*;
import models.enums.KnightClass;
import models.enums.Menu;
import models.enums.Skill;

import java.util.ArrayList;

import static java.lang.Math.max;

public class GameController {

    public Result showCurrentMenu() {
        return new Result(true, "current menu: " + App.getCurrentMenu().toString());
    }

    public Result exit() {
        App.setCurrentMenu(Menu.ExitMenu);
        return new  Result(true, "");
    }

    public Result showTurn() {
        String name = App.getGame().getCurrentPlayer().getName();
        return new Result(true, "you are now playing " + name + "'s " + App.getGame().getCurrentPlayer().getCurrentKnight().toString());
    }

    public Result skipTurn() {
        App.getGame().nextTurn();
        return new Result(true, App.getGame().getCurrentPlayer().getCurrentKnight() + " is playing...");
    }

    public Result showSkills() {
        Knight myKnight = App.getGame().getCurrentPlayer().getCurrentKnight();
        StringBuilder stringBuilder = new StringBuilder();
        for(Skill skill : myKnight.getKnight().getSkills()){
            stringBuilder.append(skill.getName()).append("->").append(" AP: ").append(skill.getAP()).append("\n--------------------\n");
        }
        return new Result(true, stringBuilder.toString());
    }

    public Result showCharms() {
        Knight myKnight = App.getGame().getCurrentPlayer().getCurrentKnight();
        StringBuilder stringBuilder = new StringBuilder();
        Charm myCharm = myKnight.getCharm();
        if (myCharm.getHP() > 1) stringBuilder.append("HP got buffed by: ").append((int)(myCharm.getHP()*100)-100).append("%\n");
        else if (myCharm.getHP() < 1) stringBuilder.append("HP got nerfed by: ").append((int)(myCharm.getHP()*100)-100).append("%\n");

        if (myCharm.getAttack() > 1) stringBuilder.append("attack got buffed by: ").append((int)(myCharm.getAttack()*100)-100).append("%\n");
        else if (myCharm.getHP() < 1) stringBuilder.append("attack got nerfed by: ").append((int)(myCharm.getAttack()*100)-100).append("%\n");

        if (myCharm.getMagic() > 1) stringBuilder.append("magic attack got buffed by: ").append((int)(myCharm.getMagic()*100)-100).append("%\n");
        else if (myCharm.getMagic() < 1) stringBuilder.append("magic attack got nerfed by: ").append((int)(myCharm.getMagic()*100)-100).append("%\n");

        if (myCharm.getDefense() > 1) stringBuilder.append("defense got buffed by: ").append((int)(myCharm.getDefense()*100)-100).append("%\n");
        else if (myCharm.getDefense() < 1) stringBuilder.append("defense got nerfed by: ").append((int)(myCharm.getDefense()*100)-100).append("%\n");

        if (myCharm.getSpeed() > 1) stringBuilder.append("speed got buffed by: ").append((int)(myCharm.getSpeed()*100)-100).append("%\n");
        else if (myCharm.getSpeed() < 1) stringBuilder.append("speed got nerfed by: ").append((int)(myCharm.getSpeed()*100)-100).append("%\n");

        if (stringBuilder.isEmpty()) return new  Result(true, "you have no charms on yourself");
        return new Result(true, stringBuilder.toString());
    }

    public Result showAP() {
        Knight tmp = App.getGame().getCurrentPlayer().getCurrentKnight();
        return new Result(true, tmp.toString() + "'s AP: " + App.getGame().getCurrentPlayer().getCurrentKnight().getAP() + "\n");
    }

    public Result showStats(String name, boolean enemy) {
        StringBuilder stringBuilder = new StringBuilder();
        Knight knight = null;
        if (enemy) knight = App.getGame().findEnemyKnightByName(name);
        else knight = App.getGame().findTeamKnightByName(name);

        if (knight == null) {
            if (name.equalsIgnoreCase(App.getGame().getCurrentPlayer().getCurrentKnight().toString())) knight = App.getGame().getCurrentPlayer().getCurrentKnight();
            else if (name.equalsIgnoreCase(App.getGame().getCurrentPlayer().getCurrentKnight().getTeammate().toString())) knight = App.getGame().getCurrentPlayer().getCurrentKnight().getTeammate();
        }
        if (knight == null) {
            return new Result(false, "knight doesn't exist");
        }
        stringBuilder.append("name: ").append(knight.toString()).append(" - class: ").append(knight.getKnight().getKnightClass().getKnightName()).append("\n")
                .append("HP: ").append(knight.getHP()).append(" - attack: ").append(knight.getAttack())
                .append(" - magic attack: ").append(knight.getMagicAttack()).append(" - defense: ").append(knight.getDefense())
                .append(" - speed: ").append(knight.getSpeed()).append("\n");
        return new Result(true, stringBuilder.toString());
    }

    public Result Attack(String name) {
        Knight myKnight = App.getGame().getCurrentPlayer().getCurrentKnight();

        Knight enemy = App.getGame().findEnemyKnightByName(name);
        if (myKnight.toString().equalsIgnoreCase(name) && enemy == null) {
            return new Result(false, "you can't attack yourself");
        }
        if(myKnight.getTeammate().toString().equalsIgnoreCase(name) && enemy == null) {
            return new Result(false, "you can't attack your own teammate");
        }

        if (enemy == null) {
            return new Result(false, "enemy doesn't exist");
        }
        int damageDealt = calculateBaseAttack(myKnight, enemy);
        if (damageDealt < 0) {
            App.getGame().nextTurn();
            return new Result(true, "enemy dodged!");
        }

        myKnight.addTotalDamageDealt(damageDealt);
        StringBuilder stringBuilder = new StringBuilder();
        enemy.decreaseHP(damageDealt);
        stringBuilder.append("you dealt ").append(damageDealt).append("\n");
        if (enemy.getHP() <= 0) {
            enemy.setDead(true);
            stringBuilder.append(enemy.toString()).append(" is dead!");
            return new Result(true, stringBuilder.toString());
        }
        App.getGame().nextTurn();
        return new Result(true, stringBuilder.toString());

    }




    public Result skill(String skillName, String knight) {
        Knight myKnight = App.getGame().getCurrentPlayer().getCurrentKnight();
        Knight enemyKnight = null;
        Result result = null;
        Skill skill = myKnight.findSkill(skillName.trim());
        if (skill == null) {
            return new Result(false, "skill doesn't exist");
        } else if (!myKnight.getKnight().getSkills().contains(skill)) {
            return new Result(false, "you don't have this skill");
        }
        String enKnight = null;
        if (knight != null) {
            if (!skill.isNeedDashK())
                return new Result(false, "this skill doesn't need target");
            enKnight = knight;
            if (skill.isEnemy()) enemyKnight = App.getGame().findEnemyKnightByName(enKnight);
            else  enemyKnight = App.getGame().findTeamKnightByName(enKnight);
        }
        else enemyKnight = App.getGame().getOtherPlayer().getCurrentKnight();
        if (skill.isNeedDashK() && knight == null)
            return new Result(false, "this skill needs a target");

        if (myKnight.getAP() < skill.getAP()) {
            return new Result(false, "you don't have enough AP");
        }

        myKnight.subAP(skill.getAP());
        result = skill.perform(myKnight, enemyKnight);
        App.getGame().nextTurn();
        return new Result(true, result.toString());

    }

    public int calculateBaseAttack(Knight me, Knight enemy) {
        int damage = max(0, ((me.getAttack() + me.getMagicAttack())/2) - (int)(enemy.getDefense()*0.3));
        int attack = me.getAttack();
        if(me.getKnight().getKnightClass().equals(KnightClass.Healer) || me.getKnight().getKnightClass().equals(KnightClass.Mage))
            attack = me.getMagicAttack();
        if (attack < enemy.getSpeed()) {
            return -1; //dodged
        }
        return damage;
    }

    public Result gameOutro(Player winner) {
        StringBuilder stringBuilder = new StringBuilder();
        Game game = App.getGame();
        Player loser = game.getOtherPlayer(winner);
        winner.addGamesPlayed();
        winner.addGamesWon();
        loser.addGamesPlayed();

        for (Knight knight: winner.getKnights()) {
            winner.addPoint(knight.calculatePoint());
            winner.addDamageDealt(knight.getTotalDamageDealt());
        }

        for (Knight knight: loser.getKnights()) {
            loser.addPoint(knight.calculatePoint());
            loser.addDamageDealt(knight.getTotalDamageDealt());
        }

        stringBuilder.append("war has ended").append("\n").append("--------------------").append("\n")
                .append("winner: ").append(winner.getName()).append(" - points: ").append(winner.getPoint()).append("\n");


        endingStats(stringBuilder, winner, winner.getKnights());
        stringBuilder.append("loser: ").append(loser.getName()).append(" - points: ").append(loser.getPoint()).append("\n");
        endingStats(stringBuilder, loser, loser.getKnights());


        App.setCurrentMenu(Menu.MainMenu);
        cleanUp();
        return new Result(true, stringBuilder.toString());

    }

    private void cleanUp() {
        for (Player player : App.getPlayers()) {
            player.getKnights().clear();
        }

    }

    private void endingStats(StringBuilder stringBuilder, Player winner, ArrayList<Knight> knights) {
        for (Knight knight : knights) {
            stringBuilder.append(winner.getName()).append("'s ").append(knight.toString()).append(": ")
                    .append("damage dealt: ").append(knight.getTotalDamageDealt()).append(" - ")
                    .append("HP remained: ").append(knight.getHP()).append(" - ");
            if (knight.isDead()) stringBuilder.append("dead");
            else stringBuilder.append("alive");
            stringBuilder.append(" - ").append("point: ").append(knight.calculatePoint()).append("\n");
        }
    }
}
