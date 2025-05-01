package org.Group34.model.enums.command.menu;

import org.Group34.model.enums.command.Command;

/**
 * Game menu specified commands regex
 */

public enum GameMenuCommands implements Command {
    // Regex: ---------- START -----------
    GAME_NEW("\\s*game\\s+new\\s+-u(?:\\s+(?<usernames>\\S.+))?\\s*"),
    GAME_MAP("\\s*game\\s+map\\s+(?<map_number>\\d+)\\s*"),
    LOAD_GAME("\\s*load\\s+game\\s*"),
    SHOW_MENU("show\\s+current\\s+menu");
    // Regex: ---------- END -------------

    private final String regex;

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
