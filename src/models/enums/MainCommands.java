package models.enums;

public enum MainCommands implements Command {
    ShowCurrentMenu ("^\\s*show\\s+current\\s+menu\\s*$"),
    Exit ("^\\s*exit\\s*$"),
    LogOut("^\\s*logout\\s*$"),
    ShowKnightsDetails("^\\s*show\\s+knights\\s+details\\s*$"),
    ScoreBoard("^\\s*scoreboard\\s*$"),
    Play("^\\s*play\\s+against\\s+(?<username>.*)\\s*$"),
    ;

    private final String pattern;

    MainCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getMatcher() {
        return pattern;
    }
}
