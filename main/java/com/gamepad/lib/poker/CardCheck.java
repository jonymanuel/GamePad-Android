package com.gamepad.lib.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CardCheck {

    public CardCheck() {
        //Empty Constructor
    }

    /**
     * Gets the highest combination of the given Cards
     * @param cards
     * @return A result object containing the winningCards and winning combination name
     */
    public Result check(ArrayList<Card> cards)
    {
        sortCards(cards);
        ArrayList<Card> winningCards = new ArrayList<Card>();
        int combinationID;
        String winningCombination;
        if(sequentialCheck(cards) != null) {        //Royal flush, Straight Flush, Straight
            winningCards = sequentialCheck(cards);
            if(suitCheck(winningCards)) {
                if(getLastCard(winningCards).getRank() == 1) {
                    combinationID = 8;
                    winningCombination = "Royal Flush";
                }
                else {
                    combinationID = 7;
                    winningCombination = "Straight Flush";
                }
            }
            else {
                combinationID = 6;
                winningCombination = "Straight";
            }
        }
        else if(rankCheck(4, cards) != null) {      //Four of a Kind
            winningCards = rankCheck(4,cards);
            combinationID = 5;
            winningCombination = "Four of a Kind";
        }
        else if(rankCheck(3, cards) != null) {      //Full House, Three of a Kind
            if(rankCheck(2, cards) != null) {
                winningCards.addAll(rankCheck(2, cards));
                winningCards.addAll(rankCheck(3, cards));
                combinationID = 5;
                winningCombination = "Full House";
            }
            else {
                winningCards = rankCheck(3, cards);
                combinationID = 4;
                winningCombination = "Three of a Kind";
            }
        }
        else if(suitCheck(5, cards) != null) {      //Flush
            winningCards = suitCheck(5, cards);
            combinationID = 3;
            winningCombination = "Flush";
        }
        else if(twoPairCheck(cards) != null) {      //Two Pair
            winningCards = twoPairCheck(cards);
            combinationID = 2;
            winningCombination = "Two Pair";
        }
        else if(rankCheck(2, cards) != null) {      //One Pair
            winningCards = rankCheck(2, cards);
            combinationID = 1;
            winningCombination = "One Pair";
        }
        else {                                      //High Card
            winningCards.add(getHighestCard(cards));
            combinationID = 0;
            winningCombination = "High Card";
        }
        return new Result(winningCards, winningCombination, combinationID);
    }

    /**
     * Check if there is a straight
     * @param cards
     * @return The 5 cards that are forming a straight
     */
    private ArrayList<Card> sequentialCheck(ArrayList<Card> cards)
    {
        ArrayList<Card> winningCards = new ArrayList<Card>();
        for(Card card : cards) {
            if(winningCards.isEmpty()) {
                winningCards.add(card);
            }
            else {
                if(getLastCard(winningCards).getRank() == 13 && cards.get(0).getRank() == 1) {
                    winningCards.add(card);
                    winningCards.add(cards.get(0));
                }
                else if(getLastCard(winningCards).getRank() == card.getRank() - 1) {
                    winningCards.add(card);
                }
                else if(getLastCard(winningCards).getRank() == card.getRank()) {
                    if(getLastCard(winningCards).getSuit() > card.getSuit())
                    {
                        winningCards.remove(winningCards.size() - 1);
                        winningCards.add(card);
                    }
                }
                else if(winningCards.size() <= 2) {     //Not enough cards left for straight
                    winningCards.clear();
                    winningCards.add(card);
                }
            }
        }
        if(getLastCard(winningCards).getRank() == 13 && cards.get(0).getRank() == 1) {
            winningCards.add(cards.get(0));
        }
        if(winningCards.size() > 4) {       //Enough cards to make a straight
            /*if(winningCards.size() > 5) {
                while (winningCards.size() != 5) {
                    winningCards.remove(0);
                }
            }*/
            return winningCards;
        }
        return null;
    }

    /**
     * Checks if ALL given cards are the same suit
     * @param cards
     * @return
     */
    private boolean suitCheck(ArrayList<Card> cards)
    {
        boolean sameSuit;
        for(int i = 0; i < 4; i++) {
            sameSuit = true;
            for(Card card : cards) {
                if(card.getSuit() != i) {
                    sameSuit = false;
                }
            }
            if(sameSuit == true) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the suit is present for a given number of times
     * @param occurrence Times the suit has to be present
     * @param cards
     * @return ArrayList with all the cards that are the same suit AND occure the given amount of times
     */
    private ArrayList<Card> suitCheck(int occurrence, ArrayList<Card> cards)
    {
        ArrayList<Card> winningCards = new ArrayList<Card>();
        int occurrences;
        for(int i = 0; i < 4; i++) {            //0 - 4, used for the four suits
            winningCards.clear();
            occurrences = 0;
            for(Card card : cards) {
                if(card.getSuit() == i) {
                    occurrences++;
                    winningCards.add(card);
                }
            }
            if(occurrences == occurrence) {
                return winningCards;
            }
        }
        return null;
    }

    /**
     * Checks whether the rank is present for a given number of times
     * @param occurrence Times the rank has to be present
     * @param cards
     * @return ArrayList with all the cards that are the same rank AND occurre the given amount of times
     */
    private ArrayList<Card> rankCheck(int occurrence, ArrayList<Card> cards)
    {
        ArrayList<Card> winningCards = new ArrayList<Card>();
        int occurrences;
        for(int i = 1; i <= 13; i++) {           //1 - 13, used for card ranks
            winningCards.clear();
            occurrences = 0;
            for(Card card : cards) {
                if(card.getRank() == i) {
                    occurrences++;
                    winningCards.add(card);
                }
            }
            if(occurrences == occurrence) {
                return winningCards;
            }
        }
        return null;
    }

    /**
     * Checks whether there are two pair present
     * @param cards
     * @return The cards forming pairs
     */
    private ArrayList<Card> twoPairCheck(ArrayList<Card> cards)
    {
        ArrayList<Card> winningCards = new ArrayList<Card>();
        ArrayList<Card> tempCards = new ArrayList<Card>();
        for(Card card: cards)
        {
            tempCards.add(card);
        }
        if(rankCheck(2, cards) != null) {
            winningCards.addAll(rankCheck(2, cards));
        }
        int size = tempCards.size();
        for(int i = size - 1; i >= 0; i--) {
            for(Card card2 : winningCards) {
                if(tempCards.get(i).getRank() == card2.getRank() && tempCards.get(i).getSuit() == card2.getSuit())
                {
                    winningCards.add(tempCards.remove(i));
                    i--;
                }
            }
        }
        if(rankCheck(2, tempCards) != null) {
            winningCards.addAll(rankCheck(2, tempCards));
            return winningCards;
        }
        return null;
    }

    /**
     * Returns the last card of the ArrayList
     * @param cards
     * @return Card object at last place
     */
    private Card getLastCard(ArrayList<Card> cards)
    {
        return cards.get(cards.size() - 1);
    }

    /**
     * Gets the highest card of the given ArrayList
     * @param cards
     * @return Highest Card object
     */
    private Card getHighestCard(ArrayList<Card> cards)
    {
        sortCards(cards);
        if(cards.get(0).getRank() == 1) {
            return cards.get(0);
        }
        return cards.get(cards.size() - 1);
    }

    /**
     * Sorts the ArrayList from low to high RANK
     * @param cards
     * @return A sorted ArrayList
     */
    private ArrayList<Card> sortCards(ArrayList cards)
    {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card card1, Card card2) {
                return Double.compare(card1.getRank(), card2.getRank());
            }
        });
        return cards;
    }
}
