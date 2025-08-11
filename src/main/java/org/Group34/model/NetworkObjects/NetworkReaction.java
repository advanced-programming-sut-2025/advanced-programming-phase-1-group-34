package org.Group34.model.NetworkObjects;

import java.io.Serializable;

public class NetworkReaction implements Serializable {
    private String player;
    private String texture;

    public NetworkReaction(String player, String texture) {
        this.player = player;
        this.texture = texture;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }
}
