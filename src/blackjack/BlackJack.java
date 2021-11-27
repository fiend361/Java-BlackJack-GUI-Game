package blackjack;

import java.util.Scanner;

public class BlackJack {
    public static Game game = new Game();
    public static GUI gui;

    public static void main(String[] args) {
        gui = new GUI();

        System.out.println("Welcome to BlackJack!!!");
        game.generateCards();
        // printAllCards();
        game.setInformation();
        gui.updateDealerHand(game.players[0].getCard(1), game.cards);

        gui.runGUI(game.cards, game.players[0].getHand(), game.players[1].getHand(), game.players[2].getHand(),
                game.players[3].getHand());

        Scanner scanner = new Scanner(System.in);
        String input;

        for (int i = 1; i < 4; i++) {
            System.out.println("\nPlayer number " + Integer.toString(i) + "'s turn. Please enter 'hit' or 'stand'");
            while (true) {
                input = scanner.nextLine();
                if (input.equals("hit")) {
                    Card card = game.drawCard();
                    game.players[i].addCard(card);
                    game.players[i].printHand();
                    game.players[i].printScore();
                    gui.updatePlayerHand(card, i);
                    if (game.players[i].isBust()) {
                        System.out.println("Bust!");
                        break;
                    } else if (game.players[i].isBlackjack()) {
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

        game.updateMaxScore();

        // player 3 is the dealer
        while (!game.players[0].isBust() && !game.players[0].isBlackjack()
                && game.maxScore >= game.players[0].getScore()) {

            Card card = game.drawCard();
            game.players[0].addCard(card);
            gui.updateDealerHand(card, game.cards);
            game.players[0].printHand();
            game.players[0].printScore();
            System.out.println("----------");
        }

        endGame();
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
                    if (i == 0) {
                        System.out.println("Dealer wins!");
                    } else {
                        System.out.println("Player " + Integer.toString(i) + " wins!");
                    }
                }
            }
        } else if (numBlackJack > 1) {
            System.out.println("PUSH!");
        } else {
            if (game.players[0].getScore() > game.players[1].getScore()
                    && game.players[0].getScore() > game.players[2].getScore()
                    && game.players[0].getScore() > game.players[3].getScore()) {
                System.out.println("Dealer wins!");
            } else if (game.players[1].getScore() > game.players[0].getScore()
                    && game.players[1].getScore() > game.players[2].getScore()
                    && game.players[1].getScore() > game.players[3].getScore()) {
                System.out.println("Player 1 wins!");
            } else if (game.players[2].getScore() > game.players[0].getScore()
                    && game.players[2].getScore() > game.players[1].getScore()
                    && game.players[2].getScore() > game.players[3].getScore()) {
                System.out.println("Player 2 wins!");
            } else if (game.players[3].getScore() > game.players[0].getScore()
                    && game.players[3].getScore() > game.players[1].getScore()
                    && game.players[3].getScore() > game.players[2].getScore()) {
                System.out.println("Player 3 wins!");
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
