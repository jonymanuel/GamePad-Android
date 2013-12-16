package com.gamepad.lib.net;

/**
 * Created by root on 04.10.13.
 */
public class NetworkStation
{

    private Integer[] ipAddress;
    private long id;
    private NetworkStationType type;

    public NetworkStation(Integer[] ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public Integer[] getIpAddress()
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
