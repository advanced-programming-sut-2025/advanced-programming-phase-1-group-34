package org.Group34.model.enums.command.menu;

import org.Group34.model.enums.command.Command;

/**
 * Profile menu specified commands regex
 */

public enum ProfileMenuCommand implements Command {
    // Regex: ---------- START -----------
    CHANGE_USERNAME("change\\s+username\\s+-u\\s+(?<username>.*?)"),
    CHANGE_NICKNAME("change\\s+nickname\\s+-u\\s+(?<nickname>.*?)"),
    CHANGE_EMAIL("change\\s+email\\s+-e\\s+(?<email>.*?)"),
    CHANGE_PASSWORD("change\\s+password\\s+-p\\s+(?<newPassword>.*?)\\s+-o\\s+(?<oldPassword>.*?)"),

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
