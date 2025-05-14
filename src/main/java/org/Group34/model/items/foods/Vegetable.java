package org.Group34.model.items.foods;

import java.util.Optional;

public enum Vegetable implements FarmingProduct {
    CARROT("Carrot", 35, true, 75, 33, ProcessedFood.CARROT_JUICE, ProcessedFood.CARROT_PICKLE),
    CAULIFLOWER("Cauliflower", 175, true, 75, 33, ProcessedFood.CAULIFLOWER_JUICE, ProcessedFood.CAULIFLOWER_PICKLE),
    GREEN_BEAN("Green Bean", 40, true, 25, 11, ProcessedFood.GREEN_BEAN_JUICE, ProcessedFood.GREEN_BEAN_PICKLE),
    KALE("Kale", 110, true, 50, 22, ProcessedFood.KALE_JUICE, ProcessedFood.KALE_PICKLE),
    PARSNIP("Parsnip", 35, true, 25, 11, ProcessedFood.PARSNIP_JUICE, ProcessedFood.PARSNIP_PICKLE),
    GARLIC("Garlic", 60, true, 20, 9, ProcessedFood.GARLIC_JUICE, ProcessedFood.GARLIC_PICKLE),
    TOMATO("Tomato", 60, true, 20, 9, ProcessedFood.TOMATO_JUICE, ProcessedFood.TOMATO_PICKLE),
    PUMPKIN("Pumpkin", 320, false, 0, 0, ProcessedFood.PUMPKIN_JUICE, ProcessedFood.PUMPKIN_PICKLE),
    RADISH("Radish", 90, true, 45, 20, ProcessedFood.RADISH_JUICE, ProcessedFood.RADISH_PICKLE),

    POTATO("Potato", 80, true, 25, 11, ProcessedFood.POTATO_JUICE, ProcessedFood.POTATO_PICKLE),
    RED_CABBAGE("Red Cabbage", 260, true, 75, 33, ProcessedFood.RED_CABBAGE_JUICE, ProcessedFood.RED_CABBAGE_PICKLE),
    CORN("Corn", 50, true, 25, 11, ProcessedFood.CORN_JUICE, ProcessedFood.CORN_PICKLE),
    YAM("Yam", 160, true, 45, 20, ProcessedFood.YAM_JUICE, ProcessedFood.YAM_PICKLE),
    AMARANTH("Amaranth", 150, true, 50, 22, ProcessedFood.AMARANTH_JUICE, ProcessedFood.AMARANTH_PICKLE),
    ARTICHOKE("Artichoke", 160, true, 30, 13, ProcessedFood.ARTICHOKE_JUICE, ProcessedFood.ARTICHOKE_PICKLE),
    BEET("Beet", 100, true, 30, 13, ProcessedFood.BEET_JUICE, ProcessedFood.BEET_PICKLE),
    BOK_CHOY("Bok Choy", 80, true, 25, 11, ProcessedFood.BOK_CHOY_JUICE, ProcessedFood.BOK_CHOY_PICKLE),
    BROCCOLI("Broccoli", 70, true, 63, 28, ProcessedFood.BROCCOLI_JUICE, ProcessedFood.BROCCOLI_PICKLE),
    HOT_PEPPER("Hot Pepper", 40, true, 13, 5, ProcessedFood.HOT_PEPPER_JUICE, ProcessedFood.HOT_PEPPER_PICKLE),
    EGGPLANT("Eggplant", 60, true, 20, 9, ProcessedFood.EGGPLANT_JUICE, ProcessedFood.EGGPLANT_PICKLE),
    SUMMER_SQUASH("Summer Squash", 45, true, 63, 28, ProcessedFood.SUMMER_SQUASH_JUICE, ProcessedFood.SUMMER_SQUASH_PICKLE);

    private final String name;
    private final int baseSellPrice;
    private final boolean isEdible;
    private final int energy;
    private final int health;
    private final ProcessedFood juiceForm;
    private final ProcessedFood pickleForm;

    Vegetable(String name, int price, boolean edible, int energy, int health, ProcessedFood juiceForm, ProcessedFood pickleForm) {
        this.name = name;
        this.baseSellPrice = price;
        this.isEdible = edible;
        this.energy = energy;
        this.health = health;
        this.juiceForm = juiceForm;
        this.pickleForm = pickleForm;
    }

    public String getName() { return name; }
    public int getBaseSellPrice() { return baseSellPrice; }
    public boolean isEdible() { return isEdible; }
    public int getEnergy() { return energy; }
    public int getHealth() { return health; }

    public ProcessedFood getJuiceForm() {
        return juiceForm;
    }

    public ProcessedFood getPickleForm() {
        return pickleForm;
    }
}
