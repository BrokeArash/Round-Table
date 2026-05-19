package controllers.actions;

import models.BattleContext;
import models.Knight;
import models.Result;
import models.enums.CharmType;
import models.enums.Skill;

public class BuffAction implements SkillAction {

    private final double factor;
    private final CharmType type;
    private final boolean includeTeammate;

    public BuffAction(double factor, CharmType type, boolean includeTeammate) {
        this.factor = factor;
        this.type = type;
        this.includeTeammate = includeTeammate;
    }

    @Override
    public Result execute(Skill skill, BattleContext battleContext) {

        //CLEANSE
        if (type.equals(CharmType.Clean)) {
            resetCharm(battleContext.getCaster());
            resetCharm(battleContext.getAllyTeammate());
            return new Result(true, "debuffs removed\n");
        }
        Knight target = battleContext.getMainTarget();

        //BUFF
        if (factor > 1) {

            if (target == null) target = battleContext.getCaster();

            switch (type) {
                case Attack:
                    target.getCharm().setAttack(factor);
                    if (includeTeammate) battleContext.getAllyTeammate().getCharm().setAttack(factor);
                    break;
                case Magic:
                    target.getCharm().setMagic(factor);
                    if (includeTeammate) battleContext.getAllyTeammate().getCharm().setMagic(factor);
                    break;
                case Defense:
                    target.getCharm().setDefense(factor);
                    if (includeTeammate) battleContext.getAllyTeammate().getCharm().setDefense(factor);
                    break;
                case Speed:
                    target.getCharm().setSpeed(factor);
                    if (includeTeammate) battleContext.getAllyTeammate().getCharm().setSpeed(factor);
                    break;
            }

            //Team BUFF
            if (includeTeammate)
                return new Result(true, "team's " + type.toString() + " buffed by " + ((int)(factor*100)-100) + "%\n");
            return new Result(true, target.toString() + "'s " + type.toString() + " buffed by " + ((int)(factor*100)-100) + "%\n");

        }

        //NERF
        if (target == null) {
            target = battleContext.getEnemy();
        }

        switch (type) {
            case Attack:
                target.getCharm().setAttack(factor);
                if (includeTeammate) target.getTeammate().getCharm().setAttack(factor);
                break;
            case Magic:
                target.getCharm().setMagic(factor);
                if (includeTeammate) target.getTeammate().getCharm().setMagic(factor);
                break;
            case Defense:
                target.getCharm().setDefense(factor);
                if (includeTeammate) target.getTeammate().getCharm().setDefense(factor);
                break;
            case Speed:
                target.getCharm().setSpeed(factor);
                if (includeTeammate) target.getTeammate().getCharm().setSpeed(factor);
                break;
        }


        //Team NERF
        if (includeTeammate) return new Result(true, "enemy's " + type.toString() + " nerfed by " + (-(int)(factor*100) + 100) + "%\n");
        return new Result(true, target.toString() + "'s " + type.toString() + " nerfed by " + (-(int)(factor*100) + 100) + "%\n");
    }

    private void resetCharm(Knight k) {
        if (k.getCharm().getAttack() < 1) k.getCharm().setAttack(1);
        if (k.getCharm().getMagic() < 1) k.getCharm().setMagic(1);
        if (k.getCharm().getDefense() < 1) k.getCharm().setDefense(1);
        if (k.getCharm().getSpeed() < 1) k.getCharm().setSpeed(1);
    }
}
