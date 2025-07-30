package org.Group34.view.graphic.dialogs;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.Group34.model.Result;
import org.Group34.controller.GameController;

public class GreenhouseRepairDialog extends Dialog {
    private final GameController gameController;
    private final int requiredWood;
    private final int requiredMoney;

    public GreenhouseRepairDialog(String title, Skin skin, GameController gameController, int requiredWood, int requiredMoney) {
        super(title, skin);
        this.gameController = gameController;
        this.requiredWood = requiredWood;
        this.requiredMoney = requiredMoney;

        text("Greenhouse Repair Required:\n" +
                "Wood: " + requiredWood + "\n" +
                "Gold: " + requiredMoney + "\n\n" +
                "Do you want to repair it?");

        button("Repair", true);
        button("Cancel", false);
    }

    @Override
    protected void result(Object object) {
        if (object.equals(true)) {
            Result result = gameController.buildGreenhouse();
            Dialog resultDialog = new Dialog("Repair Result", getSkin());
            resultDialog.text(result.message());
            resultDialog.button("OK");
            resultDialog.show(getStage());

            if (result.success()) {
                resultDialog.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        // Close both dialogs
                        resultDialog.hide();
                        GreenhouseRepairDialog.this.hide();
                        // Force a game state refresh
                        if (getStage() != null && getStage().getRoot().findActor("gameScreen") != null) {
                            // Trigger refresh logic here if needed
                        }
                    }
                });
            }
        }
    }
}