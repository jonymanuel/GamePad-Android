package com.gamepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gamepad.lib.GPC;
import com.gamepad.lib.game.GameStartedEvent;
import com.gamepad.lib.game.Lobby;
import com.gamepad.lib.game.LobbyPlayer;
import com.gamepad.lib.game.LobbyUpdateEvent;
import com.gamepad.lib.poker.PokerHostActivity;
import com.gamepad.lib.poker.PokerClientActivity;
import com.gamepad.lib.quiz.QuizClientActivity;
import com.gamepad.lib.quiz.QuizHostActivity;

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
    Button btnStart, btnLeave;
    LobbyUpdateEvent lobbyUpdateEvent;
    GameStartedEvent gameStartedEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        initViews();
        refreshViews();
        hookEvents();
    }

    private GameStartedEvent createGameStartEvent() {
        if (gameStartedEvent == null) {
            gameStartedEvent = new GameStartedEvent() {
                @Override
                public void gameStarted() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gameStartedEvent();
                        }
                    });
                }
            };
        }
        return gameStartedEvent;
    }

    private void gameStartedEvent() {
        if (GPC.getJoin().getCurrentLobby().getGameName().equals("Quiz")) {
            Intent intent = new Intent(this, QuizClientActivity.class);
            startActivity(intent);
        } else if (GPC.getJoin().getCurrentLobby().getGameName().equals("Poker")) {
            Intent intent = new Intent(this, PokerClientActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
    }

    private LobbyUpdateEvent createUpdateEvent() {
        if (lobbyUpdateEvent == null) {
            lobbyUpdateEvent = new LobbyUpdateEvent() {
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
        return lobbyUpdateEvent;
    }

    private void unhookEvents() {
        if (GPC.getIsHost()) {
            GPC.getHost().removeLobbyUpdateEventListener(createUpdateEvent());
        } else {
            GPC.getJoin().removeLobbyUpdateEventListener(createUpdateEvent());
            GPC.getJoin().removeGameStartedEventListener(createGameStartEvent());

        }
    }

    private void hookEvents() {
        if (GPC.getIsHost()) {
            GPC.getHost().addLobbyUpdateEventListener(createUpdateEvent());
        } else {
            GPC.getJoin().addLobbyUpdateEventListener(createUpdateEvent());
            GPC.getJoin().addGameStartedEventListener(createGameStartEvent());
        }
    }

    private void initViews() {
        lobbyName = (TextView) findViewById(R.id.lobbyName);
        gameName = (TextView) findViewById(R.id.gameName);
        playerListView = (ListView) findViewById(R.id.playerListView);
        lvAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        playerListView.setAdapter(lvAdapter);
        btnLeave = (Button) findViewById(R.id.btnLeaveLobby);
        btnLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leaveLobby();
            }
        });

        btnStart = (Button) findViewById(R.id.btnStartGame);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    private void startGame() {
        if (GPC.getIsHost()) {
            GPC.getHost().startGame();
            if (GPC.getHost().getLobby().getGameName().equals("Quiz")) {
                Intent intent = new Intent(this, QuizHostActivity.class);
                startActivity(intent);
            } else if (GPC.getHost().getLobby().getGameName().equals("Poker")) {
                Intent intent = new Intent(this, PokerHostActivity.class);
                startActivity(intent);
            }
        }
    }

    private void leaveLobby() {
        unhookEvents();
        if (GPC.getIsHost()) {
            GPC.getHost().destroyLobby();
            finish();
        } else {
            GPC.getJoin().leaveCurrentLobby();
            finish();
        }
    }

    private void refreshViews() {
        Lobby lob;
        if (GPC.getIsHost()) {
            lob = GPC.getHost().getLobby();
            btnLeave.setText("Destroy Lobby");

        } else {
            lob = GPC.getJoin().getCurrentLobby();
            if (lob == null) {
                leaveLobby();
                return;
            }
            btnStart.setVisibility(View.GONE);
        }
        lobbyName.setText(lob.getName());
        gameName.setText(lob.getGameName());
        lvAdapter.clear();
        synchronized (lob.getPlayers()) {
            for (LobbyPlayer player : lob.getPlayers()) {
                String toDisplay = player.getName();
                if (toDisplay.equals(GPC.getJoin().getLocalPlayerName())) {
                    toDisplay += " (You)";
                }
                lvAdapter.add(toDisplay);
            }
        }
        lvAdapter.notifyDataSetChanged();
    }
}
