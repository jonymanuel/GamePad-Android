package com.gamepad;

import android.app.Activity;
import android.os.Bundle;
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
    private ListView lvLobbies;
    private ArrayList<String> lobbies;
    ArrayAdapter lvLobbiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GPC.setJoinMode();

        lvLobbies = (ListView)findViewById(R.id.listView_lobbies);
        lvLobbiesAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lobbies);
        lvLobbies.setAdapter(lvLobbiesAdapter);
        updateLobbyList();

        GPC.getJoin().addLobbyUpdateEventListener(new LobbyUpdateEvent() {
            @Override
            public void onLobbyUpdate() {
                updateLobbyList();
            }
        });
    }

    private void updateLobbyList()
    {
        lobbies.clear();
        for(Lobby lobby : GPC.getJoin().getLobbies())
        {
            String name = lobby.getName();
            String game = lobby.getGameName();
            String max = String.valueOf(lobby.getMaxPlayers());
            String cur = String.valueOf(lobby.getPlayers().size());
            String text = String.format("Name: %s Game: %s (%s/%s)", name, game, max, cur);
            lobbies.add(text);
        }
        lvLobbiesAdapter.notifyDataSetChanged();
    }


}
