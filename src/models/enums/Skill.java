package models.enums;

import models.Knight;
import models.Result;

public enum Skill {

    // ================= COMMANDER =================
    ShieldBash("Shield Bash", 3, KnightClass.Commander, true, true,
            "deals damage to 1 enemy and stun him") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int dmg = calculatePhysicalDamage(myKnight, enemyKnight);
            return applyDamage(myKnight, enemyKnight, dmg, "Shield Bash", true);
        }
    },

    Fortify("Fortify", 1, KnightClass.Commander, false, false,
            "increases team defense by 20%") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            myKnight.getCharm().setDefense(1.2);
            myKnight.getTeammate().getCharm().setDefense(1.2);
            return new Result(true, "team's defense buffed by 20%\n");
        }
    },

    StrikeCommand("Strike Command", 5, KnightClass.Commander, true, false,
            "deals damage to all enemies") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int dmg = calculatePhysicalDamage(myKnight, enemyKnight);
            return applyDamageToBoth(myKnight, enemyKnight, dmg, "Strike Command");
        }
    },

    ArmorBreak("Armor Break", 2, KnightClass.Commander, true, false,
            "lowers enemy defense") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            enemyKnight.getCharm().setDefense(0.85);
            enemyKnight.getTeammate().getCharm().setDefense(0.85);
            return new Result(true, "enemy defense reduced by 15%\n");
        }
    },

    Rally("Rally", 4, KnightClass.Commander, false, false,
            "heals team") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            heal(myKnight, 0.2);
            heal(myKnight.getTeammate(), 0.2);
            return new Result(true, "team healed by 20%\n");
        }
    },

    // ================= WARRIOR =================
    Slash("Slash", 2, KnightClass.Warrior, true, true,
            "basic attack") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int dmg = calculatePhysicalDamage(myKnight, enemyKnight);
            return applyDamage(myKnight, enemyKnight, dmg, "Slash", true);
        }
    },

    HeavyStrike("Heavy Strike", 4, KnightClass.Warrior, true, true,
            "150% damage") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int dmg = (int)(myKnight.getAttack() * 1.5)
                    - (int)(enemyKnight.getDefense() * 0.3);
            return applyDamage(myKnight, enemyKnight, dmg, "Heavy Strike", true);
        }
    },

    Rage("Rage", 1, KnightClass.Warrior, false, false,
            "buffs own attack") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            myKnight.getCharm().setAttack(1.5);
            return new Result(true, "attack buffed by 50%\n");
        }
    },

    LifeSteal("Life Steal", 5, KnightClass.Warrior, true, true,
            "damage + heal") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            heal(myKnight, 0.1);
            int dmg = calculatePhysicalDamage(myKnight, enemyKnight);

            Result r = applyDamage(myKnight, enemyKnight, dmg, "Life Steal", true);
            return new Result(true, "HP healed 10%\n" + r.message());
        }
    },

    Berserk("Berserk", 3, KnightClass.Warrior, false, false,
            "lose HP, gain attack") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            myKnight.getCharm().setHP(0.8);
            myKnight.getCharm().setAttack(1.6);

            String msg = "Berserk activated\n";

            if (myKnight.getHP() <= 0) {
                myKnight.setDead(true);
                msg += "you died!!!\n";
            }

            return new Result(true, msg);
        }
    },

    // ================= MAGE =================
    Fireball("Fireball", 2, KnightClass.Mage, true, true,
            "magic damage") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int dmg = calculateMagicDamage(myKnight);
            return applyDamage(myKnight, enemyKnight, dmg, "Fireball", true);
        }
    },

    LightningStrike("Lightning Strike", 4, KnightClass.Mage, true, false,
            "hits both enemies") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int dmg = calculateMagicDamage(myKnight);
            return applyDamageToBoth(myKnight, enemyKnight, dmg, "Lightning Strike");
        }
    },

    IceBlast("Ice Blast", 3, KnightClass.Mage, true, true,
            "damage + slow") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            enemyKnight.getCharm().setSpeed(0.8);
            int dmg = calculateMagicDamage(myKnight);
            return applyDamage(myKnight, enemyKnight, dmg, "Ice Blast", true);
        }
    },

    ArcaneSurge("Arcane Surge", 3, KnightClass.Mage, false, false,
            "boost magic") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            myKnight.getCharm().setMagic(1.3);
            return new Result(true, "magic buffed by 30%\n");
        }
    },

    Silence("Silence", 4, KnightClass.Mage, true, true,
            "stun enemy") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            enemyKnight.setStunned(true);
            return new Result(true, "enemy stunned\n");
        }
    },

    // ================= HEALER =================
    Heal("Heal", 2, KnightClass.Healer, false, true,
            "heal self or teammate") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            heal(enemyKnight, 0.2);
            return new Result(true, "teammate healed\n");
        }
    },

    GroupHeal("Group Heal", 5, KnightClass.Healer, false, false,
            "heal team") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            heal(myKnight, 0.2);
            heal(myKnight.getTeammate(), 0.2);
            return new Result(true, "team healed\n");
        }
    },

    HealerRevive("Healer Revive", 4, KnightClass.Healer, false, false,
            "revive teammate") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            Knight t = myKnight.getTeammate();

            if (!t.isDead()) {
                return new Result(true, "teammate is not dead\n");
            }

            t.setDead(false);
            int hp = (int)(t.getKnight().getStats().HP() * 0.1);
            t.setHP(hp);

            return new Result(true, "teammate revived\n");
        }
    },

    Cleanse("Cleanse", 3, KnightClass.Healer, false, false,
            "remove debuffs") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            resetCharm(myKnight);
            resetCharm(myKnight.getTeammate());
            return new Result(true, "debuffs removed\n");
        }
    },

    Blessing("Blessing", 3, KnightClass.Healer, false, false,
            "speed buff") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            myKnight.getCharm().setSpeed(1.2);
            myKnight.getTeammate().getCharm().setSpeed(1.2);
            return new Result(true, "speed buffed\n");
        }
    };

    // ================= FIELDS =================
    private final String name;
    private final int AP;
    private final KnightClass knightClass;
    private final boolean enemy;
    private final boolean needDashK;
    private final String description;

    public abstract Result perform(Knight myKnight, Knight enemyKnight);

    Skill(String name, int AP, KnightClass knightClass, boolean enemy, boolean needDashK, String description) {
        this.name = name;
        this.AP = AP;
        this.knightClass = knightClass;
        this.enemy = enemy;
        this.needDashK = needDashK;
        this.description = description;
    }

    // ================= HELPERS =================
    protected int calculatePhysicalDamage(Knight attacker, Knight defender) {
        return attacker.getAttack() - (int)(defender.getDefense() * 0.3);
    }

    protected int calculateMagicDamage(Knight attacker) {
        return attacker.getMagicAttack();
    }

    protected Result applyDamage(Knight attacker, Knight target, int dmg, String skill, boolean stun) {
        StringBuilder sb = new StringBuilder();
        attacker.addTotalDamageDealt(dmg);
        target.decreaseHP(dmg);

        if (stun) target.setStunned(true);

        sb.append(skill)
                .append(" dealt ")
                .append(dmg)
                .append(" damage to ")
                .append(target.getKnight().getName())
                .append("\n");

        if (target.getHP() <= 0) {
            target.setDead(true);
            sb.append(target.getKnight().getName()).append(" is dead!!!\n");
        }

        return new Result(true, sb.toString());
    }

    protected Result applyDamageToBoth(Knight attacker, Knight enemy, int dmg, String skill) {
        StringBuilder sb = new StringBuilder();

        sb.append(applyDamage(attacker, enemy, dmg, skill, false).toString());
        sb.append(applyDamage(attacker, enemy.getTeammate(), dmg, skill, false).toString());

        return new Result(true, sb.toString());
    }

    protected void heal(Knight target, double percent) {
        int maxHP = target.getKnight().getStats().HP();
        int amount = (int)(maxHP * percent);
        if (target.getHP() + amount > maxHP) target.setHP(maxHP);
        else target.decreaseHP(-amount);
    }

    protected void resetCharm(Knight k) {
        if (k.getCharm().getAttack() < 1) k.getCharm().setAttack(1);
        if (k.getCharm().getMagic() < 1) k.getCharm().setMagic(1);
        if (k.getCharm().getHP() < 1) k.getCharm().setHP(1);
        if (k.getCharm().getDefense() < 1) k.getCharm().setDefense(1);
        if (k.getCharm().getSpeed() < 1) k.getCharm().setSpeed(1);
    }

    // ================= GETTERS =================
    public String getName() { return name; }
    public int getAP() { return AP; }
    public KnightClass getKnightClass() { return knightClass; }
    public boolean isEnemy() { return enemy; }
    public boolean isNeedDashK() { return needDashK; }
    public String getDescription() { return description; }
}