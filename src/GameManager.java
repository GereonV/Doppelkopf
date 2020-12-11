import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class containing the rules for the Game and methods used for card values.
 * Includes only static attributes and methods.
 *
 * @author Gereon
 * @version 1.0
 */
public class GameManager {

    /**
     * Enum for all edge-cases
     *
     * @author Gereon
     * @version 1.0
     */
    public enum Extra {

        /**
         * Queen Solo
         */
        QUEEN,

        /**
         * Jack Solo
         */
        JACK,

        /**
         * Solo without Trumps
         */
        ACE,

        /**
         * Clubs Solo
         */
        CLUBS,

        /**
         * Spades Solo
         */
        SPADES,

        /**
         * Hearts Solo, Hearts 10 remains top trump
         */
        HEARTS,

        /**
         * Diamonds Solo
         */
        DIAMONDS,

        /**
         * Pigs becomes the highest trump
         */
        PIGS;

        /**
         * get the trump order based on the extra
         *
         * @param trumpOrder the current trump order
         * @return the changed trump order
         */
        public String[] getTrumpOrder(String[] trumpOrder) {
            switch(this) {
                case QUEEN: return new String[] {"Kreuz Dame", "Pik Dame", "Herz Dame", "Karo Dame"};
                case JACK: return new String[] {"Kreuz Bube", "Pik Bube", "Herz Bube", "Karo Bube"};
                case ACE: return new String[] {};
                case CLUBS: return new String[] {"Herz 10", "Kreuz Dame", "Pik Dame", "Herz Dame", "Karo Dame", "Kreuz Bube", "Pik Bube", "Herz Bube", "Karo Bube", "Kreuz Ass", "Kreuz 10", "Kreuz König", "Kreuz 9"};
                case SPADES: return new String[] {"Herz 10", "Kreuz Dame", "Pik Dame", "Herz Dame", "Karo Dame", "Kreuz Bube", "Pik Bube", "Herz Bube", "Karo Bube", "Pik Ass", "Pik 10", "Pik König", "Pik 9"};
                case HEARTS: return new String[] {"Herz 10", "Kreuz Dame", "Pik Dame", "Herz Dame", "Karo Dame", "Kreuz Bube", "Pik Bube", "Herz Bube", "Karo Bube", "Herz Ass", "Herz König", "Herz 9"};
                case DIAMONDS: return trumpOrder;
                case PIGS: {
                    ArrayList<String> trumpList = new ArrayList<>();
                    for(Object trump : trumpOrder) trumpList.add((String) trump);
                    trumpList.remove("Karo Ass");
                    trumpList.add(0, "Karo Ass");
                    return trumpList.toArray(new String[0]);
                }
                default: return null;
            }
        }
    }

    /**
     * private constructor to deny instantiations of GameManager
     */
    private GameManager() {}

    /**
     * whether to play with nines
     */
    public static boolean withNines = true;

    /**
     * whether two Ace of Diamonds should be treated as the highest trump in the Game
     */
    public static boolean withPigs = true;

    /**
     * whether the second 10 of Hearts should win over the first
     */
    public static boolean secondDull = true;

    /**
     * whether 5x 9/King leads to new shuffle
     */
    public static boolean illness = true;

    /**
     * whether the ai want's to play alone
     */
    public static boolean playsAlone = false;

    /**
     * all trumps in descending order
     */
    private static String[] trumpOrder = {"Herz 10", "Kreuz Dame", "Pik Dame", "Herz Dame", "Karo Dame", "Kreuz Bube", "Pik Bube", "Herz Bube", "Karo Bube", "Karo Ass", "Karo 10", "Karo König", "Karo 9"};

    /**
     * all values in descending order
     */
    private static final String[] valueOrder = {"Ass", "10", "König", "Dame", "Bube", "9"};

    /**
     * all colors in descending order
     */
    private static final String[] colorOrder = {"Kreuz", "Pik", "Herz", "Karo"};

    /**
     * @return the value order
     */
    public static String[] getValueOrder() {
        return valueOrder;
    }

    /**
     * @return the color order
     */
    public static String[] getColorOrder() {
        return colorOrder;
    }

    public static void setTrumpOrder(Extra extra) {
        trumpOrder = extra.getTrumpOrder(trumpOrder);
    }

    /**
     * checks whether a card should be trump, based on it's name appearing in the trump-order
     *
     * @param card the card to be checked
     *
     * @return whether the card is trump
     */
    public static boolean isTrump(Card card) {
        return Arrays.asList(GameManager.trumpOrder).contains(card.getName());
    }

    /**
     * looks into the trump-order and finds the card's index
     *
     * @param card the card to check for
     *
     * @return the index within the trump-order
     */
    public static int trumpIndex(Card card) {
        return Arrays.asList(trumpOrder).indexOf(card.getName());
    }

    /**
     * looks into the face-order and finds the card's index
     *
     * @param card the card to check for
     *
     * @return the index within the face-order
     */
    public static int valueIndex(Card card) {
        return Arrays.asList(valueOrder).indexOf(card.getValue());
    }

    /**
     * looks into the color-order and finds the card's index
     *
     * @param card the card to check for
     *
     * @return the index within the color-order
     */
    public static int colorIndex(Card card) {
        return Arrays.asList(colorOrder).indexOf(card.getColor());
    }
}
