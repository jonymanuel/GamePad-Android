package com.gamepad.lib.net;

import android.util.Log;

import junit.framework.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by root on 04.10.13.
 */
public class NetworkListenerRunnable implements Runnable
{
    private Vector _listeners;
    private static ServerSocket server;
    private static Boolean socketCreated = false;

    public void addPacketEventListener(PacketEvent listener)
    {
        if (_listeners == null)
        {
            _listeners = new Vector();
        }
        _listeners.addElement(listener);
    }

    private void firePacketEvent(Packet packet)
    {
        if (_listeners != null && _listeners.isEmpty())
        {
            Enumeration e = _listeners.elements();
            while (e.hasMoreElements())
            {
                PacketEvent pe = (PacketEvent) e.nextElement();
                pe.newPacket(packet);
            }
        }
    }

    private String readMessage(Socket socket) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        char[] buffer = new char[200];
        int count = bufferedReader.read(buffer, 0, 200);
        String message = new String(buffer, 0, count);
        return message;
    }

    public void run()
    {
        Boolean fatalError = false;
        server = null;
        if (!socketCreated)
        {
            try
            {
                server = new ServerSocket(56020);
                socketCreated = true;
            }
            catch (Exception ex)
            {
                fatalError = true;
                Log.e("NetworkListenerRunnable", ex.getMessage());

                return;
            }
        }
        else
        {
            Log.e("NetworkListenerRunnable", "Using existing instance from serverSocket");
        }
        Log.e("NetworkListenerRunnable", "Running");
        //Server thread
        while (true)
        {
            try
            {
                Socket client = server.accept();
                String message = readMessage(client);
                Packet packet = new Packet(message);
                firePacketEvent(packet);
                Log.e("NetworkListenerRunnable", "Got new message: " + message);
            }
            catch (Exception ex)
            {
                //Log.d("NetworkListenerRunnable", "Error: could not retrieve message");
            }
        }
    }
}
