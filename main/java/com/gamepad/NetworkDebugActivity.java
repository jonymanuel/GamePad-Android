package com.gamepad;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gamepad.lib.net.Network;

import java.util.ArrayList;

public class NetworkDebugActivity extends Activity
{
    private ListView lvNetworkDebug;
    private ArrayList<String> clients;
    private Network network;

    static Context appContext;

    public static Context getContext()
    {
        return appContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_debug);
        appContext = getApplicationContext();
        lvNetworkDebug = (ListView) findViewById(R.id.listView_clients);
        clients = new ArrayList<String>();
        clients.add("testVal");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, clients);
        lvNetworkDebug.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        network = MainActivity.getBaseGPC().getNetwork();
        network.startSearchHosts();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.network_debug, menu);
        return true;
    }

}
