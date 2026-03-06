package models.enums;

import views.AppMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainCommands implements Command {
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
