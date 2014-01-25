package com.gamepad;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gamepad.lib.GPC;
import com.gamepad.lib.game.Lobby;
import com.gamepad.lib.game.LobbyPlayer;
import com.gamepad.lib.game.LobbyUpdateEvent;

import java.util.ArrayList;

/**
 * Created by Fabian on 04.01.14.
 */


public class LobbyActivity extends Activity {

    ListView playerListView;
    ArrayAdapter lvAdapter;
    String joinedLobby;
    TextView lobbyName;
    TextView gameName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        initViews();
        refreshViews();
        hookEvents();
    }

    private LobbyUpdateEvent createUpdateEvent()
    {
        return new LobbyUpdateEvent() {
            @Override
            public void onLobbyUpdate() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshViews();
                    }
                });
            }
        };
    }

    private void hookEvents()
    {
        if (GPC.getIsHost()) {
            GPC.getHost().addLobbyUpdateEventListener(createUpdateEvent());
        } else {
            GPC.getJoin().addLobbyUpdateEventListener(createUpdateEvent());
        }
    }

    private void initViews() {
        lobbyName = (TextView) findViewById(R.id.lobbyName);
        gameName = (TextView) findViewById(R.id.gameName);
        playerListView = (ListView) findViewById(R.id.playerListView);
        lvAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        playerListView.setAdapter(lvAdapter);
    }

    private void refreshViews() {
        Lobby lob;
        if (GPC.getIsHost()) {
            lob = GPC.getHost().getLobby();
        } else {
            lob = GPC.getJoin().getCurrentLobby();
        }
        lobbyName.setText(lob.getName());
        gameName.setText(lob.getGameName());
        lvAdapter.clear();
        for(LobbyPlayer player : lob.getPlayers())
        {
            lvAdapter.add(player.getName());
        }
        lvAdapter.notifyDataSetChanged();
    }
}
