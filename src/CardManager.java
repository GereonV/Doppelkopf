import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class containing methods for card calculations.
 * Includes only static methods.
 *
 * @author Gereon
 * @version 1.0
 * @see GameManager
 */
public class CardManager {

    /**
     * private constructor to deny instantiations of CardManager
     */
    private CardManager() {}

    /**
     * values of GameManager's value order
     */
    private static final int[] values = {11, 10, 4, 3, 2, 0};

    /**
     * picks the highest of 4 cards
     *
     * @param stack cards to be checked
     * @return index of the highest card
     */
    public static int highestCard(ArrayList<Card> stack) {
        int currentHighest = 0;
        for(int i = 1; i < 4; i++) {
            if(!stack.get(currentHighest).isHigherThan(stack.get(i))) currentHighest = i;
        }
        return currentHighest;
    }

    /**
     * finds every card's value and adds them up
     *
     * @param stack the stack of cards to get the points of
     * @return the points the stack is worth
     */
    public static int valueOf(ArrayList<Card> stack) {
        int value = 0;
        for (Card card : stack) {
            value += values[Arrays.asList(GameManager.getValueOrder()).indexOf(card.getValue())];
        }
        return value;
    }
}
