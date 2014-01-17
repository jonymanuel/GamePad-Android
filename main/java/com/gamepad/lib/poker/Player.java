package com.gamepad.lib.poker;

public class Player {

    String name, color;
    int playerID;
    Card card1, card2;
    Result result;

    public Player(String name, int playerID, String color) {
        this.name = name;
        this.playerID = playerID;
    }

    public Card getCard1() {
        return card1;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public Card getCard2() {
        return card2;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void clear()
    {
        card1 = null;
        card2 = null;
    }

    public String getName() {
        return name;
    }

    public int getPlayerID() {
        return playerID;
    }
}

