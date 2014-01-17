package com.gamepad.lib.poker;

import java.util.ArrayList;

public class Table {

    private ArrayList<Card> cards = new ArrayList<Card>();

    public Table() {
        // ; do nothing
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCards(int index) {
        return cards.get(index);
    }

    public void addCard(Card card)
    {
        this.cards.add(card);
    }

    private void clearCards() {
        cards.clear();
        //resetTableCard();
    }

    public void clearTable() {
        clearCards();
    }
}
