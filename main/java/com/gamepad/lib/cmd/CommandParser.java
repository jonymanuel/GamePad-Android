package com.gamepad.lib.cmd;

import android.util.Log;

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

    /* Clears all hooked commands in the command list */
    public void clearCommands()
    {
        commands.clear();
    }

    public void RegisterCommand(ICommand cmd)
    {
        commands.add(cmd);
        Log.d("CommandParser", "Registered new command: " + cmd.getCommandString());
    }

    public JSONObject parseCommand(String input) throws Exception
    {
        return new JSONObject(input);
    }

    public ICommand findCommandByCommandString(String commandString)
    {
        for(ICommand command : commands)
        {
            if(command.getCommandString().toLowerCase().equals(commandString.toLowerCase()))
            {
                return command;
            }
        }
        return null;
    }
}
