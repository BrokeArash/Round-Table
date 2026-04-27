package controllers;

import models.*;
import models.actions.SkillAction;
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
        Player player = App.getGame().getCurrentPlayer();
        return new Result(true, "you are now playing as " + player.toString() + "'s " + player.getCurrentKnight().toString());
    }

    public Result skipTurn() {
        nextTurn();
        return new Result(true, App.getGame().getCurrentPlayer().getCurrentKnight() + " is playing...");
    }

    public Result showDetails() {
        Knight myKnight = App.getGame().getCurrentPlayer().getCurrentKnight();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(App.getGame().getCurrentPlayer().toString()).append("'s ").append(myKnight.toString()).append(" details: \n")
                .append("--------------------").append("\n");

        //SKILLS
        stringBuilder.append("skills: ").append("\n");
        for(Skill skill : myKnight.getType().getSkills()){
            stringBuilder.append(skill.getName()).append("->").append(" AP: ").append(skill.getAP()).append("\n");
        }
        stringBuilder.append("--------------------\n");

        //AP
        stringBuilder.append("AP: ").append(myKnight.getAP()).append("\n").append("--------------------").append("\n");

        //CHARMS
        stringBuilder.append("charms: ").append("\n");
        stringBuilder.append(showCharms().toString());

        return new Result(true, stringBuilder.toString());
    }

    private Result showCharms() {
        Knight myKnight = App.getGame().getCurrentPlayer().getCurrentKnight();
        StringBuilder stringBuilder = new StringBuilder();
        Charm myCharm = myKnight.getCharm();

        if (myCharm.getAttack() > 1) stringBuilder.append("attack got buffed by: ").append((int)(myCharm.getAttack()*100)-100).append("%\n");
        else if (myCharm.getAttack() < 1) stringBuilder.append("attack got nerfed by: ").append(-(int)(myCharm.getAttack()*100)+100).append("%\n");

        if (myCharm.getMagic() > 1) stringBuilder.append("magic attack got buffed by: ").append((int)(myCharm.getMagic()*100)-100).append("%\n");
        else if (myCharm.getMagic() < 1) stringBuilder.append("magic attack got nerfed by: ").append(-(int)(myCharm.getMagic()*100)+100).append("%\n");

        if (myCharm.getDefense() > 1) stringBuilder.append("defense got buffed by: ").append((int)(myCharm.getDefense()*100)-100).append("%\n");
        else if (myCharm.getDefense() < 1) stringBuilder.append("defense got nerfed by: ").append(-(int)(myCharm.getDefense()*100)+100).append("%\n");

        if (myCharm.getSpeed() > 1) stringBuilder.append("speed got buffed by: ").append((int)(myCharm.getSpeed()*100)-100).append("%\n");
        else if (myCharm.getSpeed() < 1) stringBuilder.append("speed got nerfed by: ").append(-(int)(myCharm.getSpeed()*100)+100).append("%\n");

        if (stringBuilder.isEmpty()) return new  Result(true, "you have no charms on yourself\n");
        return new Result(true, stringBuilder.toString());
    }

    public Result showStats(String name, String username) {
        StringBuilder stringBuilder = new StringBuilder();
        Knight knight = null;
        boolean enemy;

        if (username == null)
            return new Result(false, "player doesn't exist");

        if (App.getGame().getCurrentPlayer().toString().equals(username.trim())) enemy = false;
        else if (App.getGame().getOtherPlayer().toString().equals(username.trim())) enemy = true;
        else return new Result(false, "player doesn't exist");

        if (enemy) knight = App.getGame().findEnemyKnightByName(name);
        else knight = App.getGame().findTeamKnightByName(name);

//        if (knight == null) {
//            if (name.equalsIgnoreCase(App.getGame().getCurrentPlayer().getCurrentKnight().toString())) knight = App.getGame().getCurrentPlayer().getCurrentKnight();
//            else if (name.equalsIgnoreCase(App.getGame().getCurrentPlayer().getCurrentKnight().getTeammate().toString())) knight = App.getGame().getCurrentPlayer().getCurrentKnight().getTeammate();
//        }
        if (knight == null) {
            return new Result(false, "knight doesn't exist");
        }
        stringBuilder.append("name: ").append(knight.toString()).append(" - class: ").append(knight.getType().getKnightClass().getKnightName()).append("\n")
                .append("HP: ").append(knight.getHP()).append(" - attack: ").append(knight.getAttack())
                .append(" - magic attack: ").append(knight.getMagicAttack()).append(" - defense: ").append(knight.getDefense())
                .append(" - speed: ").append(knight.getSpeed());
        return new Result(true, stringBuilder.toString());
    }

    public Result Attack(String name) {
        Knight myKnight = App.getGame().getCurrentPlayer().getCurrentKnight();
        StringBuilder stringBuilder = new StringBuilder();
        Knight enemy = App.getGame().findEnemyKnightByName(name);
        if (myKnight.toString().equalsIgnoreCase(name) && enemy == null) {
            return new Result(false, "you can't attack yourself");
        }
        if(myKnight.getTeammate().toString().equalsIgnoreCase(name) && enemy == null) {
            return new Result(false, "you can't attack your own teammate");
        }

        if (enemy == null || enemy.isDead()) {
            return new Result(false, "enemy doesn't exist");
        }
        int damageDealt = calculateBaseAttack(myKnight, enemy);
        if (damageDealt < 0) {
            nextTurn();
            stringBuilder.append("shokhosh enemy dodge dad").append("\n");
            stringBuilder.append(App.getGame().getCurrentPlayer().getCurrentKnight()).append(" is playing...");
            return new Result(true, stringBuilder.toString());
        }

        myKnight.addTotalDamageDealt(damageDealt);
        enemy.decreaseHP(damageDealt);
        stringBuilder.append("you dealt ").append(damageDealt).append(" damage to ").append(enemy.toString()).append("\n");
        if (enemy.getHP() <= 0) {
            enemy.setDead(true);
            enemy.setStunned(false);
            stringBuilder.append(enemy.toString()).append(" is dead!").append("\n");
            if (!App.getGame().checkEnd()) {
                nextTurn();
                stringBuilder.append(App.getGame().getCurrentPlayer().getCurrentKnight()).append(" is playing...");
            }
            return new Result(true, stringBuilder.toString());
        }
        nextTurn();
        stringBuilder.append(App.getGame().getCurrentPlayer().getCurrentKnight()).append(" is playing...");
        return new Result(true, stringBuilder.toString());

    }




    public Result skill(String skillName, String knight) {
        Knight myKnight = App.getGame().getCurrentPlayer().getCurrentKnight();
        Knight enemyKnight = null;
        StringBuilder sb = new StringBuilder();
        Skill skill = findSkill(skillName.trim());
        if (skill == null) {
            return new Result(false, "skill doesn't exist");
        } else if (!myKnight.getType().getSkills().contains(skill)) {
            return new Result(false, "you don't have this skill");
        }
        String enKnight = null;
        if (knight != null) {
            if (!skill.isNeedDashK())
                return new Result(false, "this skill doesn't need target");
            enKnight = knight.trim();
            if (skill.isEnemy()) {
                enemyKnight = App.getGame().findEnemyKnightByName(enKnight);
            }
            else  enemyKnight = App.getGame().findTeamKnightByName(enKnight);

            if (enemyKnight == null || (enemyKnight.isDead() && !skill.equals(Skill.Revive))) {
                return new Result(false, "selected knight doesn't exist");
            }

        }


        if (skill.isNeedDashK() && knight == null)
            return new Result(false, "this skill needs a target");

        if (myKnight.getAP() < skill.getAP()) {
            return new Result(false, "you don't have enough AP");
        }

        myKnight.subAP(skill.getAP());
        for (SkillAction action : skill.getActions()) {
            sb.append(action.execute(skill, new BattleContext(myKnight, enemyKnight)));
        }

        if (!App.getGame().checkEnd()) {
            nextTurn();
            sb.append(App.getGame().getCurrentPlayer().getCurrentKnight()).append(" is playing...");
        }
        return new Result(true, sb.toString());

    }

    private int calculateBaseAttack(Knight me, Knight enemy) {
        int damage = max(0, ((me.getAttack() + me.getMagicAttack())/2) - (int)(enemy.getDefense()*0.3));
        int attack = me.getAttack();
        if(me.getType().getKnightClass().equals(KnightClass.Healer) || me.getType().getKnightClass().equals(KnightClass.Mage))
            attack = me.getMagicAttack();
        if (attack < enemy.getSpeed()) {
            return -1; //dodged
        }
        return damage;
    }

    private Skill findSkill(String name) {
        for (Skill skill : Skill.values()) {
            if (skill.getName().equalsIgnoreCase(name)) {
                return skill;
            }
        }
        return null;
    }

    public Result gameOutro() {
        StringBuilder stringBuilder = new StringBuilder();
        Game game = App.getGame();
        Player winner = game.getWinner();
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
                .append("winner: ").append(winner.toString()).append(" - points: ").append(winner.getPoint()).append("\n");


        endingStats(stringBuilder, winner, winner.getKnights());
        stringBuilder.append("--------------------").append("\n");

        stringBuilder.append("loser: ").append(loser.toString()).append(" - points: ").append(loser.getPoint()).append("\n");
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
            stringBuilder.append(winner.toString()).append("'s ").append(knight.toString()).append(": ")
                    .append("damage dealt: ").append(knight.getTotalDamageDealt()).append(" - ")
                    .append("HP remained: ").append(knight.getHP()).append(" - ");
            if (knight.isDead()) stringBuilder.append("dead");
            else stringBuilder.append("alive");
            stringBuilder.append(" - ").append("point: ").append(knight.calculatePoint()).append("\n");
        }
    }

    private void nextTurn() {
        Game game = App.getGame();
        game.getCurrentPlayer().getCurrentKnight().addAP(2);
        boolean flag = false;
        Knight tmp = null;
        while (!flag) {
            tmp = game.getQueue().poll();
            if (tmp.isDead()) {
                game.getQueue().offer(tmp);
                continue;
            }else if (tmp.isStunned()) {
                game.getQueue().offer(tmp);
                tmp.setStunned(false);
                continue;
            }
            flag = true;
        }
        for (Player player : game.getPlayers()) {
            if(player.getKnights().contains(tmp)) {
                Player prev = App.getGame().getCurrentPlayer();
                prev.setCurrentKnight(prev.getKnights().get(0));
                game.setCurrentPlayer(player);
            }
        }
        game.getCurrentPlayer().setCurrentKnight(tmp);
        game.getQueue().offer(tmp);
    }
}
