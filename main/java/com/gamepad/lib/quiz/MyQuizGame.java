package com.gamepad.lib.quiz;

/**
 * Created by Fabian on 27.01.14.
 */
public class MyQuizGame {
    private static Quiz quiz;
    private static QuizClientActivity qca;
    private static QuizHostActivity qha;

    public static Quiz getQuiz() {
        return quiz;
    }

    public static void setQuiz(Quiz q) {
        quiz = q;
    }

    public static void setQuizClientActivity(QuizClientActivity q) {
        qca = q;
    }

    public static QuizClientActivity getQuizClientActivity() {
        return qca;
    }

    public static void setQuizHostActivity(QuizHostActivity q) {
        qha = q;
    }

    public static QuizHostActivity getQuizHostActivity() {
        return qha;
    }
}
