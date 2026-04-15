package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainCommands implements Command {
    ShowCurrentMenu ("^\\s*show\\s+current\\s+menu\\s*$"),
    Exit ("^\\s*exit\\s*$"),
    KnightsDetails("\\s*knights\\s+details\\s*"),
    CustomizeKnight                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ("\\s*customize\\s+knight\\s*"),
    Play("\\s*play\\s+against\\s+(?<username>.*)\\s*"),
    LogOut("\\s*logout\\s*"),
    ;

    private final String pattern;

    MainCommands(String pattern) {
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
