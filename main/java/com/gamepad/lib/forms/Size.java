package com.gamepad.lib.forms;

/**
 * Created by Fabian on 12.11.13.
 */
public class Size {
    private Integer width;
    private Integer height;

    public Size(Integer width, Integer height)
    {
        this.width =width;
        this.height =height;
    }

    public Integer getWidth()
    {
        return width;
    }

    public Integer getHeight()
    {
        return height;
    }
}
