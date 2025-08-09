package org.Group34.model.interactions;

import org.Group34.model.entities.Player;
import org.Group34.model.items.Item;

import java.util.ArrayList;

public class Interaction {
    private int level = 0;
    private final int maxLevel = 4;
    private int xp = 0;
    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<Gift> gifts = new ArrayList<>();

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getXp() {
        return xp;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessage(String message, boolean received, boolean isNew) {
        messages.add(new Message(message, received, isNew));
    }

    public String talkHistory() {
        StringBuilder result = new StringBuilder();

        result.append(" ===== Talk History =====\n");
        for (Message message : messages) {
            result
                    .append("Message: " + message.getMessage() + "\n")
                    .append("Is Received: " + message.isReceived() + "\n\n");
        }

        return result.toString();
    }

    public void addGift(Item item, int amount, boolean received, boolean isNew, Player player) {
        gifts.add(new Gift(item, amount, received, isNew, player));
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public void increaseXp(int amount) {
        xp += amount;
        if (xp >= (level + 1) * 100 && level != maxLevel) {
            level++;
            xp -= level * 100;
        }
    }

    public String giftHistory() {
        StringBuilder result = new StringBuilder();

        result.append(" ===== Gift History =====\n");
        for (Gift gift : gifts) {
            result
                    .append("Item: " + gift.getItem().getName() + "\n")
                    .append("Amount: " + gift.getAmount() + "\n")
                    .append("Is Received: " + gift.isReceived() + "\n\n");
        }

        return result.toString();
    }
}
