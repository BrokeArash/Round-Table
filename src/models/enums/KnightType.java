package models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum KnightType {
    Arthur("Arthur",KnightClass.Commander,200, 50, 15, 60, 30,
            new ArrayList<>(Arrays.asList(Skill.ShieldBash, Skill.Fortify, Skill.ArmorBreak, Skill.Rally))),
    Mordred("Mordred",KnightClass.Commander,180, 40, 20, 50, 35,
            new ArrayList<>(Arrays.asList(Skill.ShieldBash, Skill.StrikeCommand, Skill.ArmorBreak, Skill.Rally))),
    Lancelot("Lancelot",KnightClass.Warrior, 170, 60, 5, 70, 50,
            new ArrayList<>(Arrays.asList(Skill.Slash, Skill.HeavyStrike, Skill.LifeSteal, Skill.Berserk))),
    Galahad("Galahad", KnightClass.Healer,150, 20, 25, 40, 70,
            new ArrayList<>(Arrays.asList(Skill.Heal, Skill.GroupHeal, Skill.Revive, Skill.Cleanse))),
    Morgan("Morgan", KnightClass.Mage,120, 10, 45, 35, 60,
            new ArrayList<>(Arrays.asList(Skill.Fireball, Skill.LightningStrike, Skill.ArcaneSurge, Skill.Silence))),
    Merlin("Merlin", KnightClass.Mage, 130, 5, 55, 40, 55,
            new ArrayList<>(Arrays.asList(Skill.Fireball, Skill.LightningStrike, Skill.IceBlast, Skill.ArcaneSurge))),
    ;

    private final String name;
    private final KnightClass knightClass;
    private final int HP;
    private final int attack;
    private final int magicAttack;
    private final int defense;
    private final int speed;
    private final List<Skill> skills;

    KnightType(String name,KnightClass knightClass, int HP, int attack, int magicAttack, int defense, int speed, List<Skill> skills) {
        this.name = name;
        this.knightClass = knightClass;
        this.HP = HP;
        this.attack = attack;
        this.magicAttack = magicAttack;
        this.defense = defense;
        this.speed = speed;
        this.skills = skills;
    }

    public KnightClass getKnightClass() {
        return knightClass;
    }

    public int getHP() {
        return HP;
    }

    public int getAttack() {
        return attack;
    }

    public int getMagicAttack() {
        return magicAttack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public List<Skill> getSkills() {
        return skills;
    }


    @Override
    public String toString() {
        return name;
    }
}
