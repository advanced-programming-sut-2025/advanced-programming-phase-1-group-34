package org.Group34.model.entities.npcs.quests;

import org.Group34.model.entities.Player;

public class AlwaysActive implements ActivationCondition {
    @Override
    public boolean canActivate(Player player) {
        return true;
    }
}