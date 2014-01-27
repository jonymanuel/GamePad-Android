package com.gamepad.lib.quiz;

/**
 * Created by Fabian on 27.01.14.
 */
public class Answer {
    int id;
    String text;

    public Answer(int id, String text)
    {
        this.id = id;
        this.text = text;
    }

    public int getId()
    {
        return id;
    }

    public String getText()
    {
        return text;
    }
}
