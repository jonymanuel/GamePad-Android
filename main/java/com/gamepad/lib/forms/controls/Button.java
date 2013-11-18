package com.gamepad.lib.forms.controls;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.gamepad.lib.forms.Control;
import com.gamepad.lib.forms.Point;
import com.gamepad.lib.forms.Size;

/**
 * Created by Fabian on 12.11.13.
 */
public class Button extends Control
{
    private String text;

    public Button(Point location, Size size)
    {
        super(location, size);
    }

    @Override
    public void onClick(Point point) {

    }

    @Override
    public void draw(Canvas canvas)
    {
        float x = getRectangle().getX();
        float y = getRectangle().getY();
        float width = getRectangle().getWidth();
        float height = getRectangle().getHeight();
        Paint p = new Paint();
        p.setARGB(0, 255, 0, 0);
        p.setStrokeWidth(10);
        p.setAlpha(0);
        p.setTextSize(50);
        canvas.drawRect(x, y, width, height, p);
        canvas.drawText("peter test",10, 500, p);
    }

    public String getText()
    {
        return text;
    }

    @Override
    public void update()
    {

    }
}
