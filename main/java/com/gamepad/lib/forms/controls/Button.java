package com.gamepad.lib.forms.controls;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.gamepad.lib.forms.Control;
import com.gamepad.lib.forms.Point;
import com.gamepad.lib.forms.Size;

/**
 * Created by Fabian on 12.11.13.
 */
public abstract class Button extends Control
{
    public Button(Point location, Size size)
    {
        super(location, size);
    }

    @Override
    public void draw(Canvas canvas)
    {
        float x = getRectangle().getX();
        float y = getRectangle().getY();
        float width = getRectangle().getWidth();
        float height = getRectangle().getHeight();
        Paint p = new Paint();
        p.setARGB(0, 0, 0, 0);
        canvas.drawRect(x, y, width, height, p);
    }

    @Override
    public void update()
    {

    }
}
