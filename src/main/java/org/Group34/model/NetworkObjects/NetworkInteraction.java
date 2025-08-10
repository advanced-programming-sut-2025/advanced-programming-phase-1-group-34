package org.Group34.model.NetworkObjects;

import java.io.Serializable;

public class NetworkInteraction implements Serializable {
    private String player1;
    private String player2;
    private String work;
    private String text;
    private int amount;

    public NetworkInteraction(String player1, String player2, String work, String text, int amount ) {
        this.player1 = player1;
        this.player2 = player2;
        this.work = work;
        this.text = text;
        this.amount = amount;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
