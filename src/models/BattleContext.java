package models;

public class BattleContext {
    private final Knight caster;
    private Knight mainTarget;
    private final Knight enemy;
    private final Knight enemyTeammate;
    private final Knight allyTeammate;

    public BattleContext(Knight caster, Knight mainTarget) {
        this.caster = caster;
        this.allyTeammate = caster.getTeammate();

        if (mainTarget == null) {
            this.enemy = App.getGame().getOtherPlayer().getCurrentKnight();
            this.enemyTeammate = enemy.getTeammate();
        } else {
            this.mainTarget = mainTarget;
            this.enemy = mainTarget;
            this.enemyTeammate = mainTarget.getTeammate();
        }
    }

    public Knight getCaster() { return caster; }
    public Knight getMainTarget() { return mainTarget; }
    public Knight getEnemy() { return enemy; }
    public Knight getEnemyTeammate() { return enemyTeammate; }
    public Knight getAllyTeammate() { return allyTeammate; }
}
