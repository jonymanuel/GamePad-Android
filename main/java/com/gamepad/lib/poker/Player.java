package com.gamepad.lib.poker;

public class Player {

    String name;
    Card card1, card2;
    Result result;

    public Player(String name, String color) {
        this.name = name;
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
}
