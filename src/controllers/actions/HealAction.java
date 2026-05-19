package controllers.actions;

import models.BattleContext;
import models.Knight;
import models.Result;
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

        // BERSERK
        if (percent < 0) {
            heal(battleContext.getMainTarget(), percent);
            if (battleContext.getMainTarget().getHP() <= 0) {
            battleContext.getMainTarget().setDead(true);
            return new Result(true, battleContext.getMainTarget().toString() + " died!\n");
            }
            else return new Result(true, battleContext.getMainTarget().toString() + " lost " + -(int)(percent*100) + "% HP\n");
        }

        //REVIVE
        else if (revive) {

            if (!battleContext.getAllyTeammate().isDead() || battleContext.getCaster().equals(battleContext.getMainTarget())) {
                return new Result(true, battleContext.getMainTarget().toString() + " is not dead\n");
            }

            battleContext.getAllyTeammate().setDead(false);
            battleContext.getAllyTeammate().setAP(3);
            heal(battleContext.getAllyTeammate(), percent);

            return new Result(true, "teammate revived\n");
        }

        //HEAL
        else if (skill.isNeedDashK()) {
            heal(battleContext.getMainTarget(), percent);
            return new Result(true, battleContext.getMainTarget().toString() + " got healed by " + (int) (percent * 100) + "%\n");
        }

        //GROUP HEAL
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
