package org.Group34.model.entities.npcs.quests;

import org.Group34.model.entities.Player;

public class TimePassedCondition implements ActivationCondition {
    private final int requiredDays;

    public TimePassedCondition(int days) {
        this.requiredDays = days;
    }

    @Override
    public boolean canActivate(Player player) {
        //TODO return player.getDaysPlayed() >= requiredDays;
    }
}