package org.Group34.model.entities.npcs;

import org.Group34.model.entities.Player;
import org.Group34.model.entities.npcs.quests.Quest;
import org.Group34.model.items.Item;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class NPC {
    private final String name;
    private String job;
    private Personality personality;
    private Schedule schedule;
    private final Set<Item> lovedGifts;
    private final Set<Item> hatedGifts;
    private final Map<Player, Friendship> friendships = new HashMap<>();
    private final List<Quest> quests = new ArrayList<>();
    private final String homeLocation;

    private LocalDate lastInteractionDate;
    private LocalDate lastGiftDate;

    public NPC(String name, String job, Personality personality, Schedule schedule,
               Set<Item> lovedGifts, Set<Item> hatedGifts, String homeLocation) {
        this.name = name;
        this.job = job;
        this.personality = personality;
        this.schedule = schedule;
        this.lovedGifts = new HashSet<>(lovedGifts);
        this.hatedGifts = new HashSet<>(hatedGifts);
        this.homeLocation = homeLocation;
    }

    // --- Interaction ---
    public void interact(Player player) {
        Friendship friendship = friendships.computeIfAbsent(player, k -> new Friendship());

        if (!LocalDate.now().equals(lastInteractionDate)) {
            friendship.addPoints(20);
            lastInteractionDate = LocalDate.now();
        } else {
            friendship.addPoints(2); // Repeated interactions give fewer points
        }

        System.out.println(name + ": " + generateDialogue(player));
    }

    private String generateDialogue(Player player) {
        Friendship friendship = friendships.getOrDefault(player, new Friendship());
        int level = friendship.getLevel();

        if (level < 20) {
            return "Hello. Have a nice day.";
        } else if (level < 100) {
            return "Hey " + player.getName() + "! I'm glad to see you.";
        } else {
            return "Always happy to see you, old friend!";
        }
    }

    // --- Giving Gifts ---
    public void giveGift(Player player, Item item) {
//        if (item.getCategory() == ItemCategory.TOOL) {
//            System.out.println("Tools cannot be given as gifts!");
//            return;
//        }

        Friendship friendship = friendships.computeIfAbsent(player, k -> new Friendship());

        if (!LocalDate.now().equals(lastGiftDate)) {
            if (lovedGifts.contains(item)) {
                friendship.addPoints(200);
                System.out.println(name + " loves this gift!");
            } else if (hatedGifts.contains(item)) {
                friendship.addPoints(-10);
                System.out.println(name + " doesn't like this gift.");
            } else {
                friendship.addPoints(50);
                System.out.println(name + " accepted the gift.");
            }
            lastGiftDate = LocalDate.now();
        } else {
            System.out.println(name + " has already received a gift today.");
        }
    }

    // --- Quests ---
    public void updateQuests(Player player) {
        for (Quest quest : quests) {
            if (!quest.isActive() && !quest.isCompleted() && quest.canBeActivated(player)) {
                quest.setActive(true);
            }
        }
    }

    public List<Quest> getActiveQuests() {
        return quests.stream()
                .filter(q -> q.isActive() && !q.isCompleted())
                .collect(Collectors.toList());
    }

    // --- Getters ---
    public String getName() { return name; }
    public String getJob() { return job; }
    public Personality getPersonality() { return personality; }
    public Schedule getSchedule() { return schedule; }
    public Set<Item> getLovedGifts() { return Collections.unmodifiableSet(lovedGifts); }
    public Set<Item> getHatedGifts() { return Collections.unmodifiableSet(hatedGifts); }
    public List<Quest> getQuests() { return Collections.unmodifiableList(quests); }
    public Friendship getFriendship(Player player) {
        return friendships.getOrDefault(player, new Friendship());
    }
    public String getHomeLocation() { return homeLocation; }

    // --- Setters ---
    public void setJob(String job) { this.job = job; }
    public void setPersonality(Personality personality) { this.personality = personality; }
    public void setSchedule(Schedule schedule) { this.schedule = schedule; }

    // --- Adders ---
    public void addLovedGift(Item item) { lovedGifts.add(item); }
    public void addHatedGift(Item item) { hatedGifts.add(item); }
    public void addQuest(Quest quest) { quests.add(quest); }
}
