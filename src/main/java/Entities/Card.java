package Entities;

public class Card {

    //Every card has a suit
    private int cardNumber;
    private Suits cardSuit;

    //GETTERS AND SETTERS FOR ALL PRIVATE PROPERTIES
    public void setNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setSuit(Suits cardSuit) {
        this.cardSuit = cardSuit;
    }

    //comment
    public int getNumber() {
        return cardNumber;
    }

    public Suits getSuit() {
        return cardSuit;
    }

    //CONSTRUCTORS
    Card() {
    }
    public Card(int num, Suits s) {
        cardNumber = num;
        cardSuit = s;
    }



    public String toString() {
        return this.cardNumber + " " + this.cardSuit;
    }
}

