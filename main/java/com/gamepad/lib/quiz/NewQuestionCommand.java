package com.gamepad.lib.quiz;

import com.gamepad.lib.cmd.ICommand;

import org.json.JSONObject;

/**
 * Created by Fabian on 27.01.14.
 */
public class NewQuestionCommand implements ICommand {
    @Override
    public String getCommandString() {
        return "newQuestion";
    }

    @Override
    public Boolean runCommand(JSONObject input) throws Exception {
        MyQuizGame.getQuiz().updateFromData(input);
        MyQuizGame.getQuizClientActivity().updateViews();
        return true;
    }
}
