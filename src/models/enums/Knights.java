package models.enums;

import models.KnightStats;

import java.util.List;

public enum Knights {
    Arthur("Arthur", KnightClass.Commander,
            new KnightStats(200, 50, 15, 60, 30),
            List.of(Skills.ShieldBash, Skills.Fortify, Skills.ArmorBreak, Skills.Rally), true),
    Mordred("Mordred", KnightClass.Commander,
            new KnightStats(180, 40, 20, 50, 35),
            List.of(Skills.ShieldBash, Skills.BattleCry, Skills.ArmorBreak, Skills.StrikeCommand), true),
    Lancelot("Lancelot", KnightClass.Warrior,
            new KnightStats(170, 60, 5, 90, 50),
            List.of(Skills.Slash, Skills.HeavyStrike, Skills.LifeSteal, Skills.TrialByCombat), true),
    Percival("Percival", KnightClass.Warrior,
            new KnightStats(160, 65, 5, 70, 40),
            List.of(Skills.Slash, Skills.HeavyStrike, Skills.Rage, Skills.Berserk), true),
    Galahad("Galahad", KnightClass.Healer,
            new KnightStats(150, 25, 20, 40, 70),
            List.of(Skills.GroupHeal, Skills.HealerRevive, Skills.Cleanse, Skills.Heal), true),
    Gawain("Gawain", KnightClass.Healer,
            new KnightStats(140, 30, 25, 45, 65),
            List.of(Skills.Heal, Skills.GroupHeal, Skills.Blessing, Skills.LightStrike), true),
    Morgan("Morgan", KnightClass.Mage,
            new KnightStats(120, 10, 45, 35, 60),
            List.of(Skills.Fireball, Skills.LightningStrike, Skills.Silence, Skills.ArcaneSurge), true),
    Merlin("Merlin", KnightClass.Mage,
            new KnightStats(130, 5, 55, 40, 55),
            List.of(Skills.Fireball, Skills.LightningStrike, Skills.IceBlast, Skills.MageRevive), true),
    ;

    private final String name;
    private final KnightClass knightClass;
    private final KnightStats stats;
    private final List<Skills> skills;
    private boolean prebuilt;

    Knights(String name, KnightClass knightClass, KnightStats stats, List<Skills> skills,  boolean prebuilt) {
        this.name = name;
        this.knightClass = knightClass;
        this.stats = stats;
        this.skills = skills;
        this.prebuilt = prebuilt;
    }

    public String getName() {
        return name;
    }

    public KnightClass getKnightClass() {
        return knightClass;
    }

    public KnightStats getStats() {
        return stats;
    }

    public List<Skills> getSkills() {
        return skills;
    }
}
