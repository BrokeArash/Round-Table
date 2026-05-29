package models;

import models.enums.KnightType;

import static java.lang.Math.max;

public class Knight {
    private final KnightType type;
    private int HP;
    private boolean stunned;
    private boolean dead;
    private int AP;
    private int totalDamageDealt;
    private final Charm charm;


    public Knight(KnightType knight) {
        this.type = knight;
        this.HP = knight.getHP();
        this.stunned = false;
        this.dead = false;
        this.AP = 3;
        this.charm = new Charm();
        this.totalDamageDealt = 0;

    }

    public KnightType getType() {
        return type;
    }


    public Knight getTeammate() {
        Player player;
        if (App.getGame().getCurrentPlayer().getKnights().contains(this)) player = App.getGame().getCurrentPlayer();
        else player = App.getGame().getOtherPlayer();
        for (Knight knight : player.getKnights()) {
            if (!knight.equals(player.getCurrentKnight())) {
                return knight;
            }
        }
        return null;
    }

    public Charm getCharm() {
        return charm;
    }

    public int getHP() {
        return (int)(HP);
    }

    public void decreaseHP(int HP) {
        this.HP -= HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return (int)(type.getAttack() * charm.getAttack());
    }

    public int getDefense() {
        return (int)(type.getDefense() * charm.getDefense());
    }

    public int getMagicAttack() {
        return (int)(type.getMagicAttack() * charm.getMagic());
    }

    public int getSpeed() {
        return (int)(type.getSpeed() *charm.getSpeed());
    }

    public int getAP() {
        return AP;
    }

    public void addAP(int AP) {
        this.AP += AP;
        if (this.AP > 5) this.AP = 5;
    }

    public void setAP(int AP) {
        this.AP = AP;
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
        int maxHP = this.getType().getHP();
        int damageTaken = max(maxHP - this.HP, 0);
        int point = this.totalDamageDealt - damageTaken;

        if (!isDead())
            point *= 1.5;

        return point;
    }

    @Override
    public String toString() {
        return this.getType().toString();
    }
}
