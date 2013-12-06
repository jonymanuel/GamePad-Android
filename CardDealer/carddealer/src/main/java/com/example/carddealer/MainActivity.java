package com.example.carddealer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Deck deck = new Deck();


        int deckSize = deck.cards.size();
        Log.e("Deck size", Integer.toString(deckSize));

        for(int i = 0;i < deckSize; i++) {

            //Log.e("Card:", deck.getCardDisplayName( deck.getRandomCard() ));
            Card c = deck.getRandomCard();
            Log.e("Card:", deck.getCardDisplayName(c));
        }
    }
}
