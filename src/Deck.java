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
     * @param teamsManager the managing Manager
     * @return -1 or the index of the player who needs a partner
     */
    public int split(TeamsManager teamsManager) {
        Player[] players = teamsManager.getPlayers();   //gets all players
        shuffle();  //reshuffles the cards
        int needsPartner = -1;  //initialises return value
        shuffle();  //randomizes the cards' order
        for (int i = 0; i < 4; i++) {   //for every player
            for(int j = i * getCards().size() / 4; j < (i + 1) * getCards().size() / 4; j++) {    //for a fourth of the cards
                players[i].collect(getCards().get(j)); //give card to player
            }

            String badCard = GameManager.withNines ? "9" : "König"; //pick the worst card
            int badCards = 0;   //initialize the bad card counter to 0
            int foxes = 0;  //initialize the fox counter to 0
            for(Card card : players[i].getCards()) {    //for every card the player has
                if(card.getValue().equals(badCard)) badCards++; //if it is the worst card add one to the bad card counter
                else if(card.getName().equals("Karo Ass")) foxes++; //if it is a fox instead add one to the respective counter
            }
            if(badCards >= 5 && GameManager.illness) return split(teamsManager);    //if the player has at least 5 of the worst card redo everything
            else if(foxes == 2 && GameManager.withPigs) players[i].setPigs(true);   //if the player has two foxes instead and you play with Pigs
        }
        for(int i = 0; i < players.length; i++) { //ask all players for extras
            boolean[] extra = players[i].extra(teamsManager);   //asks for extras
            if(extra[1]) {  //if partner is requested
                needsPartner = i;
            }
            if(extra[0]) {  //breaks if skip requested
                break;
            }
        }
        return needsPartner;
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
