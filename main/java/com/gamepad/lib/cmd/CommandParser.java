package com.gamepad.lib.cmd;

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
    }

    public JSONObject parseCommand(String input) throws Exception
    {
        return new JSONObject(input);
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
