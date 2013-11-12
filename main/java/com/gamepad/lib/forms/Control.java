package com.gamepad.lib.forms;

import android.graphics.Canvas;

/**
 * Created by Fabian on 12.11.13.
 */
public abstract class Control
{
    private Rectangle rectangle;

    public Control(Point location, Size size)
    {
        rectangle = new Rectangle(location.getX(), location.getY(), size.getWidth(), size.getHeight());
    }

    public abstract void onClick(Point point);

    public abstract void draw(Canvas canvas);

    public abstract void update();

    public Rectangle getRectangle()
    {
        return rectangle;
    }
}
