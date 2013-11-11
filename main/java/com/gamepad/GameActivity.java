package com.gamepad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gamepad.lib.GPC;
import com.gamepad.lib.game.Game;

/**
 * Author: root
 * Date: 14.10.13.
 */
public class GameActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
}
