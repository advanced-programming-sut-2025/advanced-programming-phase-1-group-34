package org.Group34.model.enums.command.menu;

import org.Group34.model.enums.command.Command;

public enum LoginMenuCommand implements Command {
    Login ("\\s*login\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)\\s*"),
    LoginWithSave ("\\s*login\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)\\s+-stay-logged-in\\s*"),
    ForgetPassword ("\\s*forget\\s+password\\s+-u\\s+(?<username>.+)\\s*"),

    SHOW_MENU("show\\s+current\\s+menu"),

    EXIT("menu\\s+exit");

    private final String regex;

    LoginMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
