package org.Group34.model.enums.command.menu;

import org.Group34.model.enums.command.Command;

public enum GameCommands implements Command {
    // Regex: ---------- START -----------
    EXIT_GAME("\\s*exit\\s+game\\s*"),
    DELETE_GAME("\\s*delete\\s+game\\s*"),
    VOTE("\\s*(?<vote>[yes|no])\\s*"),
    NEXT_TURN("\\s*next\\s+turn\\s*"),
    CHEAT_ADVANCE_TIME("\\s*cheat\\s+advance\\s+time\\s+(?<hours>\\d+)h\\s*"),
    CHEAT_ADVANCE_DATE("\\s*cheat\\s+advance\\s+date\\s+(?<days>\\d+)d\\s*"),
    DISPLAY_TIME("^\\s*(?<type>time|date|datetime|day of week)\\s*$"),
    WALK("\\s*walk\\s+-l\\s+<(?<x>\\d+), (?<y>\\d+)>\\s*"),
    PRINT_MAP("\\s*print\\s+map\\s+-l\\s+<(?<x>\\d+), (?<y>\\d+)>\\s+-s\\s+(?<size>\\d+)\\s*"),
    HELP_READING_MAP("\\s*help\\s+reading\\s+map\\s*");
    ;
    // Regex: ---------- END -------------

    private final String regex;

    GameCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
