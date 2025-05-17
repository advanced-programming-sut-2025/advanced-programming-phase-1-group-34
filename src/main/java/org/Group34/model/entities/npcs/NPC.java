package org.Group34.model.entities.npcs;

import org.Group34.model.Time;
import org.Group34.model.entities.npcs.quests.Quest;
import org.Group34.model.enums.Season;

import java.util.*;

public class NPC {
    private final String name;
    private final List<String> dialogues;
    private int friendshipPoints;
    private final List<String> likedItems;
    private final LinkedHashMap<Quest, Boolean> quests;

    public NPC(String name, List<String> likedItems, List<Quest> questList, List<String> dialogues) {
        this.name = name;
        this.likedItems = likedItems;
        this.dialogues = dialogues;
        this.friendshipPoints = 0;

        this.quests = new LinkedHashMap<>();
        for (Quest q : questList) {
            this.quests.put(q, false);
        }
    }

    public boolean completeQuest(Quest quest) {
        if (!quests.get(quest)) {
            quests.put(quest, true);
            return true;
        }
        return false;
    }

    public String getDialogueBySeason(Season season) {
        int index = switch (season) {
            case SPRING -> 0;
            case SUMMER -> 1;
            case FALL -> 2;
            case WINTER -> 3;
            case ALL -> 0;
        };

        if (dialogues == null || index >= dialogues.size()) return "Hello.";
        return dialogues.get(index);
    }


    public void increaseFriendship(int amount) {
        friendshipPoints = Math.min(799, friendshipPoints + amount);
    }

    public int getFriendshipPoints() {
        return friendshipPoints;
    }


    public String getName() {
        return name;
    }

    public HashMap<Quest, Boolean> getQuests() {
        return quests;
    }

    public List<String> getLikedItems() {
        return likedItems;
    }

    public boolean isQuestAvailable(Quest quest, Time time) {
        if (quest.getLevel() == 2 && this.getFriendshipPoints() >= 200) {
            return true;
        }
        else if (quest.getLevel() == 3 && time.getDate() > 5 + this.getName().charAt(0) - 65) {
            return true;
        }
        return true;
    }
}