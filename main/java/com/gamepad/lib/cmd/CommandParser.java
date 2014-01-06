package com.gamepad.lib.cmd;

import com.gamepad.lib.cmd.commands.PingCommand;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Author: root
 * Date: 07.10.13.
 */
public class CommandParser
{
    ArrayList<ICommand> commands;

    public CommandParser()
    {
        commands = new ArrayList<ICommand>();
    }

    public void RegisterCommand(ICommand cmd)
    {
        commands.add(cmd);
    }

    public JSONObject parseCommand(String input) throws Exception
    {
        JSONObject obj = new JSONObject(input);
        return obj;
    }

    public ICommand findCommandByCommandString(String commandString)
    {
        for(ICommand command : commands)
        {
            if(command.getCommandString().equals(commandString.trim().toLowerCase()))
            {
                return command;
            }
        }
        return null;
    }
}
