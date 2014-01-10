package com.gamepad.lib.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

import com.gamepad.R;

/**
 * Created by Jordi on 17/12/13.
 */
public class NewRound extends Activity{

    View flop1;
    View flop2;
    View flop3;
    View turn;
    View river;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        double height = displaymetrics.heightPixels;
        double width = displaymetrics.widthPixels;

        flop1 = findViewById(R.id.flop1);
        flop2 = findViewById(R.id.flop2);
        flop3 = findViewById(R.id.flop3);
        turn = findViewById(R.id.turn);
        river = findViewById(R.id.river);

        double scaleWidth = (width / 1024) * 80;
        double scaleHeight = (height / 600) * 120;
        double marginTopCalc = (height / 600) * 200;

        double flop1MarginLeftCalc = (width/1024) * 270;
        double flop2MarginLeftCalc = (width/1024) * 370;
        double flop3MarginLeftCalc = (width/1024) * 470;
        double turnMarginLeftCalc = (width/1024) * 570;
        double riverMarginLeftCalc = (width/1024) * 670;

        int marginTop = (int)Math.round(marginTopCalc);
        int scaledWidth = (int)Math.round(scaleWidth);
        int scaledHeight = (int)Math.round(scaleHeight);
        int flop1MarginLeft = (int)Math.round(flop1MarginLeftCalc);
        int flop2MarginLeft = (int)Math.round(flop2MarginLeftCalc);
        int flop3MarginLeft = (int)Math.round(flop3MarginLeftCalc);
        int turnMarginLeft = (int)Math.round(turnMarginLeftCalc);
        int riverMarginLeft = (int)Math.round(riverMarginLeftCalc);


        RelativeLayout.LayoutParams parms1 = new RelativeLayout.LayoutParams(scaledWidth, scaledHeight);
        parms1.setMargins(flop1MarginLeft, marginTop, 0, 0);
        flop1.setLayoutParams(parms1);
        flop1.setBackgroundResource(R.drawable.aa);

        RelativeLayout.LayoutParams parms2 = new RelativeLayout.LayoutParams(scaledWidth, scaledHeight);
        parms2.setMargins(flop2MarginLeft, marginTop, 0, 0);
        flop2.setLayoutParams(parms2);
        flop2.setBackgroundResource(R.drawable.aa);

        RelativeLayout.LayoutParams parms3 = new RelativeLayout.LayoutParams(scaledWidth, scaledHeight);
        parms3.setMargins(flop3MarginLeft, marginTop, 0, 0);
        flop3.setLayoutParams(parms3);
        flop3.setBackgroundResource(R.drawable.aa);

        RelativeLayout.LayoutParams parms4 = new RelativeLayout.LayoutParams(scaledWidth, scaledHeight);
        parms4.setMargins(turnMarginLeft, marginTop, 0, 0);
        turn.setLayoutParams(parms4);
        turn.setBackgroundResource(R.drawable.aa);

        RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(scaledWidth, scaledHeight);
        parms.setMargins(riverMarginLeft, marginTop, 0, 0);
        river.setLayoutParams(parms);
        river.setBackgroundResource(R.drawable.aa);



    }

    public void dealFlop()
    {

    }
}
