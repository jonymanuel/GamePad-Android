package com.gamepad.lib.forms;

/**
 * Created by Fabian on 12.11.13.
 */
public class Rectangle
{
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;

    public Rectangle(Integer x, Integer y, Integer width, Integer height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Boolean Contains(Point point)
    {
       //x range is correct
       if(point.getX() <= x && point.getX() <= x + width)
       {
            //y range is correct
           if(point.getY() <= y && point.getY() <= y + height)
           {
                return true;
           }
       }
       return false;
    }

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
