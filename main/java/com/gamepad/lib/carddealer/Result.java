package com.gamepad.lib.carddealer;

import java.util.ArrayList;

/**
 * Created by Your Face on 16-12-13.
 */
public class Result {

    private final ArrayList<Card> cards;
    private final String winningCombination;

    public Result(ArrayList<Card> cards, String winningCombination) {
        this.cards = cards;
        this.winningCombination = winningCombination;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public String getName() {
        return winningCombination;
    }
}
