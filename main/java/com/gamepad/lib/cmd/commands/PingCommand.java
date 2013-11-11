package com.gamepad.lib.cmd.commands;

import com.gamepad.lib.cmd.ICommand;

/**
 * Author: root
 * Date: 07.10.13.
 */
public class PingCommand implements ICommand
{
    private String[] arguments;
    @Override
    public String[] getArguments()
    {
        return arguments;
    }

    @Override
    public String getCommandString()
    {
        return "ping";
    }

    @Override
    public Boolean runCommand()
    {

        return true;
    }

    @Override
    public void setArguments()
    {

    }


}
