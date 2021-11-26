package blackjack;

import java.util.Scanner;
import static java.lang.Math.max;
import blackjack.Game;

public class BlackJack {
    public static Game game = new Game();

    public static void main(String[] args) {
        System.out.println("Welcome to BlackJack!!!");
        game.generateCards();
        // printAllCards();
        game.setInformation();

        Scanner scanner = new Scanner(System.in);
        String input;

        for (int i = 0; i < 3; i++) {
            System.out.println("Player number " + Integer.toString(i + 1) + "'s turn. Please enter 'hit' or 'stand'");
            while (true) {
                input = scanner.nextLine();
                if (input.equals("hit")) {
                    game.players[i].addCard(game.drawCard());
                    game.players[i].printHand();
                    game.players[i].printScore();
                    if (game.players[i].isBust()) {
                        System.out.println("Bust!");
                        break;
                    } else if (game.players[i].isBlackjack()) {
                        System.out.println("Blackjack!");
                        break;
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
        if (!game.players[3].isBust()) {
            while (!game.players[3].isBust() && !game.players[3].isBlackjack()
                    && max(game.players[0].getScore(),
                            max(game.players[1].getScore(), game.players[2].getScore())) <= game.players[3]
                                    .getScore()) {

                game.players[3].addCard(game.drawCard());
            }
        }

        endGame();
        // scanner.close();
    }

    private static void endGame() {
        // Decides whether the game is a tie or not.
        int numBlackJack = 0;
        for (int i = 0; i < 4; i++) {
            if (game.players[i].isBlackjack()) {
                numBlackJack++;
            }
        }
        if (numBlackJack == 1) {
            for (int i = 0; i < 4; i++) {
                if (game.players[i].isBlackjack()) {
                    System.out.println("Player " + Integer.toString(i + 1) + " wins!");
                }
            }
        } else if (numBlackJack > 1) {
            System.out.println("PUSH!");
        } else {
            if (game.players[0].getScore() > game.players[1].getScore()
                    && game.players[0].getScore() > game.players[2].getScore()
                    && game.players[0].getScore() > game.players[3].getScore()) {
                System.out.println("Player 1 wins!");
            } else if (game.players[1].getScore() > game.players[0].getScore()
                    && game.players[1].getScore() > game.players[2].getScore()
                    && game.players[1].getScore() > game.players[3].getScore()) {
                System.out.println("Player 2 wins!");
            } else if (game.players[2].getScore() > game.players[0].getScore()
                    && game.players[2].getScore() > game.players[1].getScore()
                    && game.players[2].getScore() > game.players[3].getScore()) {
                System.out.println("Player 3 wins!");
            } else if (game.players[3].getScore() > game.players[0].getScore()
                    && game.players[3].getScore() > game.players[1].getScore()
                    && game.players[3].getScore() > game.players[2].getScore()) {
                System.out.println("Player 4 wins!");
            } else {
                System.out.println("PUSH!");
            }
        }
    }

    private static void printAllCards() {
        // print out the cards
        for (int i = 0; i < 52; i++) {
            System.out.println(game.cards[i].getRank() + " of " + game.cards[i].getSuit()
                    + "s" + game.cards[i].getValue());
        }
    }
}
