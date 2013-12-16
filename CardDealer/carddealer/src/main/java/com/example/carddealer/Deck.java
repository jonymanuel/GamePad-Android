package com.example.carddealer;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Deck extends ActionBarActivity {

    ArrayList<Card> cards = new ArrayList<Card>();
    SecureRandom sr = new SecureRandom();

    //private final static String[] suitArray = {"Schoppen", "Harten", "Ruiten", "Klaver"};
    private final static String[] suitArray = {"Klaver", "Harten", "Ruiten", "Klaver"};
    private final static String[] rankArray = {"", "aas", "2", "3", "4", "5", "6", "7", "8", "9", "10", "boer", "vrouw", "koning"};

    public Deck() {
        build();
    }

    // Gets a Random card, removes it from deck and return it
    public Card getRandomCard() {
        int randomNumber = sr.nextInt( cards.size() );

        //Card c = cards.remove(randomNumber);
        //Log.e("Card picked:", getCardResource(c));
        //return c;
        return cards.remove(randomNumber);
    }

    public String getCardResource(Card card) {
        return suitArray[card.getSuit()].toLowerCase() + "_" + rankArray[card.getRank()];
    }

    /*
    public String getCardResource(int index) {
        return getCardResource(cards.get(index));
    }
    */

    public String getCardDisplayName(Card card) {
        return suitArray[card.getSuit()] + " " + rankArray[card.getRank()];
    }

    /*
    public String getCardDisplayName(int index) {
        return getCardDisplayName(cards.get(index));
    }*/

    // Shuffles/Builds the deck and fill it with all cards.
    public void build() {
        cards.clear();

        // Suits
        // 0 - Spades represented the nobility
        // 1 - Hearts represented the clergy
        // 2 - Diamonds represented the merchant class
        // 3 - Clubs represented the working class

        // Ranks
        // 1 - Ace
        // 2 - 2
        // ...
        // 10 - 10
        // 11 - Jack
        // 12 - Queen
        // 13 - King
        for(int s = 0; s <= 3; s++) {
            for(int r = 1; r <= 13; r++) {
                Card c = new Card(s, r);
                cards.add(c);
            }
        }

        shuffle();
    }

    public void shuffle() {
        sr.setSeed( sr.generateSeed(32 * sr.nextInt(10)) );

        int length = cards.size();
        ArrayList<Card> shuffledDeck = new ArrayList<Card>();
        for(int i = 0; i < length; i++) {
            int randomVal = sr.nextInt(cards.size());
            shuffledDeck.add(cards.get(randomVal));
            cards.remove(randomVal);
        }
        cards = shuffledDeck;
    }
}