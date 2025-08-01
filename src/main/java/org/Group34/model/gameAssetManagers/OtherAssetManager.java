package org.Group34.model.gameAssetManagers;

import com.badlogic.gdx.graphics.Texture;

public class OtherAssetManager {
    private final static Texture chest = new Texture("other/Empty-Chest.jpg");
    private final static Texture board = new Texture("other/Inventaire.jpg");
    private final static Texture smallBoard = new Texture("other/small_board.png");
    private final static Texture faceIcon = new Texture("other/Haley_Icon.png");
    private final static Texture hearthIcon = new Texture("other/Emojis046.png");
    private final static Texture forestIcon = new Texture("other/Forester.png");
    private final static Texture bigBoard = new Texture("other/bigBoard.png");
    private final static Texture SmileIcon = new Texture("other/Achievement_A_New_Friend.jpg");

    // ----- getters -----

    public static Texture getChest() {
        return chest;
    }

    public static Texture getBoard() {
        return board;
    }

    public static Texture getSmallBoard() {
        return smallBoard;
    }

    public static Texture getFaceIcon() {
        return faceIcon;
    }

    public static Texture getHearthIcon() {
        return hearthIcon;
    }

    public static Texture getForestIcon() {
        return forestIcon;
    }

    public static Texture getBigBoard() {
        return bigBoard;
    }

    public static Texture getSmileIcon() {
        return SmileIcon;
    }

    // -------------------
}
