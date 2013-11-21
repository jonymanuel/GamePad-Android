package com.gamepad;

import android.app.Activity;
import android.os.Bundle;
import com.gamepad.lib.game.GameManager;

public class HostActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
    }

}
