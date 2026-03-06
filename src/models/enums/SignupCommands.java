package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupCommands implements Command {
    ShowCurrentMenu ("^\\s*show\\s+current\\s+menu\\s*$"),
    Exit ("^\\s*exit\\s*$"),
    Register ("^\\s*register\\s+" +
            "-username\\s+(?<username>.*)\\s+" +
            "-password\\s+(?<password>.*)\\s+" +
            "-email\\s+(?<email>.*)$"),
    Login ("\\s*login\\s*"),
    CheckUsername("^[a-zA-Z][a-zA-Z0-9_]*$"),
    CheckPass("^(?=[A-Za-z])(?=.+[%@#$^&!]).+$"),
    CheckEmail ("^[a-zA-Z0-9.]+@[a-zA-Z]+(?:\\.[a-zA-Z]+)?\\.com$"),
    ;

    private final String pattern;

    SignupCommands(String pattern) {
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
