package org.Group34.model.entities;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.npcs.NPC;

public class NPCOnMap implements Entity {
    private final NPC npc;
    private Texture texture;
    private int x, y;

    public NPCOnMap(NPC npc, Texture texture, int x, int y) {
        this.npc = npc;
        this.texture = texture;
        this.x = x;
        this.y = y;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    public NPC getNpc() {
        return npc;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}