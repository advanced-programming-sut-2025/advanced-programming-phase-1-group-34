package org.Group34.model.gameAssetManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.Group34.model.entities.NPCOnMap;
import org.Group34.model.entities.npcs.NPC;
import org.Group34.model.entities.npcs.NPCData;
import org.Group34.model.map.Space;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NPCAssetManager {
    private final List<NPCOnMap> npcOnMaps = new ArrayList<>();

    public NPCAssetManager() {
    }

    public void initializeNPCs(Space currentSpace) {
        if (!npcOnMaps.isEmpty()) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            NPCData npcData = mapper.readValue(Gdx.files.internal("data/npcs.json").read(), NPCData.class);

            int mapWidth = currentSpace.width();
            int mapHeight = currentSpace.height();

            for (NPC npc : npcData.getNpc()) {
                String texturePath = "npcs/" + npc.getName().toLowerCase() + ".png";
                Texture texture = new Texture(Gdx.files.internal(texturePath));

                Random random = new Random();
                int x = random.nextInt(mapWidth - 10) + 5; // بین 5 تا mapWidth-5
                int y = random.nextInt(mapHeight - 10) + 5; // بین 5 تا mapHeight-5

                npcOnMaps.add(new NPCOnMap(npc, texture, x, y));
            }
        } catch (IOException e) {
            e.printStackTrace();
            createSampleNPC();
        }
    }

    private void createSampleNPC() {
        try {
            Texture texture = new Texture(Gdx.files.internal("player/female_player1.png"));
            NPC npc = new NPC("Sample", List.of("Flower"), List.of(), List.of("Hello!"));
            npcOnMaps.add(new NPCOnMap(npc, texture, 5, 5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NPCOnMap> getNpcOnMaps() {
        return npcOnMaps;
    }
}