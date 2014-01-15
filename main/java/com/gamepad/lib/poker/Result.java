package com.gamepad.lib.poker;

import java.util.ArrayList;

public class Result {

    private final ArrayList<Card> cards;
    private final String winningCombination;
    private final int combinationID;

    public Result(ArrayList<Card> cards, String winningCombination, int combinationID) {
        this.cards = cards;
        this.combinationID = combinationID;
        this.winningCombination = winningCombination;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public String getName() {
        return winningCombination;
    }

    public int getCombinationID() {
        return combinationID;
    }
}
