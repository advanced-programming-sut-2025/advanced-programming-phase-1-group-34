package org.Group34.model.entities.npcs;

import org.Group34.model.Time;
import org.Group34.model.entities.npcs.quests.Quest;
import org.Group34.model.enums.Season;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class NPC {
    private final String name;
    private final List<String> dialogues;
    private int friendshipPoints;
    private final List<String> likedItems;
    private final List<Quest> quests;

    @JsonCreator
    public NPC(
            @JsonProperty("name") String name,
            @JsonProperty("likedItems") List<String> likedItems,
            @JsonProperty("quests") List<Quest> questList,
            @JsonProperty("dialogues") List<String> dialogues
    ) {
        this.name = name;
        this.likedItems = likedItems != null ? likedItems : new ArrayList<>();
        this.dialogues = dialogues != null ? dialogues : new ArrayList<>();
        this.friendshipPoints = 0;

        this.quests = new ArrayList<>();
        if (questList != null) {
            this.quests.addAll(questList);
        }
    }



    public boolean completeQuest(Quest quest) {
        if (!quest.isCompleted()) {
            return false;
        }
        quest.completeQuest();
        return true;
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

    public List<Quest> getQuests() {
        return quests;
    }

    public List<String> getLikedItems() {
        return likedItems;
    }

    public boolean isQuestAvailable(Quest quest, Time time) {
        if (quest.getLevel() == 2 && this.getFriendshipPoints() >= 200) {
            return true;
        } else if (quest.getLevel() == 3 && time.getDate() > 5 + this.getName().charAt(0) - 65) {
            return true;
        }
        return true;
    }
}
