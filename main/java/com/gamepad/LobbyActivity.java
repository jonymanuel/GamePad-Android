package com.gamepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gamepad.lib.GPC;
import com.gamepad.lib.game.Lobby;
import com.gamepad.lib.game.LobbyPlayer;

import java.util.ArrayList;

/**
 * Created by Fabian on 04.01.14.
 */
public class LobbyActivity extends Activity {
    private ListView lvNetworkDebug;
    private ArrayList<String> clients;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        lvNetworkDebug = (ListView) findViewById(R.id.listView_clients);
        clients = new ArrayList<String>();
        for(LobbyPlayer player : GPC.getHost().getLobby().getPlayers())
        {
            clients.add(player.getIp().toString());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, clients);
        lvNetworkDebug.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
