package models.enums;

import controllers.actions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Skill {

    // ================= COMMANDER =================
    ShieldBash("shield bash", 3,
            new ArrayList<>(Arrays.asList(new DamageAction(), new StunAction())), true, true) {
    },

    Fortify("fortify", 1,
            new ArrayList<>(Arrays.asList(new BuffAction(1.2, CharmType.Defense, true))), false, false) {
    },

    StrikeCommand("strike command", 5,
            new ArrayList<>(Arrays.asList(new MultiTargetDamageAction())), true, false) {
    },

    ArmorBreak("armor break", 2,
            new ArrayList<>(Arrays.asList(new BuffAction(0.85, CharmType.Defense, true))), true, false) {

    },

    Rally("rally", 4,
            new ArrayList<>(Arrays.asList(new HealAction(0.2, false))), false, false) {
    },

    // ================= WARRIOR =================
    Slash("slash", 2,
            new ArrayList<>(Arrays.asList(new DamageAction())), true, true) {

    },

    HeavyStrike("heavy strike", 4,
            new ArrayList<>(Arrays.asList(new DamageAction())), true, true) {

    },

    LifeSteal("life steal", 3,
            new ArrayList<>(Arrays.asList(new DamageAction(), new BuffAction(0.8, CharmType.Attack, false))),  true, true) {

    },

    Berserk("berserk", 3,
            new ArrayList<>(Arrays.asList( new HealAction(-0.2, false), new BuffAction(1.6, CharmType.Attack, false))), false, true) {

    },

    // ================= MAGE =================
    Fireball("fireball", 2,
            new ArrayList<>(Arrays.asList(new DamageAction())), true, true) {
    },

    LightningStrike("lightning strike", 4,
            new ArrayList<>(Arrays.asList(new MultiTargetDamageAction())), true, false) {

    },

    IceBlast("ice blast", 3,
            new ArrayList<>(Arrays.asList(new DamageAction(), new BuffAction(0.8, CharmType.Speed, false))), true, true) {

    },

    ArcaneSurge("arcane surge", 3,
            new ArrayList<>(Arrays.asList(new BuffAction(1.3, CharmType.Magic, false))), false, true) {

    },

    Silence("silence", 4,
            new ArrayList<>(Arrays.asList(new StunAction())), true, true) {
    },

    // ================= HEALER =================
    Heal("heal", 2,
            new ArrayList<>(Arrays.asList(new HealAction(0.4, false))), false, true) {
    },

    GroupHeal("group heal", 5,
            new ArrayList<>(Arrays.asList(new HealAction(0.2, false))), false, false) {
    },

    Revive("revive", 4,
            new ArrayList<>(Arrays.asList(new HealAction(0.1, true))), false, true) {
    },

    Cleanse("cleanse", 3,
            new ArrayList<>(Arrays.asList(new BuffAction(1, CharmType.Clean, true))), false, false) {
    },
    ;

    // ================= FIELDS =================
    private final String name;
    private final int AP;
    private final List<SkillAction> actions;
    private final boolean enemy;
    private final boolean needDashK;


    Skill(String name, int AP, List<SkillAction> actions, boolean enemy, boolean needDashK) {
        this.name = name;
        this.AP = AP;
        this.actions = actions;
        this.enemy = enemy;
        this.needDashK = needDashK;
    }

    // ================= GETTERS =================
    public String getName() { return name; }
    public int getAP() { return AP; }
    public List<SkillAction> getActions() {
        return actions;
    }
    public boolean isEnemy() { return enemy; }
    public boolean isNeedDashK() { return needDashK; }
}