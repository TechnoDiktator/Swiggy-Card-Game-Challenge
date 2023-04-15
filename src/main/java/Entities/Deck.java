package Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck {
    // A standard card deck comprises of 52 cards
    // 13 cards per each suit . There are total 4 suits
    // In each set of 13 cards of a suit -  there are cards numbered from 2 to 10  .... an Ace(A) , a jack(J) , a Queen(Q) , a king(K)

    private ArrayList<Card> deck;

    public Deck() {

        deck = new ArrayList<Card>();

        for (Suits suits : Suits.values()) {
            for (int i = 1; i <= 13; i++) {
                deck.add(new Card(i, suits));
            }
        }
        Collections.shuffle(deck);
    }
    public ArrayList<Card> getDeck() {
        return deck;
    }
}