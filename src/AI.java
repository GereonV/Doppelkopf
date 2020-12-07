import java.util.ArrayList;

public class AI extends Player{

    /**
     * creates a new ai-player with passed name, defaulting all other attributes
     *
     * @param name the ai's name
     */
    public AI(String name) {
        super(name);
    }

    /**
     * should only be called if player has a wedding
     *
     * @return whether the ai plays weddings silently
     */
    @Override
    public boolean playsAlone() {
        return GameManager.playsAlone;
    }

    @Override
    public void nextTurn(ArrayList<Card> stack) {
        for (Card card : getCards()) {
            if(takeTurn(card, stack)) break;
        }
    }
}
