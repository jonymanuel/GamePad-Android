package com.gamepad.lib.forms;

import android.graphics.Canvas;

/**
 * Created by Fabian on 12.11.13.
 */
public abstract class Control
{
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;

    public abstract void draw(Canvas canvas);

    public abstract void update();

    public Integer getX()
    {
        return x;
    }

    public Integer getY()
    {
        return y;
    }

    public Integer getHeight()
    {
        return height;
    }

    public Integer getWidth()
    {
        return width;
    }
}
