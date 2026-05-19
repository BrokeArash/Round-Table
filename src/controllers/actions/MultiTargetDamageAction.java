package controllers.actions;

import models.BattleContext;
import models.Knight;
import models.Result;
import models.enums.KnightClass;
import models.enums.Skill;

public class MultiTargetDamageAction implements SkillAction {

    @Override
    public Result execute(Skill skill, BattleContext battleContext) {
        Knight caster = battleContext.getCaster();
        Knight e1 = battleContext.getEnemy();
        Knight e2 = battleContext.getEnemyTeammate();

        int d1 = caster.getAttack() - (int)(e1.getDefense() * 0.3);
        int d2 = caster.getAttack() - (int)(e2.getDefense() * 0.3);

        if (caster.getType().getKnightClass().equals(KnightClass.Mage)) {
            d1 = caster.getMagicAttack();
            d2 = caster.getMagicAttack();
        }



        return applyDamageToBoth(caster, e1, d1, d2, skill.getName());
    }

    private Result applyDamageToBoth(Knight attacker, Knight enemy, int dmg, int dmgTeammate, String skill) {
        StringBuilder sb = new StringBuilder();

        sb.append(DamageAction.applyDamage(attacker, enemy, dmg, skill).toString());
        sb.append(DamageAction.applyDamage(attacker, enemy.getTeammate(), dmgTeammate, skill).toString());

        return new Result(true, sb.toString());
    }

}