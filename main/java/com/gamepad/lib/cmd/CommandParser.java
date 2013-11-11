package com.gamepad.lib.cmd;

import com.gamepad.lib.cmd.commands.PingCommand;

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
        commands.add(new PingCommand());
    }

    public ICommand parseCommand(String input) throws Exception
    {
        String[] tempSplitArray = input.split(" ");
        if(tempSplitArray.length <= 0)
        {
            throw new Exception("Invalid command: probably empty");
        }
        String commandString = tempSplitArray[0];
        ArrayList<String> arguments = new ArrayList<String>();
        for(int i = 1; i< tempSplitArray.length; i++)
        {
            arguments.add(tempSplitArray[i]);
        }
        ICommand command = findCommandByCommandString(commandString);
        if(command != null)
        {
            return command;
        }
        else
        {
            throw new Exception("Invalid command: could not find it");
        }
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
