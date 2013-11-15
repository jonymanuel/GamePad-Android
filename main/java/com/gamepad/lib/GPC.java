package com.gamepad.lib;

import com.gamepad.lib.net.Network;

/**
 * Created by root on 04.10.13.
 */
public class GPC
{
    private Network network;

    public GPC()
    {
        network = new Network();
    }

    public Network getNetwork()
    {
        return network;
    }
}
