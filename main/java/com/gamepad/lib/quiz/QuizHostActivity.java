package com.gamepad.lib.quiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.gamepad.R;
import com.gamepad.lib.GPC;
import com.gamepad.lib.game.LobbyPlayer;

import java.util.ArrayList;

/**
 * Created by Fabian on 27.01.14.
 */
public class QuizHostActivity extends Activity {
    ListView playerListView;
    ArrayAdapter lvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_host);
        GPC.getCmdParser().RegisterCommand(new AnswerCommand());
        MyQuizGame.setQuiz(new Quiz());
        for(LobbyPlayer lp : GPC.getHost().getLobby().getPlayers())
        {
            QuizPlayer qp = QuizPlayer.fromLobbyPlayer(lp);
            MyQuizGame.getQuiz().addPlayer(qp);
        }
        MyQuizGame.setQuizHostActivity(this);
        init();
        updateRankings();
    }


    private void init()
    {
        playerListView = (ListView) findViewById(R.id.quiz_ranks);
        lvAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        playerListView.setAdapter(lvAdapter);
        Button quest = (Button)findViewById(R.id.btnNextQuestion);
        quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyQuizGame.getQuiz().nextQuestion();
            }
        });
    }

    public void updateRankings()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lvAdapter.clear();
                int count = 1;
                for(int i = 0; i< MyQuizGame.getQuiz().players.size();i++)
                {

                    QuizPlayer qp = MyQuizGame.getQuiz().players.get(i);
                    if(qp.getName().equals(GPC.getJoin().getLocalPlayerName()))
                    {
                        continue;
                    }
                    lvAdapter.add((count++) + ": " + qp.getName() + " with " + qp.getPoints() + " point(s)");
                }
                lvAdapter.notifyDataSetChanged();
            }
        });

    }
}
