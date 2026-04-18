package models;

import models.enums.KnightType;
import models.enums.Skill;


import static java.lang.Math.max;

public class Knight {
    private final KnightType knight;
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


    public Knight(KnightType knight) {
        this.knight = knight;
        this.HP = knight.getHP();
        this.attack = knight.getAttack();
        this.defense = knight.getDefense();
        this.magicAttack = knight.getMagicAttack();
        this.speed = knight.getSpeed();
        this.stunned = false;
        this.dead = false;
        this.AP = 100;
        this.charm = new Charm();
        this.totalDamageDealt = 0;

    }

    public KnightType getKnight() {
        return knight;
    }


    public Knight getTeammate() {
        Player player = App.getGame().getCurrentPlayer();
        for (Knight knight : player.getKnights()) {
            if (!knight.getKnight().equals(player.getCurrentKnight())) {
                return knight;
            }
        }
        return null; //must never happen
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

    public int getDefense() {
        return (int)(defense* charm.getDefense());
    }

    public int getMagicAttack() {
        return (int)(magicAttack* charm.getMagic());
    }

    public int getSpeed() {
        return (int)(speed*charm.getSpeed());
    }

    public int getAP() {
        return AP;
    }

    public void addAP(int AP) {
        this.AP += AP;
        if (this.AP > 5) this.AP = 5;
    }

    public void subAP(int AP) {
        this.AP -= AP;
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
        int maxHP = this.getKnight().getHP();
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
        return this.getKnight().toString();
    }
}
