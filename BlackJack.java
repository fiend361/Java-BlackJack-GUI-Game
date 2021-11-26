package blackjack;

import java.util.Scanner;

import blackjack.Game;

public class BlackJack {
    public static Game game = new Game();

    public static void main(String[] args) {
        System.out.println("Welcome to BlackJack!");
        game.generateCards();
        game.setInformation();

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("Player number " + Integer.toString(i + 1) + "'s turn. Please enter 'hit' or 'stand'");
            while (true) {
                if (scanner.next().equals("hit")) {
                    game.players[i].addCard(game.drawCard());
                    if (game.players[i].isBust()) {
                        System.out.println("Bust!");
                        break;
                    } else if (game.players[i].isBlackjack()) {
                        System.out.println("Blackjack!");
                        break;
                    }
                } else if (scanner.next().equals("stand")) {
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
    }

    private static void endGame() {
        // Decides whether the game is a tie or not.
        int numBlackJack = 0;
        for (int i = 0; i < 4; i++) {
            if (players[i].isBlackjack()) {
                numBlackJack++;
            }
        }
        if (numBlackJack == 1) {
            for (int i = 0; i < 4; i++) {
                if (players[i].isBlackjack()) {
                    System.out.println("Player " + toString(i + 1) + " wins!");
                }
            }
        } else if (numBlackJack > 1) {
            System.out.println("PUSH!");
        } else {
            if (players[0].getScore() > players[1].getScore()
                    && players[0].getScore() > players[2].getScore()
                    && players[0].getScore() > players[3].getScore()) {
                System.out.println("Player 1 wins!");
            } else if (players[1].getScore() > players[0].getScore()
                    && players[1].getScore() > players[2].getScore()
                    && players[1].getScore() > players[3].getScore()) {
                System.out.println("Player 2 wins!");
            } else if (players[2].getScore() > players[0].getScore()
                    && players[2].getScore() > players[1].getScore()
                    && players[2].getScore() > players[3].getScore()) {
                System.out.println("Player 3 wins!");
            } else if (players[3].getScore() > players[0].getScore()
                    && players[3].getScore() > players[1].getScore()
                    && players[3].getScore() > players[2].getScore()) {
                System.out.println("Player 4 wins!");
            } else {
                System.out.println("PUSH!");
            }
        }
    }

    // private static void printAllCards() {
    // // print out the cards
    // for (int i = 0; i < 52; i++) {
    // System.out.println(game.cards[i].getRank() + " of " + game.cards[i].getSuit()
    // + "s");
    // }
    // }
}
