package org.Group34.model.enums;

import org.Group34.model.items.Item;
import org.Group34.model.items.foods.ProcessedFood;

public enum FishType implements Item {
    // Regular fish
    SALMON("Salmon", 75, Season.FALL, false, ProcessedFood.SMOKED_SALMON),
    SARDINE("Sardine", 40, Season.FALL, false, ProcessedFood.SMOKED_SARDINE),
    SHAD("Shad", 60, Season.FALL, false, ProcessedFood.SMOKED_SHAD),
    BLUE_DISCUS("Blue Discus", 120, Season.FALL, false, ProcessedFood.SMOKED_BLUE_DISCUS),
    MIDNIGHT_CARP("Midnight Carp", 150, Season.WINTER, false, ProcessedFood.SMOKED_MIDNIGHT_CARP),
    SQUID("Squid", 80, Season.WINTER, false, ProcessedFood.SMOKED_SQUID),
    TUNA("Tuna", 100, Season.WINTER, false, ProcessedFood.SMOKED_TUNA),
    PERCH("Perch", 55, Season.WINTER, false, ProcessedFood.SMOKED_PERCH),
    FLOUNDER("Flounder", 100, Season.SPRING, false, ProcessedFood.SMOKED_FLOUNDER),
    LIONFISH("Lionfish", 100, Season.SPRING, false, ProcessedFood.SMOKED_LIONFISH),
    HERRING("Herring", 30, Season.SPRING, false, ProcessedFood.SMOKED_HERRING),
    GHOSTFISH("Ghostfish", 45, Season.SPRING, false, ProcessedFood.SMOKED_GHOSTFISH),
    TILAPIA("Tilapia", 75, Season.SUMMER, false, ProcessedFood.SMOKED_TILAPIA),
    DORADO("Dorado", 100, Season.SUMMER, false, ProcessedFood.SMOKED_DORADO),
    SUNFISH("Sunfish", 30, Season.SUMMER, false, ProcessedFood.SMOKED_SUNFISH),
    RAINBOW_TROUT("Rainbow Trout", 65, Season.SUMMER, false, ProcessedFood.SMOKED_RAINBOW_TROUT),

    // Legendary fish
    LEGEND("Legend", 5000, Season.SPRING, true, ProcessedFood.SMOKED_LEGEND),
    GLACIERFISH("Glacierfish", 1000, Season.WINTER, true, ProcessedFood.SMOKED_GLACIERFISH),
    ANGLER("Angler", 900, Season.FALL, true, ProcessedFood.SMOKED_ANGLER),
    CRIMSONFISH("Crimsonfish", 1500, Season.SUMMER, true, ProcessedFood.SMOKED_CRIMSONFISH),
    ALL("All", 0, Season.ALL, true, null);

    public final String name;
    public final int basePrice;
    public final Season season;
    public final boolean isLegendary;
    public final ProcessedFood smokedForm; // New field

    FishType(String name, int price, Season season, boolean isLegendary, ProcessedFood smokedForm) {
        this.name = name;
        this.basePrice = price;
        this.season = season;
        this.isLegendary = isLegendary;
        this.smokedForm = smokedForm;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public ProcessedFood getSmokedForm() {
        return smokedForm;
    }
}
