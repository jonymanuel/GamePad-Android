package com.gamepad;

import android.app.Activity;
import android.os.Bundle;


public class TestMainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Drawable draw = new Drawable(this);

        setContentView(draw);
    }
}

