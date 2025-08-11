package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Player;
import org.Group34.model.gameAssetManagers.ReactionAssetManager;
import org.Group34.model.interactions.Gift;
import org.Group34.model.interactions.Interaction;
import org.Group34.model.items.Item;

import java.util.Objects;


public class InteractionController {
    public Result talk(String username, String message, Player player) {
        if (username.startsWith("all")) {
            for (Interaction value : player.getInteractions().values()) {
                value.addMessage(message, false, false);
                value.increaseXp(20);
            }
            for (Player player1 : player.getInteractions().keySet()) {
                player1.getInteractionByPlayer(player).addMessage(message, true, true);
                player1.getInteractionByPlayer(player).increaseXp(20);
            }
            return new Result(true, "Your message has been sent to all players");
        }

        Player desiredPlayer = player.getOtherPlayerByName(username);

        if (desiredPlayer == null) {
            return new Result(false, "This user does not exist.");
        } else if (Objects.equals(message, "")) {
            return new Result(false, "The message is empty.");
        }

        player.getInteractionByPlayer(desiredPlayer).addMessage(message, false, false);
        desiredPlayer.getInteractionByPlayer(player).addMessage(message, true, true);

        player.getInteractionByPlayer(desiredPlayer).increaseXp(20);
        desiredPlayer.getInteractionByPlayer(player).increaseXp(20);

        return new Result(true, "Your message has been sent successfully.");
    }

    public Result talkHistory(String username, Player player) {
        Player desiredPlayer = player.getOtherPlayerByName(username);

        if (desiredPlayer == null) {
            return new Result(false, "This user does not exist.");
        }

        return new Result(true, player.getInteractionByPlayer(desiredPlayer).talkHistory());
    }

    public Result gift(String username, String item, int amount, Player player) {
        Player desiredPlayer = player.getOtherPlayerByName(username);

        if (desiredPlayer == null) {
            return new Result(false, "This user does not exist.");
        }
//        else if (player.getInteractionByPlayer(desiredPlayer).getLevel() < 1) {
//            return new Result(false, "the level of friendship is not enough.");
//        }
        else if (!haveEnoughItem(item, player, amount)) {
            return new Result(false, "You do not have enough of this item.");
        }

        Item desiredItem = player.getItemFromInventoryByName(item);

        player.removeFromInventory(desiredItem, amount);
        desiredPlayer.addToInventory(desiredItem, amount);

        player.getInteractionByPlayer(desiredPlayer).addGift(desiredItem, amount, false, false, desiredPlayer);
        desiredPlayer.getInteractionByPlayer(player).addGift(desiredItem, amount, true, true, desiredPlayer);

        player.setReaction(player.getGiftingTexture());
        desiredPlayer.setReaction(desiredPlayer.getGiftingTexture());

        return new Result(true, "Your gift has been sent.");
    }

    public Result giftList(Player player) {
        return new Result(true, player.getGiftList());
    }

    public Result giftRate(int giftNumber, int rate, Player player) {
        if (!isRateValid(rate)) {
            return new Result(false, "The score must be between 0 and 5.");
        } else if (player.getGiftByNumber(giftNumber) == null) {
            return new Result(false, "This gift does not exist.");
        }
        Gift gift = player.getGiftByNumber(giftNumber);

        gift.setRate(rate);
        Player desiredPlayer = gift.getPlayer();
        int xp = ((rate - 3) * 30) + 15;

        player.getInteractionByPlayer(desiredPlayer).increaseXp(xp);
        desiredPlayer.getInteractionByPlayer(player).increaseXp(xp);

        return new Result(true, "The desired gift has been rated.");
    }

    public Result giftHistory(String username, Player player) {
        Player desiredPlayer = player.getOtherPlayerByName(username);

        if (desiredPlayer == null) {
            return new Result(false, "This user does not exist.");
        }

        return new Result(true, player.getInteractionByPlayer(desiredPlayer).giftHistory());
    }

    public Result hug(String username, Player player) {
        Player desiredPlayer = player.getOtherPlayerByName(username);

        if (desiredPlayer == null) {
            return new Result(false, "This user does not exist.");
        }
//        else if (player.getInteractionByPlayer(desiredPlayer).getLevel() < 2) {
//            return new Result(false, "the level of friendship is not enough.");
//        }

        player.getInteractionByPlayer(desiredPlayer).increaseXp(60);
        desiredPlayer.getInteractionByPlayer(player).increaseXp(60);

        player.setReaction(player.getHuggingTexture());
        desiredPlayer.setReaction(desiredPlayer.getHuggingTexture());

        return new Result(true, "The desired player has been hugged.");
    }

    public Result flower(String username, Player player) {
        Player desiredPlayer = player.getOtherPlayerByName(username);

        if (desiredPlayer == null) {
            return new Result(false, "This user does not exist.");
        }
//        else if (player.getInteractionByPlayer(desiredPlayer).getLevel() < 2) {
//            return new Result(false, "the level of friendship is not enough.");
//        }


        player.getInteractionByPlayer(desiredPlayer).setLevel(3);
        desiredPlayer.getInteractionByPlayer(player).setLevel(3);

        player.setReaction(player.getFloweringTexture());
        desiredPlayer.setReaction(desiredPlayer.getFloweringTexture());

        return new Result(true, "The flower was given to the desired player.");
    }

    public Result askMarriage(String username, String ring, Player player) {
        Player desiredPlayer = player.getOtherPlayerByName(username);

        if (desiredPlayer == null) {
            return new Result(false, "This user does not exist.");
        }
//        else if (player.getInteractionByPlayer(desiredPlayer).getLevel() < 3) {
//            return new Result(false, "the level of friendship is not enough.");
//        }
        //        else if (!She Accepted) {}

        player.getInteractionByPlayer(desiredPlayer).setLevel(4);
        desiredPlayer.getInteractionByPlayer(player).setLevel(4);

        player.setReaction(player.getMarriageTexture());
        desiredPlayer.setReaction(desiredPlayer.getMarriageTexture());

        return new Result(true, "The marriage proposal was given.");
    }


    private boolean haveEnoughItem(String item, Player player, int amount) {
        if (!player.isExistInInventory(item)) {
            return false;
        }

        Item desiredItem = player.getItemFromInventoryByName(item);
        if (!(player.getAmountOfItem(desiredItem) > amount)) {
            return false;
        }

        return true;
    }

    private boolean isRateValid(int rate) {
        if (rate > 5 || rate <= 0) {
            return false;
        }
        return true;
    }

}
