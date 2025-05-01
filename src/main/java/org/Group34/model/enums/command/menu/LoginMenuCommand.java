package org.Group34.model.enums.command.menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommand {
    Login ("\\s*login\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)\\s*"),
    LoginWithSave ("\\s*login\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)\\s+–stay-logged-in\\s*"),
    ForgetPassword ("\\s*forget\\s+password\\s+-u\\s+<username>\\s*");

    private String pattern;

    LoginMenuCommand(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
