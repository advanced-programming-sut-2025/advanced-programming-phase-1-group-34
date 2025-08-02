package org.Group34.model.entities.npcs.quests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Quest {
    private int level;
    private String title;
    private String description;
    private int rewardGold;
    private RewardItem rewardItem;
    private boolean isCompleted = false;
    private String type;
    private int requiredFriendship;

    @JsonCreator
    public Quest(
            @JsonProperty("level") int level,
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("rewardGold") int rewardGold,
            @JsonProperty("rewardItem") RewardItem rewardItem
    ) {
        this.level = level;
        this.title = title;
        this.description = description;
        this.rewardGold = rewardGold;
        this.rewardItem = rewardItem;
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

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getRequiredFriendship() {
        return requiredFriendship;
    }

    public void setRequiredFriendship(int requiredFriendship) {
        this.requiredFriendship = requiredFriendship;
    }

    @Override
    public String toString() {
        return "Quest: " + title + " - " + (isCompleted ? "Completed" : "Incomplete");
    }
}
