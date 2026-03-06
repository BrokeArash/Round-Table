package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginCommands implements Command {
    ShowCurrentMenu ("^\\s*show\\s+current\\s+menu\\s*$"),
    Login ("\\s*login\\s+" +
            "-username\\s+(?<username>.*)\\s+" +
            "-password\\s+(?<password>.*)\\s*"),
    ForgetPass ("\\s*forget-password\\s+" +
            "-username\\s+(?<username>.*)\\s+" +
            "-email\\s+(?<email>.*)\\s*"),
    Back ("\\s*back\\s*"),
    Exit ("\\s*exit\\s*"),
    ;

    private final String pattern;

    LoginCommands(String pattern) {
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
