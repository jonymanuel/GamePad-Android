package com.gamepad.lib.cmd.commands;

import com.gamepad.lib.cmd.ICommand;

import org.json.JSONObject;

/**
 * Created by Fabian on 04.01.14.
 */
public class GetLobbiesCommand implements ICommand
{

    @Override
    public String getCommandString() {
        return "getlobbies";
    }

    @Override
    public Boolean runCommand(JSONObject input) throws Exception {
        
        return true;
    }
}
