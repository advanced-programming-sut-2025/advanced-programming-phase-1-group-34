package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.Time;
import org.Group34.model.entities.npcs.NPC;
import org.Group34.model.entities.npcs.quests.Quest;
import org.Group34.model.enums.Season;

import java.util.ArrayList;
import java.util.List;

public class NPCController {
    NPC npc;

    NPCController(NPC npc) {
        this.npc = npc;
    }

    public Result meetNPC(NPC npc, Season season) {
        npc.increaseFriendship(20);
        return new Result(true, npc.getDialogueBySeason(season));
    }

    public Result sendGift(NPC npc, String gift) {
        if (npc.getLikedItems().contains(gift)) {
            npc.increaseFriendship(200);
            return new Result(true, "Friendship increased by 200 points!");
        }
        else {
            npc.increaseFriendship(50);
            return new Result(true, "Friendship increased by 50 points!");
        }
    }

    public Result doQuest(NPC npc, int questID, Time time) {
        List<Quest> questList = new ArrayList<>(npc.getQuests());

        Quest quest = questList.get(questID);

        if (questID == 2 && npc.getFriendshipPoints() >= 200) {
            boolean success = npc.completeQuest(quest);

            if (success) {
                return new Result(true, "Quest completed!");
            }
            else {
                return new Result(false, "Quest was completed before");
            }
        }
        else if (questID == 3 && time.getDate() >= npc.getName().charAt(0) - 64) {
            boolean success = npc.completeQuest(quest);

            if (success) {
                return new Result(true, "Quest completed!");
            }
            else {
                return new Result(false, "Quest was completed before");
            }
        }
        else {
            boolean success = npc.completeQuest(quest);

            if (success) {
                return new Result(true, "Quest completed!");
            }
            else {
                return new Result(false, "Quest was completed before");
            }
        }
    }
}
