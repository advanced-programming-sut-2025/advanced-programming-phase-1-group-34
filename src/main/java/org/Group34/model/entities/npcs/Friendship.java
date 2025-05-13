package org.Group34.model.entities.npcs;

import java.time.LocalDate;

public class Friendship {
    private int points;
    private LocalDate lastInteraction;
    private int level;

    public void addPoints(int amount) {
        points = Math.min(799, Math.max(0, points + amount));
        updateLevel();
    }

    private void updateLevel() {
        this.level = Math.min(3, points / 200);
    }

    public int getLevel() { return level; }
    public LocalDate getLastInteraction() { return lastInteraction; }
    public void setLastInteraction(LocalDate date) { this.lastInteraction = date; }
}