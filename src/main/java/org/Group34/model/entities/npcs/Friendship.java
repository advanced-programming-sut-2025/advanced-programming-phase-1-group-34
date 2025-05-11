package org.Group34.model.entities.npcs;

public class Friendship {
    private int points;
    private int level;

    public void addPoints(int amount) {
        points = Math.min(799, points + amount);
        level = Math.min(3, points / 200);
    }
}