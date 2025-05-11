package org.Group34.model.items.foods;

public enum ProcessedFoodType {
    CHEESE("Cheese"),
    JUICE("Juice"),
    MAYONNAISE("Mayonnaise"),
    OIL("Oil"),
    JAM("Jam"),
    DEHYDRATED_FOOD("Dehydrated"),
    PICKLE("Pickle"),
    SMOKED_FISH("Smoked");

    private String name;

    ProcessedFoodType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
