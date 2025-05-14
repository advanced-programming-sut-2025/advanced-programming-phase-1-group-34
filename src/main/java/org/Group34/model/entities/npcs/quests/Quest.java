package org.Group34.model.entities.npcs.quests;

public class Quest {
    private final int level;
    private final String title;
    private final String description;
    private boolean isCompleted;
    private final int rewardGold;
    private final RewardItem rewardItem;

    public Quest(int level, String title, String description, int rewardGold, RewardItem rewardItem) {
        this.level = level;
        this.title = title;
        this.description = description;
        this.rewardItem = rewardItem;
        this.isCompleted = false;
        this.rewardGold = rewardGold;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getLevel() {
        return level;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public RewardItem getRewardItem() {
        return rewardItem;
    }

    public int getRewardGold() {
        return rewardGold;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void completeQuest() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        return "Quest: " + title + " - " + (isCompleted ? "Completed" : "Incomplete");
    }
}
