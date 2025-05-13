package org.Group34.model.entities.npcs.quests;

import org.Group34.model.entities.Player;
import org.Group34.model.items.Item;

public class Quest {
    private String description;
    private Item requiredItem;
    private int requiredAmount;
    private Item reward;
    private int rewardAmount;
    private boolean isCompleted;
    private boolean isActive;
    private ActivationCondition activationCondition;

    public boolean canBeActivated(Player player) {
        return activationCondition.canActivate(player);
    }

    public boolean tryToComplete(Player player) {
        if (isCompleted || !isActive) return false;
        if (player.isExistInInventory(requiredItem) && player.getAmountOfItem(requiredItem) >= requiredAmount) {
            player.removeFromInventory(requiredItem, requiredAmount);
            player.addToInventory(reward, rewardAmount);
            isCompleted = true;
            return true;
        }
        return false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item getRequiredItem() {
        return requiredItem;
    }

    public void setRequiredItem(Item requiredItem) {
        this.requiredItem = requiredItem;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public Item getReward() {
        return reward;
    }

    public void setReward(Item reward) {
        this.reward = reward;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ActivationCondition getActivationCondition() {
        return activationCondition;
    }

    public int getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(int rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public void setActivationCondition(ActivationCondition activationCondition) {
        this.activationCondition = activationCondition;
    }
}

