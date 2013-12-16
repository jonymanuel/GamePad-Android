package com.example.carddealer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends Activity {

    ImageView card1, card2, card3, card4, card5, card6, card7;
    ImageParser imageParser = new ImageParser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_main);

        card1 = (ImageView) findViewById(R.id.card1);
        card2 = (ImageView) findViewById(R.id.card2);
        card3 = (ImageView) findViewById(R.id.card3);
        card4 = (ImageView) findViewById(R.id.card4);
        card5 = (ImageView) findViewById(R.id.card5);
        card6 = (ImageView) findViewById(R.id.card6);
        card7 = (ImageView) findViewById(R.id.card7);

        //Deck deck = new Deck();

        //int deckSize = deck.cards.size();
        //Log.e("Deck size", Integer.toString(deckSize));

        //for(int i = 0;i < deckSize; i++) {
            //Log.e("Card:", deck.getCardDisplayName( deck.getRandomCard() ));
            //Card c = deck.getRandomCard();
            //Log.e("Card:", deck.getCardDisplayName(c));
        //}

        //Log.e("Card resource", deck.getCardResource(deck.getRandomCard()));
        //Log.e("Card resource id", Integer.toString( getResources().getIdentifier(deck.getCardResource(deck.getRandomCard()), "string", getPackageName())));

        //imageParser.setSVGImage(card1, getResources().getIdentifier(deck.getCardResource(deck.getRandomCard()), "raw", getPackageName()));


        renew();
    }

    public void renew() {

        Deck deck = new Deck();

        imageParser.setSVGImage(card1, getResources().getIdentifier(deck.getCardResource(deck.getRandomCard()), "raw", getPackageName()));
        imageParser.setSVGImage(card2, getResources().getIdentifier(deck.getCardResource(deck.getRandomCard()), "raw", getPackageName()));
        imageParser.setSVGImage(card3, getResources().getIdentifier(deck.getCardResource(deck.getRandomCard()), "raw", getPackageName()));
        imageParser.setSVGImage(card4, getResources().getIdentifier(deck.getCardResource(deck.getRandomCard()), "raw", getPackageName()));
        imageParser.setSVGImage(card5, getResources().getIdentifier(deck.getCardResource(deck.getRandomCard()), "raw", getPackageName()));
        imageParser.setSVGImage(card6, getResources().getIdentifier(deck.getCardResource(deck.getRandomCard()), "raw", getPackageName()));
        imageParser.setSVGImage(card7, getResources().getIdentifier(deck.getCardResource(deck.getRandomCard()), "raw", getPackageName()));

        //setBlanc(card2);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        this.renew();
    }

    public void setBlanc(ImageView iv) {
        iv.setImageResource(android.R.color.transparent);
    }
}
