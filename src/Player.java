import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing a single player, including his name, points, extra points, team and his collection of cards.
 * Values will change when interacting with cards.
 *
 * @author Gereon
 * @version 1.0
 * @see Card
 */
public class Player {

    /**
     * name of the player
     */
    private String name;

    /**
     * points the player already won
     */
    private int points;

    /**
     * extra points the player received
     */
    private int extras;

    /**
     * the player's hand cards
     */
    private ArrayList<Card> cards;

    /**
     * what team the player is on
     */
    private boolean re;

    /**
     * whether the player has a wedding
     */
    private boolean wedding;

    /**
     * whether the player has pigs
     */
    private boolean pigs;

    /**
     * creates a new player with passed name, defaulting all other attributes
     *
     * @param name the player's name
     */
    public Player(String name) {
        setName(name);
        setPoints();
        setExtras();
        setCards();
        setRe(false);
        setWedding(false);
        setPigs(false);
    }

    /**
     * adds a card to the ones already possessed
     *
     * @param card the card to add
     */
    public void collect(Card card) {
        getCards().add(card);
        if(card.getName().equals("Kreuz Dame")) {
            if(isRe()) setWedding(true);
            else setRe(true);
        }
    }
    
    public void refreshCards() {
        for (Card card : getCards()) {
            card.setTrump();
        }
    }

    /**
     * asks the player for extras
     *
     * @param teamsManager the manager to split the teams on a solo occasion
     * @return whether the player plays a public wedding
     */
    public boolean[] extra(TeamsManager teamsManager) {
        String output = "";
        for(Card card : getCards()) {
            output = output.concat(card.getName() + ", ");
        }
        System.out.println(output.substring(0, output.length() - 2) + "\n");


        boolean skip = true;
        boolean needsPartner = false;

        Scanner scanner = new Scanner(System.in);
        boolean legalInput;
        do {
            legalInput = true;

            switch(scanner.nextLine()) {
                case "Damen-Solo":
                case "Damen Solo": {
                    GameManager.setTrumpOrder(GameManager.Extra.QUEEN);
                    teamsManager.playsSolo(this);
                    break;
                }
                case "Buben-Solo":
                case "Buben Solo": {
                    GameManager.setTrumpOrder(GameManager.Extra.JACK);
                    teamsManager.playsSolo(this);
                    break;
                }
                case "Ass-Solo":
                case "Ass Solo":
                case "fleischlos":
                case "fehllos": {
                    GameManager.setTrumpOrder(GameManager.Extra.ACE);
                    teamsManager.playsSolo(this);
                    break;
                }
                case "Kreuz-Solo":
                case "Kreuz Solo": {
                    GameManager.setTrumpOrder(GameManager.Extra.CLUBS);
                    teamsManager.playsSolo(this);
                    break;
                }
                case "Pik-Solo":
                case "Pik Solo": {
                    GameManager.setTrumpOrder(GameManager.Extra.SPADES);
                    teamsManager.playsSolo(this);
                    break;
                }
                case "Herz-Solo":
                case "Herz Solo": {
                    GameManager.setTrumpOrder(GameManager.Extra.HEARTS);
                    teamsManager.playsSolo(this);
                    break;
                }
                case "Karo-Solo":
                case "Karo Solo": {
                    GameManager.setTrumpOrder(GameManager.Extra.DIAMONDS);
                    teamsManager.playsSolo(this);
                    break;
                }
                case "Hochzeit": {
                    if(isWedding()) {
                        needsPartner = true;
                        setWedding(false);
                    }
                    else {
                        legalInput = false;
                        break;
                    }
                }
                case "gesund": {
                    if(isWedding()) {
                        GameManager.setTrumpOrder(GameManager.Extra.DIAMONDS);
                        teamsManager.playsSolo(this);
                        skip = false;
                    }
                    if(isPigs()) {
                        GameManager.setTrumpOrder(GameManager.Extra.PIGS);
                        skip = false;
                    }
                    break;
                }

                default: legalInput = false; break;
            }
        } while(!legalInput);
        return new boolean[] {skip, needsPartner};
    }

    /**
     * checks whether a card can be played, taking the first played card into account
     *
     * @param card the card to be played
     * @param beginningCard the first card played into the current stack
     * @return whether the move is allowed to be played
     */
    private boolean turnLegal(Card card, Card beginningCard) {
        if(!getCards().contains(card)) return false;    //if card isn't available to player
        else if(beginningCard == null || (card.isTrump() && beginningCard.isTrump())) return true;    //if there's no limitations or both cards are trump
        else {  //if one or no cards are trump
            if(beginningCard.isTrump()) {    //if only the first card is trump
                for(Card a_card : getCards()) {
                    if(a_card.isTrump()) return false;  //if the player has a trump card
                }
                return true;    //if player has no trump card
            } else if(card.isTrump() || !card.getColor().equals(beginningCard.getColor())) {    //if only card is trump or the colors don't match
                for(Card a_card : getCards()) {
                    if(a_card.getColor().equals(beginningCard.getColor()) && !a_card.isTrump()) return false;   //if the player has a non-trump card of correct color
                }
                return true;    //if the player can't play the correct color
            } else return true; //if the cards' colors match
        }
    }

    /**
     * tries to play a card onto the current stack, blocking illegal moves
     *
     * @param card the card to be played
     * @param stack the current stack
     * @return whether making the move succeeded
     */
    public boolean takeTurn(Card card, ArrayList<Card> stack) {
        if(stack != null) { //if the stack exists
            boolean legal = turnLegal(card, stack.size() > 0 ? stack.get(0) : null);   //checks whether the move is legal based on the first card from the stack or a null object if the stack is empty
            if(legal) {
                stack.add(card);    //adds the card to the stack
                getCards().remove(card);    //removes the card from hand
            }
            return legal;
        } else return false;    //if the stack is null
    }

    /**
     * this method plays a card from the players' hand onto the current stack
     *
     * @param stack the stack the card is being played on
     */
    public void nextTurn(ArrayList<Card> stack) {
        String output = "Aktueller Stich: \n";
        for (Card card : stack) {
            output = output.concat(card.getName() + ", ");
        }
        System.out.println(output.substring(0, output.length() - 2) + "\n");

        output = "";
        for (Card card : getCards()) {
            output = output.concat(card.getName() + ", ");
        }
        System.out.println(output.substring(0, output.length() - 2) + "\n");

        Scanner scanner = new Scanner(System.in);
        while(!takeTurn(new Card(scanner.nextLine()), stack));
    }

    /**
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * changes the players name
     *
     * @param name the player's name
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * @return the player's points
     */
    public int getPoints() {
        return points;
    }

    /**
     * sets the player's points to 0
     */
    private void setPoints() {
        this.points = 0;
    }

    /**
     * adds the specified amount of points to the player, adding extra for 40+ points
     *
     * @param points the amount of points the player receives
     */
    public void addPoints(int points) {
        this.points += points;
        if(points >= 40) {
            addExtra();
        }
    }

    /**
     * @return the player's extra points
     */
    public int getExtras() {
        return extras;
    }

    /**
     * sets the player's extra points to 0
     */
    private void setExtras() {
        this.extras = 0;
    }

    /**
     * adds one to the player's extra points
     */
    public void addExtra() {
        this.extras++;
    }

    /**
     * @return the player's hand collection of cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * sets the player's cards to an empty List
     */
    public void setCards() {
        this.cards = new ArrayList<>();
    }

    /**
     * @return whether the player is in the "Re"-Team
     */
    public boolean isRe() {
        return re;
    }

    /**
     * sets the player's re value
     *
     * @param re the player's re value
     */
    public void setRe(boolean re) {
        this.re = re;
    }

    /**
     * @return whether the player has a wedding
     */
    public boolean isWedding() {
        return wedding;
    }

    /**
     * sets whether the player has a wedding
     *
     * @param wedding whether the player has a wedding
     */
    public void setWedding(boolean wedding) {
        this.wedding = wedding;
    }

    /**
     * @return whether the player has pigs
     */
    public boolean isPigs() {
        return pigs;
    }

    /**
     * sets the player'S pigs value
     *
     * @param pigs the player's pigs value
     */
    public void setPigs(boolean pigs) {
        this.pigs = pigs;
    }
}

