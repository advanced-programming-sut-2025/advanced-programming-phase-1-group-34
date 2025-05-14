package org.Group34.model.enums.command;

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
    HELP_READING_MAP("\\s*help\\s+reading\\s+map\\s*"),

    // ----- Farming Commands -----
    CRAFT_INFO("\\s*craftinfo\\s+-n\\s+(?<craftName>.+)\\s*"),
    PLANT("\\s*plant\\s+-s\\s+(?<seed>.+)\\s+-d\\s+(?<direction>.+)\\s*"),
    SHOW_PLANT("\\s*showplant\\s+-l\\s+<(?<x>\\d+), (?<y>\\d+)>\\s*"),
    FERTILIZE("\\s*fertilize\\s+-f\\s+(?<fertilizer>.+)\\s+-d\\s+(?<direction>.+)\\s*"),
    HOW_MUCH_WATER("\\S*howmuch\\S+water\\S*"),
    // ----------------------------

    // ----- Tools Commands -----
    TOOLS_EQUIP("\\s*tools\\s+equip\\s+(?<toolName>.+)\\s*"),
    SHOW_CURRENT_TOOLS("\\s*tools\\s+show\\s+current\\s*"),
    SHOW_AVAILABLE_TOOLS("\\s*tools\\s+show\\s+available\\s*"),
    TOOLS_UPGRADE("\\s*tools\\s+upgrade\\s+(?<toolName>.+)\\s*"),
    TOOLS_USE("\\s*tools\\s+use\\s+-d\\s+(?<direction>.+)\\s*"),
    // -------------------------

    // ----- Inventory Commands -----
    INVENTORY_SHOW("\\s*inventory\\s+show\\s*"),
    INVENTORY_TRASH("\\s*inventory\\s+trash\\s+-i\\s+(?<itemName>.+)\\s+-n\\s+(?<number>\\d+)\\s*"),
    INVENTORY_PLACE_ITEM("\\s*place\\s+item\\s+-n\\s+(?<item_name>.+)\\s+-d\\s+(?<direction>.+)\\s*"),
    CHEAT_ADD_ITEM("\\s*cheat\\s+add\\s+item\\s+-n\\s+(?<item_name>.+)\\s+-c\\s+(?<count>\\d+)\\s*"),
    // -------------------------

    // ----- HouseMenu Commands -----
    SHOW_RECIPES("\\s*crafting\\s+show\\s+recipes\\s*"),
    CRAFT_ITEM("\\s*crafting\\s+craft\\s+(?<item_name>.+)\\s*"),
    // -------------------------

    // ----- Artisan Commands -----
    ARTISAN_USE("\\s*artisan\\s+use\\s+(?<artisan_name>.+)\\s+(?<item1_name>.+)(\\s+(?<item2_name))?\\s*"),
    ARTISAN_GET("\\s+artisan\\s+get\\s+(?<artisan_name>.+)\\s+");
    // -------------------------




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
