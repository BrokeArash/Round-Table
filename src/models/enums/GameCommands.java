package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands implements Command {
    ShowCurrentMenu ("^\\s*show\\s+current\\s+menu\\s*$"),
    Exit ("^\\s*exit\\s*$"),
    Attack("\\s*attack\\s+-k\\s+(?<knight>\\w+)\\s*"),
    SkillsDetails("\\s*skills\\s+details\\s*"),
    ShowAP("\\s*show\\s+AP\\s*"),
    SkipTurn("\\s*skip\\s+turn\\s*"),
    ShowStats("\\s*show\\s+stats\\s+-k\\s+(?<knight>\\w+)\\s*"),
    ShowCharms("\\s*show\\s+charms\\s*"),
    Skill("\\s*skill\\s+-s\\s+(?<skill>\\w+)(?:\\s+-k\\s+(?<knight>\\w+))?\\s*"),
    ShowTurn("\\s*show\\s+turn\\s*"),
    ;

    private final String pattern;

    GameCommands(String pattern) {
        this.pattern = pattern;
    }


    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
