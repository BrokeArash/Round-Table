package models.enums;

import models.Charm;
import models.Knight;
import models.Result;

import java.util.List;
import java.util.Random;

public enum Skills {
    //Commander
    ShieldBash("Shield Bash", 3, KnightClass.Commander, true,
            "deals damage to 1 enemy and stun him (skips the turn)") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int damageDealt = myKnight.getAttack() - (int)(enemyKnight.getDefense()*0.3);
            Random random = new Random();
            int dodge = random.nextInt(100);
            if (dodge < enemyKnight.getSpeed()/2) {
                return new Result(true, "enemy dodged!!!\n");
            }
            StringBuilder stringBuilder = new StringBuilder();
            enemyKnight.decreaseHP(damageDealt);
            enemyKnight.setStunned(true);
            stringBuilder.append("Shield Bash dealt ").append(damageDealt).append(" damage to ").append(enemyKnight.getKnight().getName()).append("\n");
            if (enemyKnight.getHP() <= 0) {
                enemyKnight.setDead(true);
                stringBuilder.append(enemyKnight.getKnight().getName()).append(" is dead!!!\n");
                return new Result(true, stringBuilder.toString());
            }
            return new Result(true, stringBuilder.toString());
        }
    },
//    BattleCry("battle Cry", 2, KnightClass.Commander, false,
//            "buffs team attack") {
//        @Override
//        public Result perform(Knight myKnight, Knight enemyKnight) {
//            myKnight.getCharm().setAttack(1.2);
//            myKnight.getTeammate().getCharm().setAttack(1.2);
//            return new Result(true, ("team's attack buffed by 20%\n"));
//        }
//    },
    Fortify("Fortify", 1, KnightClass.Commander, false,
            "increases team defense") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            myKnight.getCharm().setDefense(1.2);
            myKnight.getTeammate().getCharm().setDefense(1.2);
            return new Result(true, ("team's defense buffed by 20%\n"));
        }
    },
    StrikeCommand("Strike Command", 5, KnightClass.Commander, true,
            "deals damage to all enemies") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            boolean dodge1 = false;
            StringBuilder stringBuilder = new StringBuilder();

            // enemy 1
            int damageDealt = myKnight.getAttack() - (int)(enemyKnight.getDefense()*0.3);
            Random random = new Random();
            int dodge = random.nextInt(100);
            if (dodge < enemyKnight.getSpeed()/2) {
                dodge1 = true;
                stringBuilder.append(enemyKnight.getKnight().getName()).append(" dodged!!!\n");
            }
            if (!dodge1) {
                enemyKnight.decreaseHP(damageDealt);
                stringBuilder.append("Strike Command dealt ").append(damageDealt).append(" damage to ").append(enemyKnight.getKnight().getName()).append("\n");
                if (enemyKnight.getHP() <= 0) {
                    enemyKnight.setDead(true);
                    stringBuilder.append(enemyKnight.getKnight().getName()).append(" is dead!!!\n");
                }
            }

            //enemy 2
            damageDealt = myKnight.getAttack() - (int)(enemyKnight.getTeammate().getDefense()*0.3);
            random = new Random();
            dodge = random.nextInt(100);
            if (dodge < enemyKnight.getTeammate().getSpeed()/2) {
                stringBuilder.append(enemyKnight.getTeammate().getKnight().getName()).append(" dodged!!!\n");
                return new Result(true, stringBuilder.toString());
            }

            enemyKnight.getTeammate().decreaseHP(damageDealt);
            stringBuilder.append("Strike Command dealt ").append(damageDealt).append(" damage to ").append(enemyKnight.getTeammate().getKnight().getName()).append("\n");
            if (enemyKnight.getTeammate().getHP() <= 0) {
                enemyKnight.getTeammate().setDead(true);
                stringBuilder.append(enemyKnight.getTeammate().getKnight().getName()).append(" is dead!!!\n");
                return new Result(true, stringBuilder.toString());
            }
            return new Result(true, stringBuilder.toString());
        }
    },
    ArmorBreak("Armor Break", 2, KnightClass.Commander, true,
            "lowers enemy defense") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            enemyKnight.getCharm().setDefense(0.85);
            enemyKnight.getTeammate().getCharm().setDefense(0.85);
            return new Result(true, ("enemy's defense got nerfed by 15%\n"));
        }
    },
    Rally("Rally", 4, KnightClass.Commander, false,
            "heals 20% of all teammates HP") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int maxHP = myKnight.getKnight().getStats().HP();
            myKnight.decreaseHP((int)(maxHP*-0.2));
            maxHP = myKnight.getTeammate().getKnight().getStats().HP();
            myKnight.getTeammate().decreaseHP((int)(maxHP*-0.2));
            return new Result(true, "all of your Knights got healed by 20%\n");
        }
    },

    //Warrior
    Slash("Slash", 2, KnightClass.Warrior, true,
            "basic attack") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int damageDealt = myKnight.getAttack() - (int)(enemyKnight.getDefense()*0.3);
            Random random = new Random();
            int dodge = random.nextInt(100);
            if (dodge < enemyKnight.getSpeed()/2) {
                return new Result(true, "enemy dodged!!!\n");
            }
            StringBuilder stringBuilder = new StringBuilder();
            enemyKnight.decreaseHP(damageDealt);
            enemyKnight.setStunned(true);
            stringBuilder.append("Slash dealt ").append(damageDealt).append(" damage to ").append(enemyKnight.getKnight().getName()).append("\n");
            if (enemyKnight.getHP() <= 0) {
                enemyKnight.setDead(true);
                stringBuilder.append(enemyKnight.getKnight().getName()).append(" is dead!!!\n");
                return new Result(true, stringBuilder.toString());
            }
            return new Result(true, stringBuilder.toString());
        }
    },
    HeavyStrike("Heavy Strike", 4, KnightClass.Warrior, true,
            "critical damage deals 150% of damage") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int damageDealt = (int)(myKnight.getAttack()*1.5) - (int)(enemyKnight.getDefense()*0.3);
            Random random = new Random();
            int dodge = random.nextInt(100);
            if (dodge < enemyKnight.getSpeed()/2) {
                return new Result(true, "enemy dodged!!!\n");
            }
            StringBuilder stringBuilder = new StringBuilder();
            enemyKnight.decreaseHP(damageDealt);
            enemyKnight.setStunned(true);
            stringBuilder.append("Heavy Strike dealt ").append(damageDealt).append(" damage to ").append(enemyKnight.getKnight().getName()).append("\n");
            if (enemyKnight.getHP() <= 0) {
                enemyKnight.setDead(true);
                stringBuilder.append(enemyKnight.getKnight().getName()).append(" is dead!!!\n");
                return new Result(true, stringBuilder.toString());
            }
            return new Result(true, stringBuilder.toString());
        }
    },
//    Rage("Rage", 1, KnightClass.Warrior,
//            "buffs own attack") {
//        @Override
//        public Result perform(Knight myKnight, Knight enemyKnight) {
//            myKnight.getCharm().setAttack(1.5);
//            return new Result(true, myKnight.toString() + "'s attack buffed 50%\n");
//        }
//    },
    LifeSteal("Life Steal", 5, KnightClass.Warrior, true,
            "basic damage + 10% heal himself") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int maxHP = myKnight.getKnight().getStats().HP();
            myKnight.decreaseHP((int)(maxHP*-0.1));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("HP increased by 10%\n");
            int damageDealt = myKnight.getAttack() - (int)(enemyKnight.getDefense()*0.3);
            Random random = new Random();
            int dodge = random.nextInt(100);
            if (dodge < enemyKnight.getSpeed()/2) {
                return new Result(true, "enemy dodged!!!\n");
            }
            enemyKnight.decreaseHP(damageDealt);
            enemyKnight.setStunned(true);
            stringBuilder.append("Life Steal dealt ").append(damageDealt).append(" damage to ").append(enemyKnight.getKnight().getName()).append("\n");
            if (enemyKnight.getHP() <= 0) {
                enemyKnight.setDead(true);
                stringBuilder.append(enemyKnight.getKnight().getName()).append(" is dead!!!\n");
                return new Result(true, stringBuilder.toString());
            }
            return new Result(true, stringBuilder.toString());
        }
    },
    Berserk("Berserk", 3, KnightClass.Warrior, false,
            "decrease 20% of HP but adds 60% to attack") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(myKnight.toString()).append(" performed berserk\n");
            myKnight.getCharm().setHP(0.8);
            myKnight.getCharm().setAttack(1.6);
            if (myKnight.getHP() <= 0) {
                myKnight.setDead(true);
                stringBuilder.append(myKnight.toString()).append(" is dead!!!\n");
            }
            return new Result(true, stringBuilder.toString());
        }
    },
    TrialByCombat("Trial By Combat", 5, KnightClass.Warrior, true,
            "if you are chosen by gods you kill the enemy otherwise you die and winner gets full AP, if tied both die") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            Random myRandom = new Random();
            Random enemyRandom = new Random();
            StringBuilder stringBuilder = new StringBuilder();
            int myRand = myRandom.nextInt(100);
            int enemyRand = enemyRandom.nextInt(100);
            Knight winner = null;

            int myScore = (int)(myRand*0.8 + (myKnight.getAttack() + myKnight.getMagicAttack() + myKnight.getDefense())*0.2);
            int enemyScore = (int)(enemyRand*0.8 + (enemyKnight.getAttack() + enemyKnight.getMagicAttack() + enemyKnight.getDefense())*0.2);

            if (myScore < enemyScore) {
                myKnight.setDead(true);
                enemyKnight.addAP(5);
                winner = enemyKnight;
                stringBuilder.append(winner.toString()).append(" is chosen by the Gods\n");
            } else if (myScore > enemyScore) {
                enemyKnight.setDead(true);
                myKnight.addAP(5);
                winner = myKnight;
                stringBuilder.append(winner.toString()).append(" is chosen by the Gods\n");
            } else {
                myKnight.setDead(true);
                enemyKnight.setDead(true);
                stringBuilder.append("y'all are khodazade");
            }
            return new Result(true, stringBuilder.toString());
        }
    },

    //Mage
    Fireball("Fireball", 2, KnightClass.Mage, true,
            "basic magic attack") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            int damageDealt = myKnight.getMagicAttack();
            Random random = new Random();
            int dodge = random.nextInt(100);
            if (dodge < enemyKnight.getSpeed()/2) {
                return new Result(true, "enemy dodged!!!\n");
            }
            StringBuilder stringBuilder = new StringBuilder();
            enemyKnight.decreaseHP(damageDealt);
            enemyKnight.setStunned(true);
            stringBuilder.append("Fireball dealt ").append(damageDealt).append(" damage to ").append(enemyKnight.getKnight().getName()).append("\n");
            if (enemyKnight.getHP() <= 0) {
                enemyKnight.setDead(true);
                stringBuilder.append(enemyKnight.getKnight().getName()).append(" is dead!!!\n");
                return new Result(true, stringBuilder.toString());
            }
            return new Result(true, stringBuilder.toString());
        }
    },
    LightningStrike("Lightning Strike", 4, KnightClass.Mage, true,
            "deals magic + normal attack to all enemies") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            boolean dodge1 = false;
            StringBuilder stringBuilder = new StringBuilder();

            // enemy 1
            int damageDealt = myKnight.getMagicAttack();
            Random random = new Random();
            int dodge = random.nextInt(100);
            if (dodge < enemyKnight.getSpeed()/2) {
                dodge1 = true;
                stringBuilder.append(enemyKnight.getKnight().getName()).append(" dodged!!!\n");
            }
            if (!dodge1) {
                enemyKnight.decreaseHP(damageDealt);
                stringBuilder.append("Lightning Strike dealt ").append(damageDealt).append(" damage to ").append(enemyKnight.getKnight().getName()).append("\n");
                if (enemyKnight.getHP() <= 0) {
                    enemyKnight.setDead(true);
                    stringBuilder.append(enemyKnight.getKnight().getName()).append(" is dead!!!\n");
                }
            }

            //enemy 2
            damageDealt = myKnight.getMagicAttack();
            random = new Random();
            dodge = random.nextInt(100);
            if (dodge < enemyKnight.getTeammate().getSpeed()/2) {
                stringBuilder.append(enemyKnight.getTeammate().getKnight().getName()).append(" dodged!!!\n");
                return new Result(true, stringBuilder.toString());
            }

            enemyKnight.getTeammate().decreaseHP(damageDealt);
            stringBuilder.append("Lightning Strike dealt ").append(damageDealt).append(" damage to ").append(enemyKnight.getTeammate().getKnight().getName()).append("\n");
            if (enemyKnight.getTeammate().getHP() <= 0) {
                enemyKnight.getTeammate().setDead(true);
                stringBuilder.append(enemyKnight.getTeammate().getKnight().getName()).append(" is dead!!!\n");
                return new Result(true, stringBuilder.toString());
            }
            return new Result(true, stringBuilder.toString());
        }
    },
    IceBlast("Ice Blast", 3, KnightClass.Mage, true,
            "deals damage and slows enemy") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            enemyKnight.getCharm().setSpeed(0.8);
            int damageDealt = myKnight.getMagicAttack();
            Random random = new Random();
            int dodge = random.nextInt(100);
            if (dodge < enemyKnight.getSpeed()/2) {
                return new Result(true, "enemy dodged!!!\n");
            }
            StringBuilder stringBuilder = new StringBuilder();
            enemyKnight.decreaseHP(damageDealt);
            enemyKnight.setStunned(true);
            stringBuilder.append("Ice Blast slowed and dealt ").append(damageDealt).append(" damage to ").append(enemyKnight.getKnight().getName()).append("\n");
            if (enemyKnight.getHP() <= 0) {
                enemyKnight.setDead(true);
                stringBuilder.append(enemyKnight.getKnight().getName()).append(" is dead!!!\n");
                return new Result(true, stringBuilder.toString());
            }
            return new Result(true, stringBuilder.toString());
        }
    },
    ArcaneSurge("Arcane Surge", 3, KnightClass.Mage, false,
            "boosts own magic power") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            myKnight.getCharm().setMagic(1.3);
            return new Result(true, "magic attack got buffed ny 30%\n");
        }
    },
//    MageRevive("Mage Revive", 5, KnightClass.Mage,
//            "bring back teammate with 10% of HP") { //TODO: double check!!!
//        @Override
//        public Result perform(Knight myKnight, Knight teammate) {
//            if(!teammate.isDead()) {
//                return new Result(true, "teammate is not dead!!!\n");
//            } else {
//                teammate.setDead(false);
//                int hp = (int) (teammate.getKnight().getStats().HP() * 0.1);
//                teammate.setHP(hp);
//                return new Result(true, teammate.toString() + " got revived\n");
//            }
//        }
//    },
    Silence("Silence", 4, KnightClass.Mage, true,
            "stun enemy") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            enemyKnight.setStunned(true);
            return new Result(true, enemyKnight.toString() + " got stunned\n");
        }
    },

    //Healer
    Heal("Heal", 2, KnightClass.Healer, false,
            "restore 20% of HP") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            Knight teammate = myKnight.getTeammate();
            int hp = (int) (teammate.getKnight().getStats().HP()*0.2);
            teammate.decreaseHP(-hp);
            return new Result(true, teammate.toString() + " got healed\n");
        }
    },
    GroupHeal("Group Heal", 5, KnightClass.Healer, false,
            "restore 20% of HP of all team") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            Knight teammate = myKnight.getTeammate();
            int hp = (int) (teammate.getKnight().getStats().HP()*0.2);
            teammate.decreaseHP(-hp);

            hp = (int) (myKnight.getKnight().getStats().HP()*0.2);
            myKnight.decreaseHP(-hp);
            return new Result(true, "whole team got healed\n");
        }
    },
    HealerRevive("Healer Revive", 4, KnightClass.Healer, false,
            "bring back teammate with 30% of HP") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            Knight teammate = myKnight.getTeammate();
            if(!teammate.isDead()) {
                return new Result(true, "teammate is not dead!!!\n");
            } else {
                teammate.setDead(false);
                int hp = (int) (teammate.getKnight().getStats().HP() * 0.1);
                teammate.setHP(hp);
                return new Result(true, teammate.toString() + " got revived\n");
            }
        }
    },
    Cleanse("Cleanse", 3, KnightClass.Healer, false,
            "remove all debuffs") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            Knight teammate = myKnight.getTeammate();
            if (myKnight.getCharm().getAttack() < 1) myKnight.getCharm().setAttack(1);
            if (myKnight.getCharm().getMagic() < 1) myKnight.getCharm().setMagic(1);
            if (myKnight.getCharm().getHP() < 1) myKnight.getCharm().setHP(1);
            if (myKnight.getCharm().getDefense() < 1) myKnight.getCharm().setDefense(1);
            if (myKnight.getCharm().getSpeed() < 1) myKnight.getCharm().setSpeed(1);

            if (teammate.getCharm().getAttack() < 1) teammate.getCharm().setAttack(1);
            if (teammate.getCharm().getMagic() < 1) teammate.getCharm().setMagic(1);
            if (teammate.getCharm().getHP() < 1) teammate.getCharm().setHP(1);
            if (teammate.getCharm().getDefense() < 1) teammate.getCharm().setDefense(1);
            if (teammate.getCharm().getSpeed() < 1) teammate.getCharm().setSpeed(1);
            return new Result(true, "cleansed all debuffs\n");

        }
    },
//    LightStrike("Light Strike", 2, KnightClass.Healer,
//            "small magic damage") {
//        @Override
//        public Result perform(Knight myKnight, Knight enemyKnight) {
//            int damageDealt = myKnight.getMagicAttack();
//            Random random = new Random();
//            int dodge = random.nextInt(100);
//            if (dodge < enemyKnight.getSpeed()/2) {
//                return new Result(true, "enemy dodged!!!\n");
//            }
//            StringBuilder stringBuilder = new StringBuilder();
//            enemyKnight.decreaseHP(damageDealt);
//            enemyKnight.setStunned(true);
//            stringBuilder.append("Light Strike dealt ").append(damageDealt).append(" damage to ").append(enemyKnight.getKnight().getName()).append("\n");
//            if (enemyKnight.getHP() <= 0) {
//                enemyKnight.setDead(true);
//                stringBuilder.append(enemyKnight.getKnight().getName()).append(" is dead!!!\n");
//                return new Result(true, stringBuilder.toString());
//            }
//            return new Result(true, stringBuilder.toString());
//        }
//    },
    Blessing("Blessing", 3, KnightClass.Healer, false,
            "buff speed of team 20%") {
        @Override
        public Result perform(Knight myKnight, Knight enemyKnight) {
            Knight teammate = myKnight.getTeammate();
            myKnight.getCharm().setSpeed(1.2);
            teammate.getCharm().setSpeed(1.2);
            return new Result(true, teammate.toString() + " speed got buffed by 20%\n");
        }
    },
    ;


    private final String name;
    private final int AP;
    private final KnightClass knightClass;
    private final boolean enemy;
    private final String description;
    public abstract Result perform(Knight myKnight, Knight enemyKnight);


    Skills(String name, int AP, KnightClass knightClass, boolean enemy, String description) {
        this.name = name;
        this.AP = AP;
        this.knightClass = knightClass;
        this.enemy = enemy;
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

    public boolean isEnemy() {
        return enemy;
    }

    public String getDescription() {
        return description;
    }
}
