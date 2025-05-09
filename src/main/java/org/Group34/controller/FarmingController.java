package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Player;
import org.Group34.model.map.Space;

public class FarmingController {
    private Space currentSpace; // TODO It will fix in GameController
    private Player currentPLayer; // TODO It will fix in GameController


    public Result plant(String seedName, String direction) {
        int playerLocationX = currentPLayer.getLocation()[0];
        int playerLocationY = currentPLayer.getLocation()[1];

        return new Result(true, "");
    }
    public Result showPlant(int x, int y) {
        return new Result(true, "");
    }
    public Result fertilize(String fertilizer, String direction) {
        return new Result(true, "");
    }
    public Result showAmountOfWater() {
        return new Result(true, "");
    }


    private int getLocationOfDirectionX(String direction) {
        int playerLocationX = currentPLayer.getLocation()[0];
        if (direction.equals("Up")) {

        } else if (direction.equals("Down")) {

        } else if (direction.equals("Right")) {

        } else if (direction.equals("Left")) {

        } else if (direction.equals("UpRight")) {

        } else if (direction.equals("UpLeft")) {

        } else if (direction.equals("DownRight")) {

        } else if (direction.equals("DownLeft")) {

        }
        return 0;
    }
    private int getLocationOfDirectionY(String direction) {
        int playerLocationY = currentPLayer.getLocation()[1];
        if (direction.equals("")) {

        } else if (direction.equals("")) {

        } else if (direction.equals("")) {

        } else if (direction.equals("")) {

        } else if (direction.equals("")) {

        } else if (direction.equals("")) {

        } else if (direction.equals("")) {

        } else if (direction.equals("")) {

        }
        return 0;
    }
}