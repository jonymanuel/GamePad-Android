package com.gamepad;

import android.app.Activity;
import android.os.Bundle;

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
