package blackjack;

import java.util.Scanner;

public class BlackJack {
    private static Game game = new Game();
    private static GUI gui;

    public static void main(String[] args) {
        gui = new GUI();

        System.out.println("Welcome to BlackJack!!!");
        game.generateCards();
        game.setInformation();

        gui.runGUI(game.getCards(), game.getPlayers()[0].getHand(), game.getPlayers()[1].getHand(),
                game.getPlayers()[2].getHand(), game.getPlayers()[3].getHand());

        // gui.updateDealerHand(game.getPlayers()[3].getCard(1), game.getCards());

        Scanner scanner = new Scanner(System.in);
        String input;

        for (int i = 0; i < 3; i++) {
            System.out.println("\nPlayer number " + Integer.toString(i + 1) + " (" + game.getPlayers()[i].getName()
                    + ")'s turn. Please enter 'hit' or 'stand'");
            while (true) {
                input = scanner.nextLine();
                if (input.equals("hit")) {
                    Card card = game.drawCard();
                    game.getPlayers()[i].addCard(card);
                    game.getPlayers()[i].printHand();
                    game.getPlayers()[i].printScore();
                    game.updateMaxScore();

                    gui.updatePlayerHand(card, i);

                    if (game.getPlayers()[i].isBust()) {
                        System.out.println("Bust!");
                        break;
                    } else if (game.getPlayers()[i].isBlackjack()) {
                        System.out.println("Blackjack!");
                        break;
                    } else {
                        System.out.println("hit or stand?");
                    }
                } else if (input.equals("stand")) {
                    System.out.println("Stand!");
                    break;
                } else {
                    System.out.println("Please enter 'hit' or 'stand'");
                }
            }
        }

        // player 3 is the dealer
        while (!game.getPlayers()[3].isBust() && !game.getPlayers()[3].isBlackjack()
                && game.getPlayers()[3].getScore() <= game.getMaxScore()) {

            Card card = game.drawCard();
            game.getPlayers()[3].addCard(card);
            gui.updateDealerHand(card, game.getCards());
            game.getPlayers()[3].printHand();
            game.getPlayers()[3].printScore();
            System.out.println("----------");
        }

        endGame();
    }

    private static void endGame() {
        // Decides whether the game is a tie or not.
        if (game.getPlayers()[0].getScore() > game.getPlayers()[1].getScore()
                && game.getPlayers()[0].getScore() > game.getPlayers()[2].getScore()
                && game.getPlayers()[0].getScore() > game.getPlayers()[3].getScore()) {
            System.out.println("Player 1 wins!");
        } else if (game.getPlayers()[1].getScore() > game.getPlayers()[0].getScore()
                && game.getPlayers()[1].getScore() > game.getPlayers()[2].getScore()
                && game.getPlayers()[1].getScore() > game.getPlayers()[3].getScore()) {
            System.out.println("Player 2 wins!");
        } else if (game.getPlayers()[2].getScore() > game.getPlayers()[0].getScore()
                && game.getPlayers()[2].getScore() > game.getPlayers()[1].getScore()
                && game.getPlayers()[2].getScore() > game.getPlayers()[3].getScore()) {
            System.out.println("Player 3 wins!");
        } else if (game.getPlayers()[3].getScore() > game.getPlayers()[0].getScore()
                && game.getPlayers()[3].getScore() > game.getPlayers()[1].getScore()
                && game.getPlayers()[3].getScore() > game.getPlayers()[2].getScore()) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("PUSH!");
        }
    }
}
