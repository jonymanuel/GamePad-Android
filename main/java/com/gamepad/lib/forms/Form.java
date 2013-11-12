package com.gamepad.lib.forms;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by Fabian on 12.11.13.
 */
public class Form {

    private ArrayList<Control> controls;

    public Form()
    {
        controls = new ArrayList<Control>();
    }

    public void addControl(Control newControl)
    {
        if(newControl != null)
        {
            controls.add(newControl);
        }
    }

    public void update()
    {
        for(Integer i = 0;i<controls.size();i++)
        {
            controls.get(i).update();
        }
    }

    public void onClick(Point position)
    {
        for(Integer i = 0;i<controls.size();i++)
        {
            if(controls.get(i).getRectangle().contains(position))
            {
                controls.get(i).onClick(position);
            }
        }
    }

    public void draw(Canvas canvas)
    {
        for(Integer i = 0;i<controls.size();i++)
        {
            controls.get(i).draw(canvas);
        }
    }
}
