package com.gamepad.lib.cmd.commands;

import com.gamepad.MainActivity;
import com.gamepad.lib.cmd.ICommand;
import com.gamepad.lib.net.IpAddress;
import com.gamepad.lib.net.NetworkStation;
import com.gamepad.lib.net.Packet;

/**
 * Author: root
 * Date: 07.10.13.
 * if the client receives ping, it will return pong
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
    public void setArguments(String[] args)
    {
        this.arguments = args;
    }


}
