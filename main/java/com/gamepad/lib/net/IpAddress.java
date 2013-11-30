package com.gamepad.lib.net;

import java.util.ArrayList;

/**
 * Created by root on 05.10.13.
 */
public class IpAddress
{
    private Byte[] ipAddress;
    //commit change

    public IpAddress(Byte[] ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public IpAddress(String ip) throws IllegalArgumentException
    {
        if(!tryParse(ip, this))
        {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString()
    {
        return ipAddress[0] + "." + ipAddress[1] + "." + ipAddress[2] + "." + ipAddress[3];
    }

    public Byte[] getBytes()
    {
        return ipAddress;
    }

    public static IpAddress parse(String ip) throws Exception
    {
        IpAddress result = null;
        if(!tryParse(ip, result))
        {
            throw new IllegalArgumentException("Could not parse ip: invalid format");
        }
        if(result == null)
        {
            throw new Exception("Something went horribly wrong");
        }
        return result;
    }

    public static Boolean tryParse(String ip, IpAddress objectToSaveIn)
    {
        int currentPosition = 0;
        //192.168.1.1
        String[] ipParts = ip.split(".");
        if (ipParts.length != 3)
        {
            return false;
        }
        ArrayList<Byte> ipPartList = new ArrayList<Byte>();
        for(String ipPart : ipParts)
        {
            if(ipPart.equals(""))
            {
                return false;
            }
            ipPartList.add(Byte.parseByte(ipPart));
        }
        objectToSaveIn = new IpAddress(ipPartList.toArray(new Byte[0]));
        return true;
    }


}