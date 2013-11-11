package com.gamepad.lib.net;

/**
 * Created by root on 04.10.13.
 */
public class NetworkStation
{

    private byte[] ipAddress;
    private long id;
    private NetworkStationType type;

    public NetworkStation(byte[] ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public byte[] getIpAddress()
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
