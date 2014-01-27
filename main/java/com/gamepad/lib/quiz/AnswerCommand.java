package com.gamepad.lib.quiz;

import com.gamepad.lib.cmd.ICommand;

import org.json.JSONObject;

/**
 * Created by Fabian on 27.01.14.
 */
public class AnswerCommand implements ICommand {
    @Override
    public String getCommandString() {
        return "answer";
    }

    @Override
    public Boolean runCommand(JSONObject input) throws Exception {
        String playerName = input.getString("playerName");
        int question = Integer.parseInt(input.getString("question"));
        int answer = Integer.parseInt(input.getString("answer"));
        MyQuizGame.getQuiz().playerAnswered(playerName, question, answer);
        MyQuizGame.getQuizHostActivity().updateRankings();
        return true;
    }
}
