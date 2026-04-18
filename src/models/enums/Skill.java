package models.enums;

import models.Knight;
import models.Result;

public enum Skill {

    // ================= COMMANDER =================
    ShieldBash("shield bash", 3, true, true) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int dmg = calculatePhysicalDamage(myKnight, enemyKnight);
            return applyDamage(myKnight, enemyKnight, dmg, "shield bash", true);
        }
    },

    Fortify("fortify", 1, false, false) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            myKnight.getCharm().setDefense(1.2);
            myKnight.getTeammate().getCharm().setDefense(1.2);
            return new Result(true, "team's defense buffed by 20%\n");
        }
    },

    StrikeCommand("strike command", 5, true, false) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int dmg = calculatePhysicalDamage(myKnight, enemyKnight);
            return applyDamageToBoth(myKnight, enemyKnight, dmg, "strike command");
        }
    },

    ArmorBreak("armor break", 2, true, false) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            enemyKnight.getCharm().setDefense(0.85);
            enemyKnight.getTeammate().getCharm().setDefense(0.85);
            return new Result(true, "enemy defense reduced by 15%\n");
        }
    },

    Rally("rally", 4, false, false) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            heal(myKnight, 0.2);
            heal(myKnight.getTeammate(), 0.2);
            return new Result(true, "team healed by 20%\n");
        }
    },

    // ================= WARRIOR =================
    Slash("slash", 2, true, true) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int dmg = calculatePhysicalDamage(myKnight, enemyKnight);
            return applyDamage(myKnight, enemyKnight, dmg, "slash", false);
        }
    },

    HeavyStrike("heavy strike", 4, true, true) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int dmg = (int)(myKnight.getAttack() * 1.5)
                    - (int)(enemyKnight.getDefense() * 0.3);
            return applyDamage(myKnight, enemyKnight, dmg, "heavy strike", false);
        }
    },

    Rage("rage", 1, false, false) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            myKnight.getCharm().setAttack(1.5);
            return new Result(true, "attack buffed by 50%\n");
        }
    },

    LifeSteal("life steal", 5, true, true) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            heal(myKnight, 0.1);
            int dmg = calculatePhysicalDamage(myKnight, enemyKnight);

            Result r = applyDamage(myKnight, enemyKnight, dmg, "life steal", false);
            return new Result(true, "HP healed 10%\n" + r.message());
        }
    },

    Berserk("berserk", 3, false, false) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            myKnight.getCharm().setHP(0.8);
            myKnight.getCharm().setAttack(1.6);

            String msg = "berserk activated\n";

            if (myKnight.getHP() <= 0) {
                myKnight.setDead(true);
                msg += "you died!\n";
            }

            return new Result(true, msg);
        }
    },

    // ================= MAGE =================
    Fireball("fireball", 2, true, true) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int dmg = calculateMagicDamage(myKnight);
            return applyDamage(myKnight, enemyKnight, dmg, "fireball", false);
        }
    },

    LightningStrike("lightning strike", 4, true, false) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int dmg = calculateMagicDamage(myKnight);
            return applyDamageToBoth(myKnight, enemyKnight, dmg, "lightning strike");
        }
    },

    IceBlast("ice blast", 3, true, true) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            enemyKnight.getCharm().setSpeed(0.8);
            int dmg = calculateMagicDamage(myKnight);
            return applyDamage(myKnight, enemyKnight, dmg, "ice blast", false);
        }
    },

    ArcaneSurge("arcane surge", 3, false, false) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            myKnight.getCharm().setMagic(1.3);
            return new Result(true, "magic buffed by 30%\n");
        }
    },

    Silence("silence", 4, true, true) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            enemyKnight.setStunned(true);
            return new Result(true, "enemy stunned\n");
        }
    },

    // ================= HEALER =================
    Heal("heal", 2, false, true) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            heal(enemyKnight, 0.2);
            return new Result(true, "teammate healed\n");
        }
    },

    GroupHeal("group heal", 5, false, false) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            heal(myKnight, 0.2);
            heal(myKnight.getTeammate(), 0.2);
            return new Result(true, "team healed\n");
        }
    },

    HealerRevive("healer revive", 4, false, false) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            Knight t = myKnight.getTeammate();

            if (!t.isDead()) {
                return new Result(true, "teammate is not dead\n");
            }

            t.setDead(false);
            int hp = (int)(t.getKnight().getHP() * 0.1);
            t.setHP(hp);

            return new Result(true, "teammate revived\n");
        }
    },

    Cleanse("cleanse", 3, false, false) {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            resetCharm(myKnight);
            resetCharm(myKnight.getTeammate());
            return new Result(true, "debuffs removed\n");
        }
    },

    Blessing("blessing", 3, false, false) {
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
    private final boolean enemy;
    private final boolean needDashK;

    public abstract Result perform(Knight myKnight, Knight enemyKnight);

    Skill(String name, int AP, boolean enemy, boolean needDashK) {
        this.name = name;
        this.AP = AP;
        this.enemy = enemy;
        this.needDashK = needDashK;
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
                .append(target.toString())
                .append("\n");

        if (target.getHP() <= 0) {
            target.setDead(true);
            sb.append(target.toString()).append(" is dead!\n");
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
        int maxHP = target.getKnight().getHP();
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
    public boolean isEnemy() { return enemy; }
    public boolean isNeedDashK() { return needDashK; }
}