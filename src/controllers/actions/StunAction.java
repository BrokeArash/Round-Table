package controllers.actions;

import models.BattleContext;
import models.Result;
import models.enums.Skill;

public class StunAction implements SkillAction {

    @Override
    public Result execute(Skill skill, BattleContext battleContext) {
        battleContext.getMainTarget().setStunned(true);
        return new Result(true, battleContext.getMainTarget().toString() + " got stunned\n");
    }
}
