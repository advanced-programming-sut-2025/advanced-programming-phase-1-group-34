package org.Group34.controller;

import org.Group34.model.entities.Player;
import org.Group34.model.enums.LevelType;

public class LevelUpController {

    public void farmingLevelUp(Player player, LevelType levelType){
        boolean leveledUp = levelUp(player, levelType, 5);
        if (leveledUp){

        }
    }

    public void miningLevelUp(Player player, LevelType levelType){
        boolean leveledUp = levelUp(player, levelType, 10);
        if (leveledUp){

        }
    }

    public void foragingLevelUp(Player player, LevelType levelType){
        boolean leveledUp = levelUp(player, levelType, 10);
        if (leveledUp){

        }
    }

    public void fishingLevelUp(Player player, LevelType levelType){
        boolean leveledUp = levelUp(player, levelType, 5);
        if (leveledUp){

        }
    }

    private boolean levelUp(Player player, LevelType levelType, int amount){
        int currentLevelUnit = player.getLevelUnit().get(levelType) + amount;
        int nextLevel = player.getLevel(levelType);
        int nextLevelUnit = 100 * (nextLevel + 1) + 50;

        if (currentLevelUnit >= nextLevel){
            player.levelUp(levelType, 1);
            player.setLevelUnit(levelType, currentLevelUnit - nextLevelUnit);
            return true;
        }
        else {
            player.setLevelUnit(levelType, currentLevelUnit);
            return false;
        }
    }
}
