package org.Group34.model.enums.command.menu;

import org.Group34.model.enums.command.Command;

/**
 * Profile menu specified commands regex
 */

public enum ProfileMenuCommand implements Command {
    // Regex: ---------- START -----------
    CHANGE_USERNAME("change\\s+username\\s+-u\\s+(?<username>\\S+)"),
    CHANGE_NICKNAME("change\\s+nickname\\s+-u\\s+(?<nickname>\\S+)"),
    CHANGE_EMAIL("change\\s+email\\s+-e\\s+(?<email>\\S+)"),
    CHANGE_PASSWORD("change\\s+password\\s+-p\\s+(?<newPassword>\\S+)\\s+-o\\s+(?<oldPassword>\\S+)"),

    ENTER_MAIN_MENU("menu\\s+enter\\s+main\\s+menu"),

    SHOW_INFO("user\\s+info"),

    SHOW_MENU("show\\s+current\\s+menu");
    // Regex: ---------- END -------------

    private final String regex;

    ProfileMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
