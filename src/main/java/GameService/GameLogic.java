package GameService;

import java.util.ArrayList;
import java.util.Scanner;

import Entities.Card;
import Entities.Deck;
import Entities.Player;

public class GameLogic {
    private ArrayList<Card> deck;
    private ArrayList<Player> players;
    private ArrayList<Card> drawPile;
    private ArrayList<Card> discardPile;


    public void playGame() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Please input a number of players. The maximum number of players is 4, and the minimum number is 2.");
        int numOfPlayers = sc.nextInt();


        if (numOfPlayers < 2 || numOfPlayers > 4) { //ckeack valid no of players
            throw new Exception("Invalid number of players ... Retry again....");
        }



        deck = new Deck().getDeck(); //deck initialized aand shuffeled

        //list of the currently playing players
        players = new ArrayList<>(); 
        //giving 5 cards to each player at the start
        for (int i = 1; i <= numOfPlayers; i++) {
            Player p = new Player(i);
            for (int j = 1; j <= 5; j++) {
                p.addCard(deck.get(deck.size() - 1));
                deck.remove(deck.size() - 1);
            }
            players.add(p);

        }


        //initilizing the discardPile with one card from the deck
        discardPile = new ArrayList<>();
        discardPile.add(deck.get(0));
        deck.remove(0);

        //all the remaining cards go to the deck
        drawPile = new ArrayList<>();
        for (Card c : deck) {
            drawPile.add(c);
        }

   

        int playerTurn = 0; //player 0 starts the game
        int direction = 1; //initial direction

        //initially only one card has to be picked by a player
        int numCardsTake = 1;

        while (true) {

            //if draw pile empties then game is over
            if (drawPile.size() < numCardsTake) { 
                System.out.println(
                        "The game has ended in a draw because there are not enough cards left to continue playing.");
                break;
            }

            playerTurn %= numOfPlayers;
            if (playerTurn < 0)
                playerTurn += numOfPlayers;
            // if playerturn goes to negative integers.
            playerTurn %= numOfPlayers;

            // current player will atleast have 1 card ... it is guarranteed
            boolean matched = false;
            int matchedNumber = -1;
            Card topDiscardCard = discardPile.get(discardPile.size() - 1);
            // top card of the discard pile with whom the player will try to match his
            // cards.
            System.out.println("Please discard the top card from the deck. = " + discardPile.get(discardPile.size() - 1));

            for (Card currentPlayerCard : players.get(playerTurn).getHand()) {

                // if number/suit of any of the hand cards match 
                if (currentPlayerCard.getNumber() == topDiscardCard.getNumber()
                        || currentPlayerCard.getSuit() == topDiscardCard.getSuit()) {
                    
                    
                    //if card is an action card         
                    if (topDiscardCard.getNumber() == 1 || topDiscardCard.getNumber() == 11
                            || topDiscardCard.getNumber() == 12 || topDiscardCard.getNumber() == 13) {
                        if (currentPlayerCard.getNumber() == topDiscardCard.getNumber())
                            // player cannot play same action card even if available so he will skip.
                            continue;
                    }
                    // if cards get matched then this statement will be printed.
                    System.out.println("Cards matched for player " + players.get(playerTurn).getPlayerId()
                            + " Card and new Discard Deck top card = " + currentPlayerCard);

                    //numcards take represents the number of cards current player has to pick
                    if (numCardsTake > 1) {
                        while (numCardsTake-- > 0) {
                            System.out.println("Drawing " + drawPile.get(drawPile.size() - 1) + " Card");

                            players.get(playerTurn).addCard(drawPile.get(drawPile.size() - 1));
                            drawPile.remove(drawPile.size() - 1);
                        }
                        numCardsTake = 1;
                    }

                    //player removes the matched card from his hand an
                    players.get(playerTurn).removeCard(currentPlayerCard);
                    discardPile.add(currentPlayerCard);
                    matched = true;
                    matchedNumber = currentPlayerCard.getNumber();
                    break;
                }
            }

            /*
             * if not matched then the player have to take a card from
             * the draw pile and keep it with him.
             */

            if (matched == false) {
                System.out.println("No cards match for player " + players.get(playerTurn).getPlayerId() + " Taking "
                        + numCardsTake + " Card(s)");
                /*
                 * number of cards to be taken by the current player
                 * from the draw pile, depending upon the previous player's
                 * turn.
                 */
                while (numCardsTake-- > 0) {
                    System.out.println("Drawing " + drawPile.get(drawPile.size() - 1) + " Card");
                    players.get(playerTurn).addCard(drawPile.get(drawPile.size() - 1));
                    drawPile.remove(drawPile.size() - 1);
                }
                numCardsTake = 1;
            }

            //if hand cards of a player are zero then he has won the match
            if (matched == true && players.get(playerTurn).getHand().size() == 0) {

                System.out.println(
                        "Player " + players.get(playerTurn).getPlayerId() + " won the match !!!");

                System.exit(0);
            }

            //if matched number is one then current(just next) players turn will be skipped
            if (matched == true && matchedNumber == 1) {
                playerTurn += direction;
            }
            
            //if matched card is King then reverse playing
            if (matched == true && matchedNumber == 13) {
                direction *= -1;
            }

            //if matched card is jack then next player has to pick 4 cards from the drawpile
            if (matched == true && matchedNumber == 11) {
                numCardsTake = 4;
            }

            //if matched card is queen then next player has to draw 2 cards from the draw pile
            if (matched == true && matchedNumber == 12) {
                numCardsTake = 2;
            }
            // to determine the next player whose gonna play.
            playerTurn += direction;
        }
    }
    
}
