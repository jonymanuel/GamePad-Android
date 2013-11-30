package com.gamepad.lib.net;

/**
 * Created by root on 04.10.13.
 */
public class NetworkStation
{

    private Byte[] ipAddress;
    private long id;
    private NetworkStationType type;

    public NetworkStation(Byte[] ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public Byte[] getIpAddress()
    {
        return ipAddress;
    }

    public long getIdentifier()
    {
        return id;
    }

    public NetworkStationType getType()
    {
        return type;
    }
}
