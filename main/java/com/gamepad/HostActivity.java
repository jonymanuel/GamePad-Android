package com.gamepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gamepad.lib.game.GameManager;

public class HostActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        ImageView btnTh = (ImageView) findViewById(R.id.pokerGame);
        btnTh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                createLobby();
            }
        });

        ImageView btnCh = (ImageView) findViewById(R.id.chessGame);
        btnCh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dummyGame();
            }
        });

        ImageView btnMp = (ImageView) findViewById(R.id.monopolyGame);
        btnMp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dummyGame();
            }
        });
    }

    private void dummyGame()
    {
        //Intent intent = new Intent(this, GameActivity.class);
       // startActivity(intent);
        Toast.makeText(getApplicationContext(),"Dummy Game", Toast.LENGTH_LONG).show();
    }

    private void createLobby()
    {

    }
}
