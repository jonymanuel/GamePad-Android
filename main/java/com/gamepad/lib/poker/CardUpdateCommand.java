package com.gamepad.lib.poker;

import com.gamepad.lib.cmd.ICommand;

import org.json.JSONObject;

/**
 * Created by Fabian on 26.01.14.
 */
public class CardUpdateCommand implements ICommand {
    @Override
    public String getCommandString() {
        return "cardUpdate";
    }

    @Override
    public Boolean runCommand(JSONObject input) throws Exception {
        String card1 = input.getString("card1");
        String card2 = input.getString("card2");
        return true;
    }
}
