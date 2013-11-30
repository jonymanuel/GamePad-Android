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
        if(arguments == null)
        {
            return false;
        }
        if(arguments.length <= 0)
        {
            return false;
        }
        //get the ip address
        String ipAddress = arguments[0];

        //create a new ping packet
        Packet pingPacket = new Packet("pong");

        //get the local ipAddress
        IpAddress localIp = MainActivity.getBaseGPC().getNetwork().getLocalIp();

        //create a networkstation which represents this local client
        NetworkStation local =  new NetworkStation(localIp.getBytes());

        //set the packet "from" property
        pingPacket.setFrom(local);

        IpAddress destinationIp = null;
        try { destinationIp = IpAddress.parse(ipAddress);  } catch(Exception ex){}

        //get our packet destination which is in the arguments
        NetworkStation destination = new NetworkStation(destinationIp.getBytes());
        MainActivity.getBaseGPC().getNetwork().sendPacket(pingPacket, destination);
        return true;
    }

    @Override
    public void setArguments(String[] args)
    {
        this.arguments = args;
    }


}
