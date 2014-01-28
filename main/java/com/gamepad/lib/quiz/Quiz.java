package com.gamepad.lib.quiz;

import com.gamepad.lib.GPC;
import com.gamepad.lib.helpers.BetterRandom;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabian on 27.01.14.
 */
public class Quiz {
    ArrayList<Question> questions;
    ArrayList<Answer> answers;
    int currentQuestion;
    int[] currentAnswers;
    ArrayList<QuizPlayer> players;
    int answered;

    public Quiz() {
        questions = new ArrayList<Question>();
        answers = new ArrayList<Answer>();
        players = new ArrayList<QuizPlayer>();
        buildQuestionsAndAnswers();
    }

    public void addPlayer(QuizPlayer p)
    {
        players.add(p);
    }

    public boolean checkAnswer(int question, int answer) {
        for (Question q : questions) {
            if (q.getAnswer() == answer) {
                return true;
            }
        }
        return false;
    }

    public String getQuestionFromId(int id) {
        for (Question q : questions) {
            if (q.getId() == id) {
                return q.getText();
            }
        }
        return "";
    }

    public String getAnswerFromId(int id) {
        for (Answer a : answers) {
            if (a.getId() == id) {
                return a.getText();
            }
        }
        return "";
    }

    public void answerQuestion(int q, int a) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("cmd", "answer");
            obj.put("answer", a);
            obj.put("question", q);
            obj.put("playerName", GPC.getJoin().getLocalPlayerName());
        } catch (Exception ex) {
        }
        GPC.getJoin().sendToHost(obj);
    }

    public QuizPlayer getPlayer(String name) {
        for (QuizPlayer qp : players) {
            if (qp.getName().equals(name)) {
                return qp;
            }
        }
        return null;
    }

    public void playerAnswered(String player, int question, int answer) {
        if (checkAnswer(question, answer)) {
            try {
                getPlayer(player).increasePoints(1);
            } catch (Exception ex) {
            }
        }
        answered++;
        if(answered == players.size() - 1)
        {
            answered = 0;
            nextQuestion();
        }
    }

    public JSONObject prepareUpdatePacket() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("cmd", "newQuestion");
            obj.put("currentQuestion", currentQuestion);
            obj.put("currentAnswer1", currentAnswers[0]);
            obj.put("currentAnswer2", currentAnswers[1]);
            obj.put("currentAnswer3", currentAnswers[2]);
            obj.put("currentAnswer4", currentAnswers[3]);
        } catch (Exception ex) {
        }
        return obj;
    }

    public void updateFromData(JSONObject data) {
        try {
            currentQuestion = data.getInt("currentQuestion");
            int a1 = data.getInt("currentAnswer1");
            int a2 = data.getInt("currentAnswer2");
            int a3 = data.getInt("currentAnswer3");
            int a4 = data.getInt("currentAnswer4");
            currentAnswers = new int[]{a1, a2, a3, a4};
        } catch (Exception ex) {
        }
    }

    public void nextQuestion() {
        currentQuestion = BetterRandom.nextInt(questions.size());
        int a1, a2, a3, a4;
        a1 = generateAnswer(new int[0]);
        a2 = generateAnswer(new int[]{a1});
        a3 = generateAnswer(new int[]{a1, a2});
        a4 = generateAnswer(new int[]{a1, a2, a3});
        currentAnswers = new int[]{a1, a2, a3, a4};
        for(QuizPlayer player :players)
        {
            GPC.getHost().sendPacketToPlayer(player, prepareUpdatePacket());
        }
    }

    public int generateAnswer(int[] forbidden) {
        int res;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i : forbidden)
            temp.add(i);
        while (temp.contains(res = BetterRandom.nextInt(answers.size()))) ;
        return res;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public int[] getCurrentAnswers() {
        return currentAnswers;
    }

    public void buildQuestionsAndAnswers() {
        Answer a1 = new Answer(0, "Barrack Obama");
        Answer a2 = new Answer(1, "Amsterdam");
        Answer a3 = new Answer(2, "Jackson");
        Answer a4 = new Answer(3, "Peter griffin");
        Question q1 = new Question(0, "Who is the president of the united states?", 0);
        Question q2 = new Question(1, "Who is the main character in Family Guy?", 3);
        Question q3 = new Question(2, "What is the capital city of the Netherlands?", 1);
        Question q4 = new Question(3, "What is the last name of Micheal Jackson?", 2);
        answers.add(a1);
        answers.add(a2);
        answers.add(a3);
        answers.add(a4);
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);

    }
}
