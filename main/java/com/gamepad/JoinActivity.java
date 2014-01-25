package com.gamepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.gamepad.lib.GPC;
import com.gamepad.lib.game.Lobby;
import com.gamepad.lib.game.LobbyJoinedEvent;
import com.gamepad.lib.game.LobbyPlayer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class JoinActivity extends Activity {
    LobbyAdapter elvLobbiesAdapter;
    ExpandableListView elvLobbies;
    LinkedHashMap<String, List<String>> lobbies = new LinkedHashMap<String, List<String>>();
    String joinedLobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        initRefreshButton();
        initLobbyListView();
        initLobbyJoinEventListener();
        GPC.setJoinMode();
    }

    private void initJoinButton() {

        Button btnJoin = (Button) findViewById(R.id.joinBn);
        if (btnJoin != null) {
            btnJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View view2 = view;
                }
            });
        }
    }

    private void initRefreshButton() {
        Button btnRefresh = (Button) findViewById(R.id.refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLobbyList();
            }
        });
    }

    private void initLobbyJoinEventListener() {
        GPC.getJoin().addLobbyJoinedEventListener(new LobbyJoinedEvent() {
            @Override
            public void lobbyJoined() {
                joinedLobby();
            }
        });
    }

    private void initLobbyListView() {
        elvLobbies = (ExpandableListView) findViewById(R.id.elv_lobbies);
        elvLobbies.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                final String selected = (String) elvLobbiesAdapter.getChild(groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG).show();

                return true;
            }
        });
    }

    private void openLobby(int id) {
        Intent intent = new Intent(this, LobbyActivity.class);
        startActivity(intent);
    }

    private void clickedOnListView(int i) {
        Lobby toJoin = GPC.getJoin().getLobbies().get(i);
        try {
            GPC.getJoin().requestJoin(toJoin);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
    * Get called when the client received a successful client join accepted packet
    * */
    public void joinedLobby() {

    }

    private void updateLobbyList() {
        lobbies.clear();
        for (Lobby lobby : GPC.getJoin().getLobbies()) {
            ArrayList<LobbyPlayer> lobbyPlayers = lobby.getPlayers();
            ArrayList<String> playersNames = new ArrayList<String>();
            String temp = "";
            for (int p = 0; p < lobbyPlayers.size(); p++) {
                playersNames.add(lobbyPlayers.get(p).getName());
            }
            lobbies.put(lobby.getName(), playersNames);

        }

        elvLobbiesAdapter = new LobbyAdapter(this, lobbies);
        elvLobbies.setAdapter(elvLobbiesAdapter);
        initJoinButton();
    }
}
