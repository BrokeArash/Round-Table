package models;

import models.enums.KnightClass;
import models.enums.Knights;
import models.enums.Skills;

import java.util.Random;

import static java.lang.Math.max;

public class Knight {
    private Knights knight;
    private Player owner;
    private Knight teammate;
    private int HP;
    private int attack;
    private int defense;
    private int magicAttack;
    private int speed;
    private boolean stunned;
    private boolean dead;
    private int AP;

    private Charm charm;


    public Knight(Knights knight, Player owner) {
        this.knight = knight;
        this.owner = owner;
        this.HP = knight.getStats().HP();
        this.attack = knight.getStats().attack();
        this.defense = knight.getStats().defense();
        this.magicAttack = knight.getStats().magicAttack();
        this.speed = knight.getStats().speed();
        this.stunned = false;
        this.dead = false;
        this.AP = 3;
    }

    public Knights getKnight() {
        return knight;
    }

    public void setKnight(Knights knight) {
        this.knight = knight;
    }

    public Player getOwner() {
        return owner;
    }

    public Knight getTeammate() {
        return teammate;
    }

    public void setTeammate(Knight teammate) {
        this.teammate = teammate;
    }

    public Charm getCharm() {
        return charm;
    }

    public int getHP() {
        return (int)(HP* charm.getHP());
    }

    public void decreaseHP(int HP) {
        this.HP -= HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return (int)(attack*charm.getAttack());
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return (int)(defense* charm.getDefense());
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMagicAttack() {
        return (int)(magicAttack* charm.getMagic());
    }

    public void setMagicAttack(int magicAttack) {
        this.magicAttack = magicAttack;
    }

    public int getSpeed() {
        return (int)(speed*charm.getSpeed());
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAP() {
        return AP;
    }

    public void addAP(int AP) {
        this.AP += AP;
        if (this.AP > 5) this.AP = 5;
    }

    public boolean isStunned() {
        return stunned;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public static int calculateBaseAttack(Knight me, Knight enemy) {
        int damage = max(0, ((me.getAttack() + me.magicAttack)/2) - (int)(enemy.defense*0.3));
        Random rand = new Random();
        int speedRand = rand.nextInt(100);
        if (speedRand < enemy.getSpeed()) {
            return -1; //dodged
        }
        return damage;
    }

    public Skills findSkill(String name) {
        for (Skills skill : Skills.values()) {
            if (skill.getName().equals(name)) {
                return skill;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getKnight().getName();
    }
}
