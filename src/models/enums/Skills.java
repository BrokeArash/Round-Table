package models.enums;

import models.Charm;

public enum Skills {
    //Commander
    ShieldBash("Shield Bash", 3, KnightClass.Commander, SkillType.Single, null,
            "deals damage to 1 enemy and stun him (skips the turn)"),
    BattleCry("battle Cry", 2, KnightClass.Commander, SkillType.GroupSelf, new Charm(CharmType.BUffAttack, 10),
            "buffs team attack"),
    Fortify("Fortify", 1, KnightClass.Commander, SkillType.GroupSelf, new Charm(CharmType.BuffDefense, 10),
            "increases team defense"),
    StrikeCommand("Strike Command", 5, KnightClass.Commander, SkillType.Group, null,
            "deals damage to all enemies"),
    ArmorBreak("Armor Break", 2, KnightClass.Commander, SkillType.Group, new Charm(CharmType.BuffDefense, -10),
            "lowers enemy defense"),
    Rally("Rally", 4, KnightClass.Commander, SkillType.GroupSelf, new Charm(CharmType.BuffHeal, 20),
            "heals 20% of all teammates HP"),

    //Warrior
    Slash("Slash", 2, KnightClass.Warrior, SkillType.Single, null,
            "basic attack"),
    HeavyStrike("Heavy Strike", 4, KnightClass.Warrior, SkillType.Single, null,
            "critical damage deals 150% of damage"),
    Rage("Rage", 1, KnightClass.Warrior, SkillType.SingleSelf, new Charm(CharmType.BUffAttack, 20),
            "buffs own attack"),
    LifeSteal("Life Steal", 5, KnightClass.Warrior, SkillType.Single, new Charm(CharmType.BuffHeal, 10),
            "basic damage + 10% heal himself"),
    Berserk("Berserk", 3, KnightClass.Warrior, SkillType.Single, new Charm(CharmType.HealTOAttack, 10),
            "decrease 20% of HP but adds 60% to attack"),
    TrialByCombat("Trial By Combat", 5, KnightClass.Warrior, SkillType.Single, null,
            "if you are chosen by gods you kill the enemy otherwise you die"),

    //Mage
    Fireball("Fireball", 2, KnightClass.Mage, SkillType.Single, null,
            "basic magic attack"),
    LightningStrike("Lightning Strike", 4, KnightClass.Mage, SkillType.Group, null,
            "deals magic + normal attack to all enemies"),
    IceBlast("Ice Blast", 3, KnightClass.Mage, SkillType.Single, new Charm(CharmType.BUffSpeed, -10),
            "deals damage and slows enemy"),
    ArcaneSurge("Arcane Surge", 3, KnightClass.Mage, SkillType.SingleSelf, new Charm(CharmType.BuffMagic, 10),
            "boosts own magic power"),
    MageRevive("Mage Revive", 5, KnightClass.Mage, SkillType.SingleOther, null,
            "bring back teammate with 10% of HP"),
    Silence("Silence", 4, KnightClass.Mage, SkillType.Single, null,
            "skips enemies turn"),

    //Healer
    Heal("Heal", 2, KnightClass.Healer, SkillType.SingleOther, new Charm(CharmType.BuffHeal, 20),
            "restore 20% of HP"),
    GroupHeal("Group Heal", 4, KnightClass.Healer, SkillType.GroupSelf, new Charm(CharmType.BuffHeal, 20),
            "restore 20% of HP of all team"),
    HealerRevive("Healer Revive", 4, KnightClass.Healer, SkillType.SingleOther, null,
            "bring back teammate with 30% of HP"),
    Cleanse("Cleanse", 3, KnightClass.Healer, SkillType.GroupSelf, null,
            "remove all debuffs"),
    LightStrike("Light Strike", 2, KnightClass.Healer, SkillType.Single, null,
            "small magic damage"),
    Blessing("Blessing", 3, KnightClass.Healer, SkillType.GroupSelf, new Charm(CharmType.BUffSpeed, 20),
            "buff speed of team 20%"),
    ;

    private final String name;
    private final int AP;
    private final KnightClass knightClass;
    private final SkillType type;
    private final Charm charms;
    private final String description;

    Skills(String name, int AP, KnightClass knightClass, SkillType type, Charm charms, String description) {
        this.name = name;
        this.AP = AP;
        this.knightClass = knightClass;
        this.type = type;
        this.charms = charms;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getAP() {
        return AP;
    }

    public KnightClass getKnightClass() {
        return knightClass;
    }

    public SkillType getType() {
        return type;
    }

    public Charm getCharms() {
        return charms;
    }

    public String getDescription() {
        return description;
    }
}
