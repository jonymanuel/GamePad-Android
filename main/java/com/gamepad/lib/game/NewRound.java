package com.gamepad.lib.game;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gamepad.R;
import com.gamepad.lib.carddealer.ImageParser;

/**
 * Created by Jordi on 17/12/13.
 */
public class NewRound extends Activity{

    ImageView imgFlop1, imgFlop2, imgFlop3, imgTurn, imgRiver;
    private ImageParser imageParser;

    LinearLayout mLinearLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imgFlop1 = (ImageView) findViewById(R.id.flop1);
        imgFlop2 = (ImageView) findViewById(R.id.flop2);
        imgFlop3 = (ImageView) findViewById(R.id.flop3);
        imgTurn = (ImageView) findViewById(R.id.turn);
        imgRiver = (ImageView) findViewById(R.id.river);


        setContentView(R.layout.activity_round);

    }
}
