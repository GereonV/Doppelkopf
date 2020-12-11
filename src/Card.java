/**
 * Class  representing a single playing-card and to compare playing-cards.
 * Contains information about whether the card should be treated as trump.
 *
 * @author Gereon
 * @version 1.0
 * @see GameManager
 */
public class Card {

    /**
     * full name of the card
     */
    private String name;

    /**
     * the color of the card, generated from the name
     */
    private String color;

    /**
     * the value of the card, generated from the name
     */
    private String value;

    /**
     * whether the card is trump, generated from the name
     */
    private boolean trump;

    /**
     * creates a new card based on the passed name
     *
     * @param name the card's full name
     */
    public Card(String name) {
        setName(name);
        setColor(name.substring(0, name.indexOf(" ")));
        setValue(name.substring(name.indexOf(" ") + 1));
        setTrump();
    }

    /**
     * checks for equality between two cards, based on the name
     *
     * @param o the card to compare with
     *
     * @return whether the card is equal to the other
     */

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return name.equals(card.name);
    }

    /**
     * checks which card should win, based on the rules in the GameManager-Class.
     * assumes that calling object has been played first
     *
     * @param card the card to compare itself to
     *
     * @return whether the calling object is worth more
     */
    public boolean isHigherThan(Card card) {
        setTrump(); //refreshes the card's trump value
        if(isTrump()) {
            if(!card.isTrump()) return true;    //if only this is trump
            else {  //if both is trump
                if(GameManager.secondDull && getName().equals("Herz 10")) return !card.getName().equals("Herz 10"); //if 10 of Hearts and the second is higher
                else return GameManager.trumpIndex(this) <= GameManager.trumpIndex(card);   //per default
            }
        } else {    //if this ain't trump
            if(card.isTrump()) return false;    //if only card is trump
            else {  //if both ain't trump
                if(!color.equals(card.color)) return true;
                int thisIndex = GameManager.valueIndex(this);
                int otherIndex = GameManager.valueIndex(card);
                if(thisIndex != otherIndex) return thisIndex <= otherIndex;  //if different values
                else return GameManager.colorIndex(this) <= GameManager.colorIndex(card);   //if the same value
            }
        }
    }

    /**
     * @return the name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * changes the card's name
     *
     * @param name the card's name
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * @return the color of the card
     */
    public String getColor() {
        return color;
    }

    /**
     * changes the card's color
     *
     * @param color the card's color
     */
    private void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the value of the card
     */
    public String getValue() {
        return value;
    }

    /**
     * changes the card's value
     *
     * @param value the card's value
     */
    private void setValue(String value) {
        this.value = value;
    }

    /**
     * @return whether the card is trump
     */
    public boolean isTrump() {
        return trump;
    }

    /**
     * sets the card's trump value based on GameManager's trump-order
     */
    public void setTrump() {
        this.trump = GameManager.isTrump(this);
    }
}
