package com.gamepad.lib.net;

import java.net.InetAddress;

/**
 * Created by root on 04.10.13.
 */
public class Packet
{
    private String message;
    private InetAddress from;
    private InetAddress destination;

    public Packet(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public InetAddress getFrom()
    {
        return from;
    }

    public InetAddress getDestination() { return destination; }

    public void setFrom(InetAddress ipAddress)
    {
        this.from = ipAddress;
    }

    public void setDestination(InetAddress dest)
    {
        this.destination = dest;
    }

    public void setDestination(String inetAddress)
    {
        InetAddress addr = null;
        try { addr = InetAddress.getByName(inetAddress); } catch(Exception ex){};
        if(addr != null)
        {
            this.destination = addr;
        }
    }
}
