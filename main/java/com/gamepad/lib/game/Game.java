package com.gamepad.lib.game;

import android.graphics.Canvas;

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
