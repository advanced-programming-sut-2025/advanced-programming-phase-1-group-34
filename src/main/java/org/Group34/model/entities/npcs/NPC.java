package org.Group34.model.entities.npcs;

import org.Group34.model.entities.Player;
import org.Group34.model.entities.npcs.quests.Quest;
import org.Group34.model.items.Item;

import java.util.*;
import java.util.stream.Collectors;

public class NPC {
    private String name;
    private String job;
    private Personality personality;
    private Schedule schedule;
    private Set<Item> lovedGifts;
    private Set<Item> hatedGifts;
    private Map<Player, Friendship> friendships = new HashMap<>();
    private List<Quest> quests = new ArrayList<>();

    public NPC(String name, String job, Personality personality, Schedule schedule,
               Set<Item> lovedGifts, Set<Item> hatedGifts) {
        this.name = name;
        this.job = job;
        this.personality = personality;
        this.schedule = schedule;
        this.lovedGifts = new HashSet<>(lovedGifts);
        this.hatedGifts = new HashSet<>(hatedGifts);
    }

    public List<Quest> getActiveQuests() {
        return quests.stream()
                .filter(q -> q.isActive() && !q.isCompleted())
                .collect(Collectors.toList());
    }

    public void updateQuests(Player player) {
        for (Quest quest : quests) {
            if (!quest.isActive() && !quest.isCompleted() && quest.canBeActivated(player)) {
                quest.setActive(true);
            }
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public Personality getPersonality() {
        return personality;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Set<Item> getLovedGifts() {
        return Collections.unmodifiableSet(lovedGifts);
    }

    public Set<Item> getHatedGifts() {
        return Collections.unmodifiableSet(hatedGifts);
    }

    public List<Quest> getQuests() {
        return Collections.unmodifiableList(quests);
    }

    public Friendship getFriendship(Player player) {
        return friendships.getOrDefault(player, new Friendship());
    }

    // Setters
    public void setJob(String job) {
        this.job = job;
    }

    public void setPersonality(Personality personality) {
        this.personality = personality;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void addLovedGift(Item item) {
        lovedGifts.add(item);
    }

    public void addHatedGift(Item item) {
        hatedGifts.add(item);
    }

    public void giveGift(Player player, Item item) {
        Friendship friendship = friendships.computeIfAbsent(player, k -> new Friendship());

        if (lovedGifts.contains(item)) {
            friendship.addPoints(20);
            System.out.println(name + " loves the gift!");
        }
        else if (hatedGifts.contains(item)) {
            friendship.addPoints(-10);
            System.out.println(name + " hates the gift!");
        }
        else {
            friendship.addPoints(5);
            System.out.println(name + " accepts the gift.");
        }
    }

    public void addQuest(Quest quest) {
        quests.add(quest);
    }

    public void interact(Player player) {
        Friendship friendship = friendships.computeIfAbsent(player, k -> new Friendship());
        friendship.addPoints(2);
        System.out.println(name + ": Hello " + player.getName() + "!");
    }
}

