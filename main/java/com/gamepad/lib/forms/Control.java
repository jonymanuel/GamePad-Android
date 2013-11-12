package com.gamepad.lib.forms;

import android.graphics.Canvas;

/**
 * Created by Fabian on 12.11.13.
 */
public abstract class Control
{
    private Rectangle rectangle;

    public Control()
    {
        rectangle = new Rectangle();
    }

    public abstract void draw(Canvas canvas);

    public abstract void update();

    public Rectangle getRectangle()
    {
        return rectangle;
    }
}
