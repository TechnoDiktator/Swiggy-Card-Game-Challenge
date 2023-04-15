package Entities;

import java.util.ArrayList;
import java.util.List;


public class Player {


    private int playerId;
    //Hand - Represents the current cards the player is holding . At the start of the game he willl have 5 cards 
    private ArrayList<Card> hand = new ArrayList<>();

    public Player(int playerId) {
        this.playerId = playerId;
    }


    //when the player picks a card from the pile this card is added to his hand
    public void addCard(Card card) {
        hand.add(card);
    }

    //when a player does a move the card he plays gets removed from his hand
    public void removeCard(Card card) {
        hand.remove(card);
    }

    
    //GETTERS AND SETTERS
    public int getPlayerId() {
        return playerId;
    }


    public ArrayList<Card> getHand() {
        return hand;
    }

    
}
