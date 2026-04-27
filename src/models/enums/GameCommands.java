package models.enums;

public enum GameCommands implements Command {
    ShowCurrentMenu ("^\\s*show\\s+current\\s+menu\\s*$"),
    Exit ("^\\s*exit\\s*$"),
    ShowTurn("^\\s*show\\s+turn\\s*$"),
    SkipTurn("^\\s*skip\\s+turn\\s*$"),
    ShowDetails("^\\s*show\\s+details\\s*$"),
    ShowStats("^\\s*show\\s+stats\\s+-k\\s+(?<knight>\\S+)\\s+-u\\s+(?<username>.*)\\s*$"),
    Attack("^\\s*attack\\s+-k\\s+(?<knight>\\S+)\\s*$"),
    Skill("^\\s*skill\\s+-s\\s+(?<skill>[^-]+?)(?:\\s+-k\\s+(?<knight>.+))?\\s*$"),

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
