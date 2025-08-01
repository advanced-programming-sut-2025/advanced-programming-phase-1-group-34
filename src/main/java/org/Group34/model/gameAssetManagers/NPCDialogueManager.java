package org.Group34.model.gameAssetManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.NPCOnMap;

import java.util.HashMap;
import java.util.Map;

public class NPCDialogueManager {
    private final Map<NPCOnMap, Float> npcProximityTime = new HashMap<>();
    private final Map<NPCOnMap, Boolean> npcDialogueIconVisible = new HashMap<>();
    private final Map<NPCOnMap, Float> npcDialogueIconVisibleTime = new HashMap<>();
    private final Texture dialogueIconTexture;

    // ثابت‌های زمانی
    private static final float TIME_TO_SHOW_ICON = 2.0f;

    public NPCDialogueManager() {
        dialogueIconTexture = new Texture(Gdx.files.internal("npcs/dialogue_icon.png"));
    }

    public void update(float delta, Iterable<NPCOnMap> npcs, int[] playerPos) {
        for (NPCOnMap npc : npcs) {
            int npcX = npc.getX();
            int npcY = npc.getY();

            boolean isAdjacent = Math.abs(npcX - playerPos[0]) <= 1 && Math.abs(npcY - playerPos[1]) <= 1;

            if (isAdjacent) {
                float currentTime = npcProximityTime.getOrDefault(npc, 0f);
                currentTime += delta;
                npcProximityTime.put(npc, currentTime);

                if (currentTime >= TIME_TO_SHOW_ICON && !npcDialogueIconVisible.containsKey(npc)) {
                    npcDialogueIconVisible.put(npc, true);
                    npcDialogueIconVisibleTime.put(npc, 0f);
                    Gdx.app.log("NPC Dialogue", "Icon appeared for " + npc.getNpc().getName());
                }
            } else {
                npcProximityTime.remove(npc);
                npcDialogueIconVisible.remove(npc);
                npcDialogueIconVisibleTime.remove(npc);
            }
        }

        for (Map.Entry<NPCOnMap, Float> entry : npcDialogueIconVisibleTime.entrySet()) {
            NPCOnMap npc = entry.getKey();
            float visibleTime = entry.getValue() + delta;
            npcDialogueIconVisibleTime.put(npc, visibleTime);        }
    }

    public boolean isDialogueIconVisible(NPCOnMap npc) {
        return npcDialogueIconVisible.containsKey(npc);
    }

    public Texture getDialogueIconTexture() {
        return dialogueIconTexture;
    }

    public void activateDialogue(NPCOnMap npc) {
        if (npcDialogueIconVisible.containsKey(npc)) {
            npcDialogueIconVisible.remove(npc);
            npcDialogueIconVisibleTime.remove(npc);
            Gdx.app.log("NPC Dialogue", "Dialogue activated for " + npc.getNpc().getName());
        }
    }

    public void dispose() {
        dialogueIconTexture.dispose();
    }
}