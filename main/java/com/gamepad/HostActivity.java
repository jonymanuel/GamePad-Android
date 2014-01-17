package com.gamepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.gamepad.lib.GPC;
import com.gamepad.lib.poker.HostGame;

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
        initializeSliderButtons();
        GPC.setHostMode();
    }

    public void initializeSliderButtons() {

        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        int buttonHeight = (int) (metrics.heightPixels * 0.9);

        int gameCount = games.size();
        for(int i = 1; i <= gameCount; i++) {

            final int j = i;

            ImageButton tmpGameBtn = (ImageButton) findViewById( getResources().getIdentifier("game_" + Integer.toString(i), "id", getPackageName()) );

            ViewGroup.LayoutParams params = tmpGameBtn.getLayoutParams();
            params.width =  buttonHeight;
            tmpGameBtn.setLayoutParams(params);

            tmpGameBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(games.size() > j)
                    {
                        String s = games.get(j);
                        if (s.equals(texasPoker)) {
                            createPokerLobby();
                        } else if (s.equals(monopoly)) {
                            createMonopolyLobby();
                        }
                    }
                }
            });
        }
    }

    private void createMonopolyLobby()
    {
        GPC.getHost().createLobby(monopoly);
        GPC.getHost().getLobby().setGameName(monopoly);
        Intent intent = new Intent(this, LobbyActivity.class);
        startActivity(intent);
    }

    private void createPokerLobby()
    {
        GPC.getHost().createLobby(texasPoker);
        GPC.getHost().getLobby().setGameName(texasPoker);
        //Intent intent = new Intent(this, LobbyActivity.class);
        Intent intent = new Intent(this, HostGame.class);
        startActivity(intent);
    }


}
