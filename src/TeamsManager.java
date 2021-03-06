import java.util.ArrayList;
import java.util.Random;

/**
 * Class to manage Players and handle actions with them
 */
public class TeamsManager {

    /**
     * all players that can be controlled by this TeamsManager
     */
    private Player[] players;

    /**
     * creates a TeamsManager object to handle all the players
     *
     * @param players the Player objects to handle
     */
    public TeamsManager(Player[] players) {
        setPlayers(players);
    }

    /**
     * @return all to manager known players
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * changes the players processed by this manager
     *
     * @param players the players the TeamManager controls
     */
    private void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * @return the players in a randomly cycled order
     */
    public Player[] getPlayersInOrder() {
        return getPlayersInOrder(new Random().nextInt(4));
    }

    /**
     * call this to split the teams for a Solo
     *
     * @param player the player being re
     */
    public void playsSolo(Player player) {
        for(Player aPlayer : getPlayers()) {
            aPlayer.setRe(false);
            aPlayer.refreshCards();
        }
        player.setRe(true);
    }

    /**
     * orders the players according to which one goes first, cycling through known players
     *
     * @param goFirst which player should go first
     *
     * @return the cycled through players
     */
    public Player[] getPlayersInOrder(int goFirst) {
        Player[] playersInOrder = new Player[4];
        for(int i = 0; i < 4; i++) {
            playersInOrder[i] = getPlayers()[(goFirst + i) % 4];
        }
        return playersInOrder;
    }

    /**
     * @return all players that are re
     */
    public Player[] getRePlayers() {
        ArrayList<Player> playerList = new ArrayList<>();
        for(Player player : getPlayers()) {
            if(player.isRe()) playerList.add(player);
        }

        Player[] rePlayers = new Player[playerList.size()];
        for(int i = 0; i < rePlayers.length; i++) {
            rePlayers[i] = playerList.get(i);
        }
        return rePlayers;
    }

    /**
     * @return the points of re
     */
    public int getRePoints() {
        int points = 0;
        for(Player player : getRePlayers()) {
            points += player.getPoints();
        }
        return points;
    }

    /**
     * @return all players that aren't re
     */
    public Player[] getContraPlayers() {
        ArrayList<Player> playerList = new ArrayList<>();
        for(Player player : getPlayers()) {
            if(!player.isRe()) playerList.add(player);
        }

        Player[] contraPlayers = new Player[playerList.size()];
        for(int i = 0; i < contraPlayers.length; i++) {
            contraPlayers[i] = playerList.get(i);
        }
        return contraPlayers;
    }

    /**
     * @return the points of contra
     */
    public int getContraPoints() {
        int points = 0;
        for(Player player : getContraPlayers()) {
            points += player.getPoints();
        }
        return points;
    }

    /**
     * prints the final results of the game
     * only should be called when game has ended
     */
    public void printResults() {
        int rePoints = getRePoints();
        int contraPoints = getContraPoints();


        String output;

        output = "Re: " + rePoints + "\n";
        for(Player player : getRePlayers()) output = output.concat(player.getName() + " (" + player.getPoints() + ")\n");
        System.out.println(output);

        output = "Contra: " + contraPoints + "\n";
        for(Player player : getContraPlayers()) output = output.concat(player.getName() + " (" + player.getPoints() + ")\n");
        System.out.println(output);


        boolean reWon = rePoints > contraPoints;

        output = reWon ? "Re" : "Kontra";
        output = output.concat(" gewinnt ");

        int points = 1;
        if(!reWon) points++;
        int winnerPoints = reWon ? rePoints : contraPoints;
        if(winnerPoints >= 150) {
            points++;
            if(winnerPoints >= 180) {
                points++;
                if(winnerPoints >= 210) {
                    points++;
                    if(winnerPoints == 240) points++;
                }
            }
        }
        for(Player player : reWon ? getRePlayers() : getContraPlayers()) points += player.getExtras();
        output = output.concat(points + " Punkte.");
        System.out.println(output);
    }
}
