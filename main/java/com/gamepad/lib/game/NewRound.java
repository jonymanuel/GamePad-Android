package com.gamepad.lib.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gamepad.R;

/**
 * Created by Jordi on 17/12/13.
 */
public class NewRound extends Activity{
    RelativeLayout mRelativeLayout;
    ImageView flop1, flop2, flop3, turn, river;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        flop1 = findViewById(flop1);
        setContentView(R.layout.activity_round);
    }

    public void dealFlop()
    {

    }
}
