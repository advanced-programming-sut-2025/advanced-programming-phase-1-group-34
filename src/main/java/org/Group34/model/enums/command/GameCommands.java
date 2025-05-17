package org.Group34.model.enums.command;

public enum GameCommands implements Command {
    // Regex: ---------- START -----------
    EXIT_GAME("\\s*exit\\s+game\\s*"),
    DELETE_GAME("\\s*delete\\s+game\\s*"),
    VOTE("\\s*(?<vote>[yes|no])\\s*"),
    NEXT_TURN("\\s*next\\s+turn\\s*"),
    CHEAT_ADVANCE_TIME("\\s*cheat\\s+advance\\s+time\\s+(?<hours>\\d+)h\\s*"),
    CHEAT_ADVANCE_DATE("\\s*cheat\\s+advance\\s+date\\s+(?<days>\\d+)d\\s*"),
    CHEAT_CHANGE_WEATHER("\\s*cheat\\s+change\\s+weather\\s+(?<weather>\\S+)\\s*"),
    CHEAT_ADD_MONEY("\\s*cheat\\s+add\\s+money\\s+(?<amount>\\d+)\\s*"),
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
    INVENTORY_PLACE_ITEM("\\s*place\\s+item\\s+-n\\s+(?<itemName>.+)\\s+-d\\s+(?<direction>.+)\\s*"),
    CHEAT_ADD_ITEM("\\s*cheat\\s+add\\s+item\\s+-n\\s+(?<itemName>.+)\\s+-c\\s+(?<count>\\d+)\\s*"),
    // -------------------------

    // ----- HouseMenu Commands -----
    SHOW_RECIPES("\\s*crafting\\s+show\\s+recipes\\s*"),
    CRAFT_ITEM("\\s*crafting\\s+craft\\s+(?<itemName>.+)\\s*"),
    // -------------------------

    // ----- Artisan Commands -----
    ARTISAN_USE("\\s*artisan\\s+use\\s+(?<artisanName>.+)\\s+(?<item1Name>.+)(\\s+(?<item2Name))?\\s*"),
    ARTISAN_GET("\\s+artisan\\s+get\\s+(?<artisanName>.+)\\s+"),
    // -------------------------

    // ----- Time Commands -----
    SHOW_TIME("\\s*time\\s*"),
    SHOW_DATE("\\s*date\\s*"),
    SHOW_DATETIME("\\s*datetime\\s*"),
    SHOW_WEEKDAY("\\s*day\\s+of\\s+the\\s+week\\s*"),
    // -------------------------

    // ----- Season Commands -----
    SHOW_SEASON("\\s*season\\s*"),
    // -------------------------

    // ----- Weather Commands -----
    SHOW_TODAY_WEATHER("\\s*weather\\s*"),
    SHOW_TOMORROW_WEATHER("\\s*weather\\s+forecast\\s*"),
    // -------------------------

    // ----- Greenhouse Commands -----
    ENTER_GREENHOUSE("\\s*enter\\s+greenhouse\\s*"),
    BUILD_GREENHOUSE("\\s*greenhouse\\s+build\\s*"),
    // -------------------------

    // ----- Animals Commands -----
    BUILD_ANIMALS_PLACEMENT("\\s*build\\s+-a\\s+(?<buildingName>.+)\\s+-l\\s+(?<x>\\d+)\\s+,\\s+(?<y>\\d+)\\s*"),
    BUY_ANIMAL("\\s*buy\\s+animal\\s+-a\\s+(?<animal>.+)\\s+-n\\s+(?<name>.+)\\s*"),
    PET_ANIMAL("\\s*pet\\s+-n\\s+(?<name>.+)\\s*"),
    LIST_ANIMALS("\\s*animals\\s*"),
    SHEPHERD_ANIMAL("\\s*shepherd\\s+animals\\s+-n\\s+(?<animalName>.+)\\s+-l\\s+(?<x>\\d+)\\s+,\\s+(?<y>\\d+)\\s*"),
    FEED_ANIMAL("\\s*feed\\s+hay\\s+-n\\s+(?<animalName>.+)\\s*"),
    SHOW_PRODUCTS("\\s*produces\\s*"),
    COLLECT_PRODUCTS("\\s*collect\\s+produce\\s+-n\\s+(?<animalName>.+)\\s*"),
    SELL_ANIMAL("\\s*sell\\s+animal\\s+-n\\s+(?<animalName>.+)\\s*"),
    CHEAT_SET_FRIENDSHIP("\\s*cheat\\s+set\\s+friendship\\s+-n\\s+(?<animalName>.+)\\s+-c\\s+(<amount>\\d+)\\s*"),
    // -------------------------

    // ----- Fishing Commands -----
    START_FISHING("\\s*fishing\\s+-p\\s+(?<fishingPole>.+)\\s*"),
    // -------------------------

    // ----- NPC Commands -----
    MEET_NPC("\\s*meet\\s+NPC\\s+(?<npcName>.+)\\s*"),
    SEND_GIFT("\\s*gift\\s+NPC\\s+(?<npcName>.+)\\s+-i\\s+(?<itemName>.+)\\s*"),
    LIST_NPC_FRIENDSHIP("\\s*friendship\\s+NPC\\s+list\\s*"),
    LIST_AVAILABLE_QUESTS("\\s*available\\s+quests\\s*"),
    COMPLETE_QUEST("\\s*complete\\s+quest\\s+ -n\\s+(?<npcName>.+)\\s+-i\\s+(?<questNumber>.+)\\s*"),
    // -------------------------

    // ----- Shop Commands -----
    SHOW_ALL_PRODUCTS("\\s*show\\s+all\\s+products\\s*"),
    SHOW_AVAILABLE_PRODUCTS("\\s*show\\s+all\\s+available\\s+products\\s*"),
    PURCHASE("\\s*purchase\\s+(?<productName>.+)\\s*"),
    PURCHASE_WITH_COUNT("\\s*purchase\\s+(?<productName>.+)\\s+-n\\s+(?<count>\\d+)\\s*"),
    CHEAT_ADD_DOLLARS("\\s*cheat\\s+add\\s+(?<count>[0-9]+)\\s+dollars\\s*"),
    SELL("\\s*sell\\s+(?<productName>.+)\\s*"),
    SELL_WITH_COUNT("\\s*sell\\s+(?<productName>.+)\\s+-n\\s+(?<count>[0-9]+)\\s*");
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
