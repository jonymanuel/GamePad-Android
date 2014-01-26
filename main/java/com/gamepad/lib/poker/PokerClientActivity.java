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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poker_client);
        GPC.getCmdParser().RegisterCommand(new CardUpdateCommand());
    }

    public void drawCards(Card card1, Card card2) {

        //imageParser.setSVGImage(player_card1, getResources().getIdentifier(deck.getCardResource(card1), "raw", getPackageName()));
        //imageParser.setSVGImage(player_card2, getResources().getIdentifier(deck.getCardResource(card2), "raw", getPackageName()));
    }

    @Override
    public void onBackPressed() {
    }
}
