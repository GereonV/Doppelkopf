import java.util.ArrayList;

/**
 * Class mimicing a player using basic AI to pick th enext move
 *
 * @author Gereon
 * @version 1.0
 * @see Player
 */
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
     * @param teamsManager the manager to split the teams on a solo occasion
     * @return true if the ai plays an open wedding
     */
    @Override
    public boolean[] extra(TeamsManager teamsManager) {
        return new boolean[] {isWedding() && !GameManager.playsAlone, isWedding() && !GameManager.playsAlone};
    }
    
    /**
     * plays the first found legal move to the stack
     * 
     * @param stack the current stack
     */
    @Override
    public void nextTurn(ArrayList<Card> stack) {
        for (Card card : getCards()) {
            if(takeTurn(card, stack)) break;
        }
    }
}
