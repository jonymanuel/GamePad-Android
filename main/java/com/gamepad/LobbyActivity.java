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
        */
        //Fake lobby maken


        /*
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
