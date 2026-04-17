package models;

import models.enums.KnightType;
import models.enums.Skill;


import static java.lang.Math.max;

public class Knight {
    private final KnightType knight;
    private final Player owner;
    private Knight teammate;
    private int HP; //stats
    private int attack;
    private int defense;
    private int magicAttack;
    private int speed;
    private boolean stunned;
    private boolean dead;
    private int AP;

    private int totalDamageDealt;

    private Charm charm;


    public Knight(KnightType knight, Player owner) {
        this.knight = knight;
        this.owner = owner;
        this.HP = knight.getStats().HP();
        this.attack = knight.getStats().attack();
        this.defense = knight.getStats().defense();
        this.magicAttack = knight.getStats().magicAttack();
        this.speed = knight.getStats().speed();
        this.stunned = false;
        this.dead = false;
        this.AP = 100;
        this.charm = new Charm();
        this.totalDamageDealt = 0;

    }

    public KnightType getKnight() {
        return knight;
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
        //if (this.AP > 5) this.AP = 5;
    }

    public void subAP(int AP) {
        this.AP -= AP;
        //if (this.AP > 5) this.AP = 5;
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

    public void setDead(boolean dead)
    {
        this.dead = dead;
        this.setHP(0);
    }

    public int getTotalDamageDealt() {
        return totalDamageDealt;
    }

    public void addTotalDamageDealt(int totalDamageDealt) {
        this.totalDamageDealt += totalDamageDealt;
    }

    public int calculatePoint() {
        int maxHP = this.getKnight().getStats().HP();
        int damageTaken = max(maxHP - this.HP, 0);
        int point = this.totalDamageDealt - damageTaken;

        if (!isDead())
            point *= 1.5;

        return point;
    }


    public Skill findSkill(String name) {
        for (Skill skill : Skill.values()) {
            if (skill.getName().equalsIgnoreCase(name)) {
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
