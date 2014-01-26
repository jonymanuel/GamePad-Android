package com.gamepad.lib.poker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gamepad.R;
import com.gamepad.lib.GPC;

import java.util.ArrayList;

/**
 * Created by Fabian on 26.01.14.
 */
public class PokerClientActivity extends Activity {
    ImageParser imageParser;
    ImageView player_card1, player_card2;
    Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poker_client);
        deck = new Deck();
        player_card1 = (ImageView)findViewById(R.id.player_card1);
        player_card2 = (ImageView)findViewById(R.id.player_card2);
        imageParser = new ImageParser(this);
        GPC.getCmdParser().RegisterCommand(new CardUpdateCommand());
        MyPokerGame.setPokerClientActivity(this);
    }

    public void drawCards(Card card1, Card card2) {
        final Card card1F = card1;
        final Card card2F = card2;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageParser.setSVGImage(player_card1, getResources().getIdentifier(deck.getCardResource(card1F), "raw", getPackageName()));
                imageParser.setSVGImage(player_card2, getResources().getIdentifier(deck.getCardResource(card2F), "raw", getPackageName()));
            }
        });

    }

    public void updateCards()
    {

    }

    @Override
    public void onBackPressed() {
    }
}
