package com.gamepad;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by Fabian on 04.01.14.
 */


public class LobbyActivity extends Activity {

    ListView listView;
    String joinedLobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
    }
}
