import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    public static void main(String[] args) {

        TeamsManager teamsManager = new TeamsManager(new Player[] {new Player("Gereon"), new AI("AI 1"), new AI("AI 2"), new AI("AI 3")});

        Deck deck = new Deck();
        int needsPartner = deck.split(teamsManager);

        Player[] players = teamsManager.getPlayersInOrder();

        for(int i = 0; i < Deck.getCardCount() / 4; i++) {

            //prints player order
            String output = "Aktuelle Spieler-Reihenfolge: \n";
            for (Player player : players) {
                output = output.concat(player.getName() + ", ");
            }
            System.out.println(output.substring(0, output.length() - 2) + "\n");

            //play stack
            ArrayList<Card> stack = new ArrayList<>();
            for (Player player : players) player.nextTurn(stack);
            int winnerIndex = CardManager.highestCard(stack);
            Player winner = players[winnerIndex];
            winner.addPoints(CardManager.valueOf(stack));

            //prints final stack
            System.out.println("\n" + winner.getName() + " hat gewonnen.");
            output = "";
            for (Card card : stack) {
                output = output.concat(card.getName() + ", ");
            }
            System.out.println(output.substring(0, output.length() - 2) + "\n");

            //gets partner
            if(i < 3 && needsPartner != -1 && winner != teamsManager.getPlayers()[needsPartner]) {
                System.out.println(winner.getName() + " spielt jetzt mit " + teamsManager.getPlayers()[needsPartner].getName() + "\n");
                winner.setRe(true);
                needsPartner = -1;
            }

            //looks for foxes
            int firstIndex = -1;
            int secondIndex = -1;
            for(int j = 0; j < 4; j++) {
                Card fox = new Card("Karo Ass");
                if(!GameManager.isTrump(fox) && stack.get(j).equals(fox)) {
                    if(firstIndex == -1) firstIndex = i;
                    else secondIndex = i;
                }
            }
            if(firstIndex != -1 && players[firstIndex].isRe() != winner.isRe()) winner.addExtra();
            if(secondIndex != -1 && players[secondIndex].isRe() != winner.isRe()) winner.addExtra();

            //looks for charly in last stack
            if(i == Deck.getCardCount() / 4) {
                firstIndex = -1;
                secondIndex = -1;
                for(int j = 0; j < 4; j++) {
                    if(stack.get(j).equals(new Card("Kreuz Bube"))) {
                        if(firstIndex == -1) firstIndex = i;
                        else secondIndex = i;
                    }
                }
                if(firstIndex != -1 && firstIndex == winnerIndex) winner.addExtra();
                else if(secondIndex != -1 && secondIndex != winnerIndex) winner.addExtra();
            }


            players = teamsManager.getPlayersInOrder(Arrays.asList(teamsManager.getPlayers()).indexOf(players[winnerIndex]));

        }

        //print final results
        String output = "Re: \n";
        for(Player player : teamsManager.getRePlayers()) {
            output = output.concat(player.getName() + " (" + player.getPoints() + ")\n");
        }
        System.out.println(output);

        output = "Contra: \n";
        for (Player player : teamsManager.getContraPlayers()) {
            output = output.concat(player.getName() + " (" + player.getPoints() + ")\n");
        }
        System.out.println(output);
    }
}
