package com.gamepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ListView;

import com.gamepad.lib.GPC;

import java.util.ArrayList;

public class HostActivity extends Activity
{
    private ArrayList<String> games;
    private String texasPoker = "Poker";
    private String monopoly = "Monopoly";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        final HorizontalScrollView lv = (HorizontalScrollView)findViewById(R.id.horizontalScrollView);
        games = new ArrayList<String>();
        games.add(texasPoker);
        games.add(monopoly);
        lv.setClickable(true);

        lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = lv.indexOfChild(view);
                String s = games.get(i);
                if (s.equals(texasPoker)) {
                    createPokerLobby();
                } else if (s.equals(monopoly)) {
                    createMonopolyLobby();
                }
            }
        });
        GPC.setHostMode();
    }

    private void createMonopolyLobby()
    {
        GPC.getHost().createLobby(monopoly + " by Unknown");
        GPC.getHost().getLobby().setGameName(monopoly);
        Intent intent = new Intent(this, LobbyActivity.class);
        startActivity(intent);
    }

    private void createPokerLobby()
    {
        GPC.getHost().createLobby(texasPoker + " by Unknown");
        GPC.getHost().getLobby().setGameName(texasPoker);
        Intent intent = new Intent(this, LobbyActivity.class);
        startActivity(intent);
    }


}
