package com.gamepad.lib.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Author: root
 * Date: 14.10.13.
 */
public interface Game
{
    public void initialize();

    public void draw(Canvas c);

    public void update();

    public void unload();
}
