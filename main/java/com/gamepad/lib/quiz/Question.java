package com.gamepad.lib.quiz;

/**
 * Created by Fabian on 27.01.14.
 */
public class Question {
    int id;

    String text;
    int answer;

    public Question(int id, String text, int answer)
    {
        this.id = id;
        this.text = text;
        this.answer = answer;
    }

    public String getText() {
        return text;
    }

    public int getAnswer() {
        return answer;
    }


    public int getId()
    {
        return id;
    }


}
