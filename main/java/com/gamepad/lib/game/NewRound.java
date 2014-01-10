package com.gamepad.lib.game;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.gamepad.R;

/**
 * Created by Jordi on 17/12/13.
 */
public class NewRound extends Activity{
    RelativeLayout mRelativeLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //int getWidthScreen = mRelativeLayout.getWidth();
        //int getHeightScreen = mRelativeLayout.getHeight();


        /*ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.aa);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rl.addView(iv, lp);*/

        setContentView(R.layout.activity_round);

    }
}
