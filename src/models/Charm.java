package models;

public class Charm {
    private double attack;
    private double magic;
    private double defense;
    private double speed;

    public Charm() {
        this.attack = 1;
        this.magic = 1;
        this.defense = 1;
        this.speed = 1;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getMagic() {
        return magic;
    }

    public void setMagic(double magic) {
        this.magic = magic;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
