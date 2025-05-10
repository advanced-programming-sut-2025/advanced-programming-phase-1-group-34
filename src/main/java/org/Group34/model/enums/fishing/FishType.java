package org.Group34.model.enums.fishing;

import org.Group34.model.enums.Season;

public enum FishType {
    // Regular fish
    SALMON("Salmon", 75, Season.AUTUMN, false),
    SARDINE("Sardine", 40, Season.AUTUMN, false),
    SHAD("Shad", 60, Season.AUTUMN, false),
    BLUE_DISCUS("Blue Discus", 120, Season.AUTUMN, false),
    MIDNIGHT_CARP("Midnight Carp", 150, Season.WINTER, false),
    SQUID("Squid", 80, Season.WINTER, false),
    TUNA("Tuna", 100, Season.WINTER, false),
    PERCH("Perch", 55, Season.WINTER, false),
    FLOUNDER("Flounder", 100, Season.SPRING, false),
    LIONFISH("Lionfish", 100, Season.SPRING, false),
    HERRING("Herring", 30, Season.SPRING, false),
    GHOSTFISH("Ghostfish", 45, Season.SPRING, false),
    TILAPIA("Tilapia", 75, Season.SUMMER, false),
    DORADO("Dorado", 100, Season.SUMMER, false),
    SUNFISH("Sunfish", 30, Season.SUMMER, false),
    RAINBOW_TROUT("Rainbow Trout", 65, Season.SUMMER, false),

    // Legendary fish
    LEGEND("Legend", 5000, Season.SPRING, true),
    GLACIERFISH("Glacierfish", 1000, Season.WINTER, true),
    ANGLER("Angler", 900, Season.AUTUMN, true),
    CRIMSONFISH("Crimsonfish", 1500, Season.SUMMER, true);

    public final String name;
    public final int basePrice;
    public final Season season;
    public final boolean isLegendary;

    FishType(String name, int price, Season season, boolean isLegendary) {
        this.name = name;
        this.basePrice = price;
        this.season = season;
        this.isLegendary = isLegendary;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

}