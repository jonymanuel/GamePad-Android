package com.gamepad.lib;

import com.gamepad.lib.game.GameManager;
import com.gamepad.lib.game.Host;
import com.gamepad.lib.game.Join;
import com.gamepad.lib.net.Network;

/**
 * Created by root on 04.10.13.
 */
public class GPC
{
    private static Network network;
    private static Host host;
    private static Join join;

    public static void InitGamePad()
    {
        network = new Network();
        host = new Host();
        join = new Join();
    }

    public static Host getHost()
    {
        return host;
    }

    public static Join getJoin()
    {
        return join;
    }

    public static Network getNetwork()
    {
        return network;
    }
}
