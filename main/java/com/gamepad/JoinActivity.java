package com.gamepad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gamepad.lib.GPC;
import com.gamepad.lib.game.Lobby;
import com.gamepad.lib.game.LobbyUpdateEvent;

import java.util.ArrayList;

/**
 * Created by Fabian on 07.01.14.
 */
public class JoinActivity extends Activity
{
    ArrayList<String> lobbies;
    ArrayAdapter lvLobbiesAdapter;
    ListView lvLobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        GPC.setJoinMode();

        lvLobbies = (ListView)findViewById(R.id.listViewLobbies);
        lobbies  = new ArrayList<String>();
        lvLobbiesAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lobbies);
        lvLobbies.setAdapter(lvLobbiesAdapter);
        updateLobbyList();

        GPC.getJoin().addLobbyUpdateEventListener(new LobbyUpdateEvent() {
            @Override
            public void onLobbyUpdate() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateLobbyList();
                    }
                });
            }
        });

        lvLobbies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Lobby toJoin = GPC.getJoin().getLobbies().get(i);
                try{
                GPC.getJoin().requestJoin(toJoin);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void updateLobbyList()
    {
        lvLobbiesAdapter.clear();
        for(Lobby lobby : GPC.getJoin().getLobbies())
        {
            String name = lobby.getName();
            String game = lobby.getGameName();
            String max = String.valueOf(lobby.getMaxPlayers());
            String cur = String.valueOf(lobby.getPlayers().size());
            String text = String.format("Name: %s Game: %s (%s/%s)", name, game, max, cur);

            lvLobbiesAdapter.add(text);
        }

        lvLobbiesAdapter.notifyDataSetChanged();
    }


}
