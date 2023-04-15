# Swiggy-Card-Game-Challenge
Card Game code explanation


## Entities

### CARD - This class defines a card with two properties 1st SUIT and 2nd NUMBER and contains getters and setters for the same 

### DECK - This Class conatins a list that gets initialised with 52 Card class objects which are standard playing cards . It shuffles the card arraylist with the collections.shuffle method

### PLAYER - A player has an ID , A hand of cards (that is a list of 5 cards pisked from the shuffeled deck. this list can increase and decrease in size with the game progress) . Aplayer can remove a card from his hhand and add a card to his hand 
          
### SUITS -  Simply an enum class with the four suits - Spade , Club , hearts , Diamonds



## Game Service

1. The game deck is initialized and shuffeled
2. one card(from the top of the deck) is taken and this card now becomes the discardPile
3. The game will ask how many players will play ..min 2 and max 4 players are allowed. These players will be initialized and added to the players list
4. Each player is given a hand(list) of five cards to start the game with



### Game Loop
Each player gets one turn and the process continues till the game is over

1. Current player picks a card from discard pile(from its top) and check if he has the same card or not . If he has the same (number or suit) card then he can remove that card from his hand

2. If the player dosent have that card then it gets added to his hand(that is his personal list)


3. If  a card is an action card(king , queen , jack , ace) then some extra behaviours have to be followed depending on the card like reverse the sequence of turns , or pick four cards at once from draq pile)

4. If at any ppoint the draw pile becomes empty(the game is draw) or any players hand gets empty then that player wins and game is over




















