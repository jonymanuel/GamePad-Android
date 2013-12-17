package com.gamepad.lib.carddealer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends Activity {

    ImageView imgCard1, imgCard2, imgCard3, imgCard4, imgCard5, imgCard6, imgCard7;
    ImageParser imageParser;
    CardCheck cardCheck;
    Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardCheck = new CardCheck();
        imageParser = new ImageParser(this);
        imgCard1 = (ImageView) findViewById(R.id.card1);
        imgCard2 = (ImageView) findViewById(R.id.card2);
        imgCard3 = (ImageView) findViewById(R.id.card3);
        imgCard4 = (ImageView) findViewById(R.id.card4);
        imgCard5 = (ImageView) findViewById(R.id.card5);
        imgCard6 = (ImageView) findViewById(R.id.card6);
        imgCard7 = (ImageView) findViewById(R.id.card7);

        renew();
    }

    public void renew() {


        Deck deck = new Deck();
        ArrayList<Card> cards = new ArrayList<Card>();
        /*for(int i = 0; i < 7; i++){
            cards.add(deck.getRandomCard());
        }*/
        cards.add(new Card(3, 5));
        cards.add(new Card(1, 4));
        cards.add(new Card(0, 13));
        cards.add(new Card(0, 5));
        cards.add(new Card(1, 3));
        cards.add(new Card(1, 1));
        cards.add(new Card(2, 7));

        imageParser.setSVGImage(imgCard1, getResources().getIdentifier(deck.getCardResource(cards.get(0)), "raw", getPackageName()));
        imageParser.setSVGImage(imgCard2, getResources().getIdentifier(deck.getCardResource(cards.get(1)), "raw", getPackageName()));
        imageParser.setSVGImage(imgCard3, getResources().getIdentifier(deck.getCardResource(cards.get(2)), "raw", getPackageName()));
        imageParser.setSVGImage(imgCard4, getResources().getIdentifier(deck.getCardResource(cards.get(3)), "raw", getPackageName()));
        imageParser.setSVGImage(imgCard5, getResources().getIdentifier(deck.getCardResource(cards.get(4)), "raw", getPackageName()));
        imageParser.setSVGImage(imgCard6, getResources().getIdentifier(deck.getCardResource(cards.get(5)), "raw", getPackageName()));
        imageParser.setSVGImage(imgCard7, getResources().getIdentifier(deck.getCardResource(cards.get(6)), "raw", getPackageName()));

        Date start = new Date();
        result = cardCheck.check(cards);
        Toast.makeText(getBaseContext(), result.getName(), Toast.LENGTH_LONG).show();
        Log.e("Calculation time ", Long.toString((new Date().getTime() - start.getTime())/1000));
    }

    @Override
    public void onBackPressed() {
        this.renew();
    }

    private void setBlank(ImageView iv) {
        iv.setImageResource(android.R.color.holo_blue_bright);
    }

    private boolean compareCards(Card card1, Card card2)
    {
        if(card1.getRank() == card2.getRank() &&
                card1.getSuit() == card2.getSuit()) {
            return true;
        }
        return false;
    }

}
