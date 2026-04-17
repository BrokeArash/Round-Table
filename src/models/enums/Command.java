package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    String getMatcher();
    default Matcher isMatch(String input){
        Matcher matcher = Pattern.compile(this.getMatcher()).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
