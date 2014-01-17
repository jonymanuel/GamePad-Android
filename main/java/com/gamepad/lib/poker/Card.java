package com.gamepad.lib.poker;

public class Card {
    int suit, rank;
    boolean tableCard;

    public Card(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
        tableCard = false;
    }

    public Card(int suit, int rank, boolean tableCard) {
        this.suit = suit;
        this.rank = rank;
        this.tableCard = tableCard;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public boolean isTableCard() {
        return tableCard;
    }

    public Card setTableCard(boolean tableCard) {
        this.tableCard = tableCard;
        return this;
    }
}