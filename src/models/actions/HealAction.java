package models.actions;

import models.BattleContext;
import models.Knight;
import models.Result;
import models.enums.KnightClass;
import models.enums.Skill;

public class HealAction implements SkillAction {
    private final double percent;
    private final boolean revive;


    public HealAction(double percent, boolean revive) {
        this.percent = percent;
        this.revive = revive;
    }

    @Override
    public Result execute(Skill skill, BattleContext battleContext) {

        //if (battleContext.getCaster().getType().getKnightClass().equals(KnightClass.Warrior)) {

        if (percent < 0) {
            heal(battleContext.getCaster(), percent);
            if (battleContext.getCaster().getHP() <= 0) {
            battleContext.getCaster().setDead(true);
            return new Result(true, "you died!\n");
            }
            else return new Result(true, "you lost " + -(int)(percent*100) + "% HP\n");
        }
        //return new  Result(true, "you got healed by " + (int)(percent*100) + "%\n");
        //}

        else if (revive) {

            if (!battleContext.getAllyTeammate().isDead() || battleContext.getCaster().equals(battleContext.getMainTarget())) {
                return new Result(true, battleContext.getMainTarget().toString() + " is not dead\n");
            }

            battleContext.getAllyTeammate().setDead(false);
            battleContext.getAllyTeammate().setAP(3);
            heal(battleContext.getAllyTeammate(), percent);

            return new Result(true, "teammate revived\n");
        }

        else if (skill.isNeedDashK()) {
            heal(battleContext.getMainTarget(), percent);
            return new Result(true, battleContext.getMainTarget().toString() + " got healed by " + (int) (percent * 100) + "%\n");
        }

        heal(battleContext.getCaster(), percent);
        heal(battleContext.getAllyTeammate(), percent);

        return  new Result(true, "team got healed by " + (int)(percent*100) + "%\n");

    }

    private void heal(Knight target, double percent) {
        int maxHP = target.getType().getHP();
        int amount = (int)(maxHP * percent);
        if (target.getHP() + amount > maxHP) target.setHP(maxHP);
        else target.decreaseHP(-amount);
    }
}
