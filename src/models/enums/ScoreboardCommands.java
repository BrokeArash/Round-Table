package models.enums;

public enum ScoreboardCommands implements Command {
    ShowCurrentMenu ("^\\s*show\\s+current\\s+menu\\s*$"),
    Exit ("^\\s*exit\\s*$"),
    ShowScoreboard("^\\s*show\\s+scoreboard\\s+-t\\s+(?<sort>.*)\\s*$"),
    Back("^\\s*back\\s*$"),
    ;



    private final String pattern;

    ScoreboardCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getMatcher() {
        return pattern;
    }
}
