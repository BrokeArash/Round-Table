package models.actions;

import models.BattleContext;
import models.Result;
import models.enums.Skill;

public interface SkillAction {
    Result execute(Skill skill, BattleContext battleContext);
}
