package com.gamepad.lib;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

import com.gamepad.MainActivity;
import com.gamepad.lib.cmd.CommandParser;
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
    private static PowerManager.WakeLock gameWakeLock;
    private static PowerManager powerManager;
    private static CommandParser commandParser;
    private static boolean isHost;

    public static void InitGamePad()
    {
        commandParser = new CommandParser();
        powerManager = (PowerManager)MainActivity.getContext().getSystemService(Context.POWER_SERVICE);
        gameWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "GamePadWakeLock");
        network = new Network();
        host = new Host();
        join = new Join();
    }

    public static CommandParser getCmdParser()
    {
        return commandParser;
    }

    public static void setHostMode()
    {
        join.clearMode();
        host.initMode();
        isHost = true;
    }

    public static void setJoinMode()
    {
        host.clearMode();
        join.initMode();
        isHost = false;
    }

    public static boolean getIsHost()
    {
        return isHost;
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

    public static void InitGameWakeLock()
    {
        gameWakeLock.acquire();
        Log.d("GPC", "Acquired gameWakeLock");
    }

    public static void ReleaseGameWakeLock()
    {

        gameWakeLock.release();
        Log.d("GPC", "Released gameWakeLock");
    }
}
