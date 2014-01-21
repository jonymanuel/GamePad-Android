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

        players.add(new Player("Fabi",  1, "99CCFF"));
        players.add(new Player("Boris", 2, "FFABFF56"));
        players.add(new Player("Kroky", 3, "FFFF171D"));
        players.add(new Player("Jan",   4, "FF8E20FF"));

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

    public void playPoker(int action) {
        switch(action) {
            case 0: // Deal poker_client cards
                for(Player p : players) {
                    p.setCard1(deck.getRandomCard());
                    p.setCard2(deck.getRandomCard());
                }

                for(Player p : players) {

                    ImageView card1 = (ImageView) findViewById( getResources().getIdentifier("p" + Integer.toString(p.getPlayerID()) + "_card1", "id", getPackageName()) );
                    ImageView card2 = (ImageView) findViewById( getResources().getIdentifier("p" + Integer.toString(p.getPlayerID()) + "_card2", "id", getPackageName()) );

                    imageParser.setSVGImage(card1, getResources().getIdentifier(deck.getCardResource(p.getCard1()), "raw", getPackageName()));
                    imageParser.setSVGImage(card2, getResources().getIdentifier(deck.getCardResource(p.getCard2()), "raw", getPackageName()));
                }

                break;
            case 1: // Flop

                addTableCard(3);

                imageParser.setSVGImage(table_card1, getResources().getIdentifier(deck.getCardResource(table.getCard(0)), "raw", getPackageName()));
                imageParser.setSVGImage(table_card2, getResources().getIdentifier(deck.getCardResource(table.getCard(1)), "raw", getPackageName()));
                imageParser.setSVGImage(table_card3, getResources().getIdentifier(deck.getCardResource(table.getCard(2)), "raw", getPackageName()));
                break;
            case 2: // Turn
                addTableCard(1);
                imageParser.setSVGImage(table_card4, getResources().getIdentifier(deck.getCardResource(table.getCard(3)), "raw", getPackageName()));
                break;
            case 3: // River
                addTableCard(1);
                imageParser.setSVGImage(table_card5, getResources().getIdentifier(deck.getCardResource(table.getCard(4)), "raw", getPackageName()));
                break;
        }
    }

    /**
     * Picks the winner and IN THE FUTURE return(s) the winning player(s)
     * NEEDS ALOT OF REFACTORING
     */
    public void pickWinner()
    {
        rateHands();
        Player player = getHighestCombination();
        for(Player playerIt : players) {
            if(playerIt.getResult().getCombinationID() == player.getResult().getCombinationID()) {
                if(!playerIt.getName().equals(player.getName())) {
                    if(cardCheck.getHighestHandCard(player.getResult().getCards()).getRank() == 1) {
                        //Ace High
                        //Do nothing
                    }
                    else if(cardCheck.getHighestHandCard(playerIt.getResult().getCards()).getRank() == 1 && cardCheck.getHighestHandCard(player.getResult().getCards()).getRank() != 1) {
                        //Ace high
                        player = playerIt;
                    }
                    else if(cardCheck.getHighestHandCard(playerIt.getResult().getCards()).getRank() > cardCheck.getHighestHandCard(player.getResult().getCards()).getRank()) {
                        //Handcard high
                        player = playerIt;
                    }
                    /*else if() {
                        //Second Handcard High
                    }*/
                }
            }
        }
        printWinner(player);
    }

    private void printWinner(Player player)
    {
        Toast.makeText(getBaseContext(), player.getName() + " won with a " + player.getResult().getName(), Toast.LENGTH_SHORT).show();
        for(Card card : player.getResult().getCards()) {
            Log.e("WinningCombinations", "Suit:" + card.getSuit() + " " + "Rank:" + card.getRank());
        }
    }

    /**
     * Gets the player with the highest combination
     * @return
     */
    private Player getHighestCombination()
    {
        Player winningPlayer = null;
        for(Player player : players) {
            if(winningPlayer == null) {
                winningPlayer = player;
            }
            else if(player.getResult().getCombinationID() > winningPlayer.getResult().getCombinationID()) {
                winningPlayer = player;
            }
        }
        return winningPlayer;
    }

    private void rateHands()
    {
        for(Player p : players) {
            ArrayList<Card> cards = new ArrayList<Card>();
            cards.addAll(table.getCards());

            cards.add(p.getCard1());
            cards.add(p.getCard2());
            p.setResult(cardCheck.check(cards));
            cards.clear();

            TextView tmp = (TextView) findViewById( getResources().getIdentifier("p" + Integer.toString(p.getPlayerID()) + "_result", "id", getPackageName()));
            tmp.setText(p.getResult().getName());
        }
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
        for(Player p : players) {
            TextView tmp = (TextView) findViewById( getResources().getIdentifier("p" + Integer.toString(p.getPlayerID()) + "_name", "id", getPackageName()) );
            tmp.setText( p.getName() );
        }
    }

    public void showPlayers() {
        for(Player p : players) {
            LinearLayout tmp = (LinearLayout) findViewById( getResources().getIdentifier("p" + Integer.toString(p.getPlayerID()) + "_block", "id", getPackageName()) );
            tmp.setVisibility(View.VISIBLE);
        }
    }

    public void resetPlayerResults() {
        for(Player p : players) {
            TextView tmp = (TextView) findViewById( getResources().getIdentifier("p" + Integer.toString(p.getPlayerID()) + "_result", "id", getPackageName()));
            tmp.setText("");
        }
    }

    public void resetPlayerCards() {

        for(Player p : players) {

            ImageView card1 = (ImageView) findViewById( getResources().getIdentifier("p" + Integer.toString(p.getPlayerID()) + "_card1", "id", getPackageName()) );
            ImageView card2 = (ImageView) findViewById( getResources().getIdentifier("p" + Integer.toString(p.getPlayerID()) + "_card2", "id", getPackageName()) );

            imageParser.setSVGImage(card1, getResources().getIdentifier("back", "raw", getPackageName()));
            imageParser.setSVGImage(card2, getResources().getIdentifier("back", "raw", getPackageName()));
        }
    }

    public void resetTableCards() {
        imageParser.setSVGImage(table_card1, getResources().getIdentifier("back", "raw", getPackageName()));
        imageParser.setSVGImage(table_card2, getResources().getIdentifier("back", "raw", getPackageName()));
        imageParser.setSVGImage(table_card3, getResources().getIdentifier("back", "raw", getPackageName()));
        imageParser.setSVGImage(table_card4, getResources().getIdentifier("back", "raw", getPackageName()));
        imageParser.setSVGImage(table_card5, getResources().getIdentifier("back", "raw", getPackageName()));
    }

    private void addTableCard(int i)
    {
        for(int j = 0; j < i; j++) {
            table.addCard(deck.getRandomCard());
        }
    }
}
