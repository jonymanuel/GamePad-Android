package com.gamepad.lib.poker;

public class Round  {

    private int step = 0;
    private HostGame hostGame;

    public Round(HostGame tg) {
        this.hostGame = tg;
    }

    public void nextStep() {

        switch (step) {
            case 0:
                // change text button to:   DEAL CARD
                hostGame.setBtnText("DEAL CARDS");
                break;
            case 1:
                // Deal cards to players
                hostGame.playCards(0);

                // change text button to:   THE FLOP
                hostGame.setBtnText("THE FLOP");
                break;
            case 2:
                // Deal first three cards
                hostGame.playCards(1);

                // change text button to:   THE TURN
                hostGame.setBtnText("THE TURN");
                break;
            case 3:
                // Deal fourth card to
                hostGame.playCards(2);

                // change text button to:   THE RIVER
                hostGame.setBtnText("THE RIVER");
                break;
            case 4:
                // Deal fifth card
                hostGame.playCards(3);

                // change text button to:   RATE HANDS
                hostGame.setBtnText("RATE HANDS");
                break;
            case 5:
                // Show players cards

                // Rate them
                hostGame.pickWinner();

                // Pick winner
                hostGame.pickWinner();

                hostGame.setBtnText("NEW ROUND");

                break;
            case 6:
                // Clear steps
                hostGame.nextGame();
                break;
        }

        step++;
    }

    public void reset() {
        step = 0;
    }
}
