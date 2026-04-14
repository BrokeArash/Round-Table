package models.enums;

import models.KnightStats;

import java.util.List;

public enum Knights {
    Arthur("Arthur", KnightClass.Commander,
            new KnightStats(200, 40, 15, 60, 30),
            List.of(Skills.ShieldBash, Skills.Fortify, Skills.ArmorBreak, Skills.Rally)),
    Mordred("Mordred", KnightClass.Commander,
            new KnightStats(180, 50, 15, 50, 35),
            List.of(Skills.ShieldBash, Skills.BattleCry, Skills.ArmorBreak, Skills.StrikeCommand)),
    Lancelot("Lancelot", KnightClass.Warrior,
            new KnightStats(170, 60, 5, 90, 50),
            List.of(Skills.Slash, Skills.HeavyStrike, Skills.LifeSteal, Skills.TrialByCombat)),
    Percival("Percival", KnightClass.Warrior,
            new KnightStats(160, 65, 5, 70, 40),
            List.of(Skills.Slash, Skills.HeavyStrike, Skills.Rage, Skills.Berserk)),
    Galahad("Galahad", KnightClass.Healer,
            new KnightStats(150, 25, 20, 40, 70),
            List.of(Skills.GroupHeal, Skills.HealerRevive, Skills.Cleanse, Skills.Heal)),
    Gawain("Gawain", KnightClass.Healer,
            new KnightStats(140, 30, 25, 45, 65),
            List.of(Skills.Heal, Skills.GroupHeal, Skills.Blessing, Skills.LightStrike)),
    Morgan("Morgan", KnightClass.Mage,
            new KnightStats(120, 15, 70, 35, 60),
            List.of(Skills.Fireball, Skills.LightningStrike, Skills.Silence, Skills.ArcaneSurge)),
    Merlin("Merlin", KnightClass.Mage,
            new KnightStats(130, 10, 80, 40, 55),
            List.of(Skills.Fireball, Skills.LightningStrike, Skills.IceBlast, Skills.MageRevive)),
    ;

    private final String name;
    private final KnightClass knightClass;
    private final KnightStats stats;
    private final List<Skills> skills;

    Knights(String name, KnightClass knightClass, KnightStats stats, List<Skills> skills) {
        this.name = name;
        this.knightClass = knightClass;
        this.stats = stats;
        this.skills = skills;
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
