package com.gamepad.lib.quiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamepad.R;
import com.gamepad.lib.GPC;
import com.gamepad.lib.poker.CardUpdateCommand;
import com.gamepad.lib.poker.Deck;
import com.gamepad.lib.poker.ImageParser;
import com.gamepad.lib.poker.MyPokerGame;

import java.util.Vector;

/**
 * Created by Fabian on 27.01.14.
 */
public class QuizClientActivity extends Activity {
    TextView q;
    Button a1btn, a2btn, a3btn, a4btn;
    int a1, a2, a3, a4;
    boolean blocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_client);
        GPC.getCmdParser().RegisterCommand(new NewQuestionCommand());
        MyQuizGame.setQuiz(new Quiz());
        MyQuizGame.setQuizClientActivity(this);
        blocked = false;
        init();
    }

    private void init() {
        q = (TextView) findViewById(R.id.questionTextView);
        a1btn = (Button) findViewById(R.id.btn_a1);
        a1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(blocked)
                    return;
                MyQuizGame.getQuiz().answerQuestion(MyQuizGame.getQuiz().getCurrentQuestion(), a1);
                blocked = true;
            }
        });
        a2btn = (Button) findViewById(R.id.btn_a2);
        a2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(blocked)
                    return;
                MyQuizGame.getQuiz().answerQuestion(MyQuizGame.getQuiz().getCurrentQuestion(), a2);
                blocked = true;
            }
        });
        a3btn = (Button) findViewById(R.id.btn_a3);
        a3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(blocked)
                    return;
                MyQuizGame.getQuiz().answerQuestion(MyQuizGame.getQuiz().getCurrentQuestion(), a3);
                blocked = true;
            }
        });
        a4btn = (Button) findViewById(R.id.btn_a4);
        a4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(blocked)
                    return;
                MyQuizGame.getQuiz().answerQuestion(MyQuizGame.getQuiz().getCurrentQuestion(), a4);
                blocked = true;
            }
        });
    }

    public void updateViews() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int[] a = MyQuizGame.getQuiz().getCurrentAnswers();
                a1 = a[0];
                a2 = a[1];
                a3 = a[2];
                a4 = a[3];
                a1btn.setText(MyQuizGame.getQuiz().getAnswerFromId(a1));
                a2btn.setText(MyQuizGame.getQuiz().getAnswerFromId(a2));
                a3btn.setText(MyQuizGame.getQuiz().getAnswerFromId(a3));
                a4btn.setText(MyQuizGame.getQuiz().getAnswerFromId(a4));
                q.setText(MyQuizGame.getQuiz().getQuestionFromId(MyQuizGame.getQuiz().currentQuestion));
                blocked = false;
            }
        });

    }
}
