package controllers.actions;

import models.BattleContext;
import models.Knight;
import models.Result;
import models.enums.KnightClass;
import models.enums.Skill;

public class DamageAction implements SkillAction {

    public DamageAction() {}

    @Override
    public Result execute(Skill skill, BattleContext battleContext) {
        Knight caster = battleContext.getCaster();
        Knight target = battleContext.getMainTarget();
        int dmg = caster.getAttack() - (int)(target.getDefense() * 0.3);
        if (skill.equals(Skill.HeavyStrike)) dmg = (int)(caster.getAttack() * 1.5) - (int)(target.getDefense() * 0.3);
        if (caster.getType().getKnightClass().equals(KnightClass.Mage)) dmg = caster.getMagicAttack();

        return applyDamage(caster, target, dmg, skill.getName());
    }

    public static Result applyDamage(Knight attacker, Knight target, int dmg, String skill) {
        StringBuilder sb = new StringBuilder();
        attacker.addTotalDamageDealt(dmg);
        target.decreaseHP(dmg);

        sb.append(skill)
                .append(" dealt ")
                .append(dmg)
                .append(" damage to ")
                .append(target.toString())
                .append("\n");

        if (target.getHP() <= 0) {
            target.setDead(true);
            target.setStunned(false);
            sb.append(target.toString()).append(" is dead!\n");
        }

        return new Result(true, sb.toString());
    }
}
