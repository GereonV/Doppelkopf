import java.util.ArrayList;
import java.util.Collections;

/**
 * Class representing all Cards needed for a Game.
 * Uses Nines if necessary
 *
 * @author Gereon
 * @version 1.0
 * @see GameManager
 * @see Card
 */
public class Deck {

    /**
     * cards in the deck
     */
    private final ArrayList<Card> cards;

    /**
     * populates the deck, skipping over the Nines in accordance with GameManager
     */
    public Deck() {
        this.cards = new ArrayList<>();
        for (String value : GameManager.getValueOrder()) {
            if(!(!GameManager.withNines && value.equals("9"))) {
                for (String color : GameManager.getColorOrder()) {
                    for(int i = 0; i < 2; i++) {
                        this.cards.add(new Card(color + " " + value));
                    }
                }
            }
        }
    }

    /**
     * shuffles the deck randomly
     */
    private void shuffle() {
        Collections.shuffle(getCards());
    }

    /**
     * Splits the deck evenly across 4 Players, randomizing the cards order.
     * Calls itself until it succeeds.
     *
     * @param players the players receiving the cards
     * @return -1 or the index of the player who needs a partner
     */
    public int split(Player[] players) {
        shuffle();  //randomizes the cards' order
        int needsPartner = -1;  //defaults the return value to -1
        for (int i = 0; i < 4; i++) {   //for every player
            for(int j = i * getCards().size() / 4; j < (i + 1) * getCards().size() / 4; j++) {    //for a fourth of the cards
                if(players[i].collect(getCards().get(j))) needsPartner = i; //give card to player - if player requests partner adjust the return value
            }

            String badCard = GameManager.withNines ? "9" : "König"; //pick the worst card
            int badCards = 0;   //initialize the bad card counter to 0
            int foxes = 0;  //initialize the fox counter to 0
            for(Card card : players[i].getCards()) {    //for every card the player has
                if(card.getValue().equals(badCard)) badCards++; //if it is the worst card add one to the bad card counter
                else if(card.getName().equals("Karo Ass")) foxes++; //if it is a fox instead add one to the respective counter
            }
            if(badCards >= 5) return split(players);    //if the player has at least 5 of the worst card redo everything
            else if(foxes == 2 && GameManager.withPigs) {    //if the player has two foxes instead and you play with Pigs
                players[i].setPigs(true);
            }
        }
        return needsPartner;    //returns -1 or index of whoever requested a partner
    }

    /**
     * @return the cards in the deck
     */
    public ArrayList<Card> getCards() {
        return this.cards;
    }

    /**
     * @return how many cards a new Deck holds
     */
    public static int getCardCount() {
        return GameManager.withNines ? 48 : 40;
    }
}
