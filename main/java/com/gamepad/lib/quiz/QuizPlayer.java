package com.gamepad.lib.quiz;

import com.gamepad.lib.game.Lobby;
import com.gamepad.lib.game.LobbyPlayer;

/**
 * Created by Fabian on 27.01.14.
 */
public class QuizPlayer extends LobbyPlayer {
    int points;

    public static QuizPlayer fromLobbyPlayer(LobbyPlayer lp)
    {
        QuizPlayer res = new QuizPlayer();
        res.setName(lp.getName());
        res.setPort(lp.getPort());
        res.setIp(lp.getIp());
        res.points = 0;
        return res;
    }

    public int getPoints()
    {
        return points;
    }

    public void increasePoints(int mod)
    {
        points += mod;
    }
}
