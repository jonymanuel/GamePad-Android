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

    ImageView imgCard1, imgCard2, imgCard3, imgCard4, imgCard5, imgPlayer1_1, imgPlayer1_2, imgPlayer2_1, imgPlayer2_2, imgPlayer3_1,
            imgPlayer3_2, imgPlayer4_1, imgPlayer4_2, imgPlayer5_1, imgPlayer5_2, imgPlayer6_1, imgPlayer6_2, imgPlayer7_1, imgPlayer7_2,
            imgPlayer8_1, imgPlayer8_2;
    private ImageParser imageParser;

    LinearLayout mLinearLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imgCard1 = (ImageView) findViewById(R.id.flop1);
        imgCard2 = (ImageView) findViewById(R.id.flop2);
        imgCard3 = (ImageView) findViewById(R.id.flop3);
        imgCard4 = (ImageView) findViewById(R.id.turn);
        imgCard5 = (ImageView) findViewById(R.id.river);
        imgPlayer1_1 = (ImageView) findViewById(R.id.card1_1);
        imgPlayer1_2 = (ImageView) findViewById(R.id.card1_2);
        imgPlayer2_1 = (ImageView) findViewById(R.id.card2_1);
        imgPlayer2_2 = (ImageView) findViewById(R.id.card2_2);
        imgPlayer3_1 = (ImageView) findViewById(R.id.card3_1);
        imgPlayer3_2 = (ImageView) findViewById(R.id.card3_2);
        imgPlayer4_1 = (ImageView) findViewById(R.id.card4_1);
        imgPlayer4_2 = (ImageView) findViewById(R.id.card4_2);
        imgPlayer5_1 = (ImageView) findViewById(R.id.card5_1);
        imgPlayer5_2 = (ImageView) findViewById(R.id.card5_2);
        imgPlayer6_1 = (ImageView) findViewById(R.id.card6_1);
        imgPlayer6_2 = (ImageView) findViewById(R.id.card6_2);
        imgPlayer7_1 = (ImageView) findViewById(R.id.card7_1);
        imgPlayer7_2 = (ImageView) findViewById(R.id.card7_2);
        imgPlayer8_1 = (ImageView) findViewById(R.id.card8_1);
        imgPlayer8_2 = (ImageView) findViewById(R.id.card8_2);



    }
}
