package models.enums;

public enum GameCommands implements Command {
    ShowCurrentMenu ("^\\s*show\\s+current\\s+menu\\s*$"),
    Exit ("^\\s*exit\\s*$"),
    ShowTurn("\\s*show\\s+turn\\s*"),
    SkipTurn("\\s*skip\\s+turn\\s*"),
    ShowSkillsDetails("\\s*show\\s+skills\\s+details\\s*"),
    ShowCharms("\\s*show\\s+charms\\s*"),
    ShowAP("\\s*show\\s+AP\\s*"),
    ShowStats("\\s*show\\s+stats\\s+-k\\s+(?<knight>\\w+)\\s*"),
    ShowStatsEnemy("\\s*show\\s+stats\\s+-e\\s+(?<knight>\\w+)\\s*"),
    Attack("\\s*attack\\s+-k\\s+(?<knight>\\w+)\\s*"),
    Skill("\\s*skill\\s+-s\\s+(?<skill>[^-]+?)(?:\\s+-k\\s+(?<knight>\\w+))?\\s*"),

    ;

    private final String pattern;

    GameCommands(String pattern) {
        this.pattern = pattern;
    }


    @Override
    public String getMatcher() {
        return pattern;
    }
}
