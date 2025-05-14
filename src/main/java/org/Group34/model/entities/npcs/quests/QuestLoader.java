package org.Group34.model.entities.npcs.quests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.Group34.model.entities.npcs.NPC;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class QuestLoader {
    public List<NPC> loadNPCs(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        return mapper.readValue(
                new File(filePath),
                new TypeReference<List<NPC>>(){}
        );
    }
}

//TODO add the following lines to load NPCs on game:
/*
* List<NPC> npcs = new QuestLoader().loadNPCs("npcs.json");
* NPC sebastian = npcs.get(0);
* System.out.println(sebastian.getRandomDialogue());
* */