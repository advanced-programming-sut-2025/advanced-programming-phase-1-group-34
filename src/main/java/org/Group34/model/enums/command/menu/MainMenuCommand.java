package main.java.org.Group34.model.enums.command.menu;

import main.java.org.Group34.model.enums.command.Command;

/**
 * Main menu specified commands regex
 */

public enum MainMenuCommand implements Command {
    // Regex: ---------- START -----------
    ENTER_PROFILE_MENU("menu\\s+enter\\s+profile\\s+menu"),
    ENTER_GAME_MENU("menu\\s+enter\\s+game\\s+menu"),

    LOGOUT("user\\s+logout"),

    SHOW_MENU("show\\s+current\\s+menu");
    // Regex: ---------- END -------------
    
    private final String regex;

    MainMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
