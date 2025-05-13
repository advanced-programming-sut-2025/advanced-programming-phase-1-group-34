package org.Group34.model.entities.npcs.quests;

import org.Group34.model.entities.Player;
import org.Group34.model.entities.npcs.NPC;

public class FriendshipLevelCondition implements ActivationCondition {
    private NPC npc;
    private int requiredLevel;

    public FriendshipLevelCondition(NPC npc, int level) {
        this.npc = npc;
        this.requiredLevel = level;
    }

    @Override
    public boolean canActivate(Player player) {
        return player.getFriendshipLevel(npc) >= requiredLevel;
    }
}
