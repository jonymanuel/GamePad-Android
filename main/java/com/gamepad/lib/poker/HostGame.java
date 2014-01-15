package com.gamepad.lib.poker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gamepad.R;

import java.util.ArrayList;

public class HostGame extends Activity {

    public ImageView table_card1, table_card2, table_card3, table_card4, table_card5;
    static Button control;
    static ImageParser imageParser;
    public CardCheck cardCheck;
    public Deck deck;
    public Round round;
    public Table table;
    public ArrayList<Player> players = new ArrayList<Player>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poker_host);

        deck = new Deck();
        cardCheck = new CardCheck();
        imageParser = new ImageParser(this);
        table_card1 = (ImageView) findViewById(R.id.table_card1);
        table_card2 = (ImageView) findViewById(R.id.table_card2);
        table_card3 = (ImageView) findViewById(R.id.table_card3);
        table_card4 = (ImageView) findViewById(R.id.table_card4);
        table_card5 = (ImageView) findViewById(R.id.table_card5);

        round = new Round(this);
        table = new Table();

        control = (Button) findViewById(R.id.control);
        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                round.nextStep();
            }
        });

        players.add(new Player("Fabi", "99CCFF"));
        players.add(new Player("Boris", "FFABFF56"));
        players.add(new Player("Kroky", "FFFF171D"));
        players.add(new Player("Jan", "FF8E20FF"));

        setPlayerNames();
        showPlayers();

        nextGame();
    }

    public void renew() {
        //if(deck.getSize() < 5 + (players.size() * 2)) {
            deck = new Deck();
            Log.e("Deck", "NEW DECK");
        // }
    }

    public void playCards(int action) {
        switch(action) {
            case 0: // Deal poker_client cards
                for(Player p : players) {
                    p.setCard1(deck.getRandomCard());
                    p.setCard2(deck.getRandomCard());
                }

                int count = 1;
                for(Player p : players) {

                    ImageView card1 = (ImageView) findViewById( getResources().getIdentifier("p" + Integer.toString(count) + "_card1", "id", getPackageName()) );
                    ImageView card2 = (ImageView) findViewById( getResources().getIdentifier("p" + Integer.toString(count) + "_card2", "id", getPackageName()) );

                    imageParser.setSVGImage(card1, getResources().getIdentifier(deck.getCardResource(p.getCard1()), "raw", getPackageName()));
                    imageParser.setSVGImage(card2, getResources().getIdentifier(deck.getCardResource(p.getCard2()), "raw", getPackageName()));

                    count++;
                }

                break;
            case 1: // Flop

                for(int i = 0; i < 3; i++) {
                    table.addCard(deck.getRandomCard());
                }

                imageParser.setSVGImage(table_card1, getResources().getIdentifier(deck.getCardResource(table.getCards(0)), "raw", getPackageName()));
                imageParser.setSVGImage(table_card2, getResources().getIdentifier(deck.getCardResource(table.getCards(1)), "raw", getPackageName()));
                imageParser.setSVGImage(table_card3, getResources().getIdentifier(deck.getCardResource(table.getCards(2)), "raw", getPackageName()));
                break;
            case 2: // Turn
                table.addCard(deck.getRandomCard());
                imageParser.setSVGImage(table_card4, getResources().getIdentifier(deck.getCardResource(table.getCards(3)), "raw", getPackageName()));
                break;
            case 3: // River
                table.addCard(deck.getRandomCard());
                imageParser.setSVGImage(table_card5, getResources().getIdentifier(deck.getCardResource(table.getCards(4)), "raw", getPackageName()));
                break;
        }
    }

    public void rateHands() {
        for(Player p : players) {
            ArrayList<Card> cards = new ArrayList<Card>();
            cards.addAll(table.getCards());

            cards.add(p.getCard1());
            cards.add(p.getCard2());
            p.setResult(cardCheck.check(cards));
            cards.clear();
        }
    }

    public void pickWinner() {
        int count = 1;
        Player winningPlayer = null;
        for(Player player : players) {
            if(winningPlayer == null) {
                winningPlayer = player;
            }
            else if(player.getResult().getCombinationID() > winningPlayer.getResult().getCombinationID()) {
                winningPlayer = player;
            }

            // Show results
            TextView tmp = (TextView) findViewById( getResources().getIdentifier("p" + Integer.toString(count) + "_result", "id", getPackageName()));
            tmp.setText(player.getResult().getName().toUpperCase());

            count++;
        }
        Toast.makeText(getBaseContext(), winningPlayer.getName() + " heeft gewonnen met een " + winningPlayer.getResult().getName(), Toast.LENGTH_SHORT).show();
    }

    public void setBtnText(String btnText) {
        control.setText(btnText);
    }

    public void nextGame() {
        renew();
        resetTableCards();
        resetPlayerCards();
        resetPlayerResults();

        table.clearTable();
        round.reset();
        //round.nextStep();
        setBtnText("DEAL CARDS");
    }

    public void setPlayerNames() {
        int count = 1;
        for(Player p : players) {
            TextView tmp = (TextView) findViewById( getResources().getIdentifier("p" + Integer.toString(count) + "_name", "id", getPackageName()) );
            tmp.setText( p.getName() );
            count++;
        }
    }

    public void showPlayers() {
        int count = 1;
        for(Player p : players) {
            LinearLayout tmp = (LinearLayout) findViewById( getResources().getIdentifier("p" + Integer.toString(count) + "_block", "id", getPackageName()) );
            tmp.setVisibility(View.VISIBLE);
            count++;
        }
    }

    public void resetPlayerResults() {

        int count = 1;
        for(Player p : players) {

            TextView tmp = (TextView) findViewById( getResources().getIdentifier("p" + Integer.toString(count) + "_result", "id", getPackageName()));
            tmp.setText("");

            count++;
        }
    }

    public void resetPlayerCards() {

        int count = 1;
        for(Player p : players) {

            ImageView card1 = (ImageView) findViewById( getResources().getIdentifier("p" + Integer.toString(count) + "_card1", "id", getPackageName()) );
            ImageView card2 = (ImageView) findViewById( getResources().getIdentifier("p" + Integer.toString(count) + "_card2", "id", getPackageName()) );

            imageParser.setSVGImage(card1, getResources().getIdentifier("back", "raw", getPackageName()));
            imageParser.setSVGImage(card2, getResources().getIdentifier("back", "raw", getPackageName()));

            count++;
        }
    }

    public void resetTableCards() {
        imageParser.setSVGImage(table_card1, getResources().getIdentifier("back", "raw", getPackageName()));
        imageParser.setSVGImage(table_card2, getResources().getIdentifier("back", "raw", getPackageName()));
        imageParser.setSVGImage(table_card3, getResources().getIdentifier("back", "raw", getPackageName()));
        imageParser.setSVGImage(table_card4, getResources().getIdentifier("back", "raw", getPackageName()));
        imageParser.setSVGImage(table_card5, getResources().getIdentifier("back", "raw", getPackageName()));
    }
}
