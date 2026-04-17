package models.enums;

import models.KnightStats;

import java.util.List;

public enum KnightType {
    Arthur("Arthur", KnightClass.Commander,
            new KnightStats(200, 50, 15, 60, 30),
            List.of(Skill.ShieldBash, Skill.Fortify, Skill.ArmorBreak, Skill.Rally)),
    Mordred("Mordred", KnightClass.Commander,
            new KnightStats(180, 40, 20, 50, 35),
            List.of(Skill.ShieldBash, Skill.Rally, Skill.ArmorBreak, Skill.StrikeCommand)),
    Lancelot("Lancelot", KnightClass.Warrior,
            new KnightStats(170, 60, 5, 90, 50),
            List.of(Skill.Slash, Skill.HeavyStrike, Skill.LifeSteal, Skill.Berserk)),
    Galahad("Galahad", KnightClass.Healer,
            new KnightStats(150, 25, 20, 40, 70),
            List.of(Skill.GroupHeal, Skill.HealerRevive, Skill.Cleanse, Skill.Heal)),
    Morgan("Morgan", KnightClass.Mage,
            new KnightStats(120, 10, 45, 35, 60),
            List.of(Skill.Fireball, Skill.LightningStrike, Skill.Silence, Skill.ArcaneSurge)),
    Merlin("Merlin", KnightClass.Mage,
            new KnightStats(130, 5, 55, 40, 55),
            List.of(Skill.Fireball, Skill.LightningStrike, Skill.IceBlast, Skill.ArcaneSurge)),
    ;

    private final String name;
    private final KnightClass knightClass;
    private final KnightStats stats;
    private final List<Skill> skills;

    KnightType(String name, KnightClass knightClass, KnightStats stats, List<Skill> skills) {
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

    public List<Skill> getSkills() {
        return skills;
    }
}
