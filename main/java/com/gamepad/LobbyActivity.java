package com.gamepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.gamepad.lib.GPC;
import com.gamepad.lib.game.Lobby;
import com.gamepad.lib.game.LobbyPlayer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fabian on 04.01.14.
 */
public class LobbyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        /*lvNetworkDebug = (ListView) findViewById(R.id.listView_clients);
        clients = new ArrayList<String>();
        for(LobbyPlayer player : GPC.getHost().getLobby().getPlayers())
        {
            clients.add(player.getIp().toString());
        }
        if(clients.size() > 0)
        {
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, clients);
            lvNetworkDebug.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        //Fake lobby maken

        Lobby lobbyTokkie = new Lobby();
        LobbyPlayer Tokkie = new LobbyPlayer();
        LobbyPlayer Tosti = new LobbyPlayer();
        Tokkie.setName("Tokkie");
        Tosti.setName("Tosti");
        lobbyTokkie.setGameName("Gabbertjee!");
        lobbyTokkie.setName("Is het een tokkie of gewoon een dik wijf");
        lobbyTokkie.addPlayer(Tokkie);
        lobbyTokkie.addPlayer(Tosti);
        GPC.getJoin().addLobby(lobbyTokkie);

        Lobby lobby2 = new Lobby();
        LobbyPlayer Panchito = new LobbyPlayer();
        LobbyPlayer Panchito2 = new LobbyPlayer();
        Panchito.setName("Panpanchito");
        Panchito2.setName("Beunhaas");
        lobby2.setGameName("twister");
        lobby2.setName("Salty dog");
        lobby2.addPlayer(Panchito);
        lobby2.addPlayer(Panchito2);
        GPC.getJoin().addLobby(lobby2);

        createGroupList();

        createCollection();

        expListView = (ExpandableListView) findViewById(R.id.laptop_list);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, laptopCollection);
        expListView.setAdapter(expListAdapter);
        expListAdapter.notifyDataSetChanged();
        //setGroupIndicatorToRight();

        expListView.setOnChildClickListener(new OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();

                return true;
            }
        });
        */
    }

}
