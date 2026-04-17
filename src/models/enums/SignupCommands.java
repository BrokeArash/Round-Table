package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupCommands implements Command {
    ShowCurrentMenu ("^\\s*show\\s+current\\s+menu\\s*$"),
    Exit ("^\\s*exit\\s*$"),
    Signup ("^\\s*signup\\s+" +
            "-username\\s+(?<username>.*)\\s+" +
            "-password\\s+(?<password>.*)\\s*$"),
    Login ("\\s*login\\s+" +
            "-username\\s+(?<username>.*)\\s+" +
            "-password\\s+(?<password>.*)\\s*"),
    CheckUsername("^[a-zA-Z][a-zA-Z0-9_]*$"),
    CheckPass("^(?=[A-Za-z])(?=.+[%@#$^&!]).+$"),
    ;

    private final String pattern;

    SignupCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getMatcher() {
        return pattern;
    }
}
