package org.Group34.model.entities.npcs.quests;

import org.Group34.model.entities.Player;

public interface ActivationCondition {
    boolean canActivate(Player player);
}
