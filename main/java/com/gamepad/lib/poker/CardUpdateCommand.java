package com.gamepad.lib.poker;

import com.gamepad.lib.cmd.ICommand;

import org.json.JSONObject;

/**
 * Created by Fabian on 26.01.14.
 */
public class CardUpdateCommand implements ICommand {
    @Override
    public String getCommandString() {
        return "cardUpdate";
    }

    @Override
    public Boolean runCommand(JSONObject input) throws Exception {
        int card1Suit = Integer.parseInt(input.getString("card1Suit"));
        int card1Rank = Integer.parseInt(input.getString("card1Rank"));
        int card2Suit = Integer.parseInt(input.getString("card2Suit"));
        int card2Rank = Integer.parseInt(input.getString("card2Rank"));
        MyPokerGame.getPokerClientActivity().drawCards(new Card(card1Suit, card1Rank), new Card(card2Suit, card2Rank));
        return true;
    }
}
