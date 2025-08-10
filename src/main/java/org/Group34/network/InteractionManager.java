package org.Group34.network;

import org.Group34.model.NetworkObjects.NetworkInteraction;

public class InteractionManager {
    private NetworkInteraction lastInteraction = null;

    public synchronized NetworkInteraction getLastInteraction() {
        return lastInteraction;
    }

    public synchronized void setLastInteraction(NetworkInteraction lastInteraction) {
        this.lastInteraction = lastInteraction;
    }
}
