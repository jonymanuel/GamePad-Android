package com.gamepad.lib.net;

/**
 * Created by root on 04.10.13.
 */
public class Packet
{
    private String message;
    private NetworkStation from;

    public Packet(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public NetworkStation getFrom()
    {
        return from;
    }

    public void setFrom(NetworkStation ipAddress)
    {
        this.from = ipAddress;
    }
}
