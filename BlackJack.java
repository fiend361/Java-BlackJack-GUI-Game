package blackjack;

import java.util.Scanner;

import blackjack.Game;

public class BlackJack {
    public static Game game = new Game();

    public static void main(String[] args) {
        game.generateCards();
        game.setInformation();

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("Player number " + toString(i + 1) + "'s turn. Please enter 'hit' or 'stand'");
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
                    game.stand();
                    break;
                } else {
                    System.out.println("Please enter 'hit' or 'stand'");
                }
            }
        }

        // player 3 is the dealer
        if (!player[3].isBust()) {
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
                    game.stand();
                    break;
                } else {
                    System.out.println("Please enter 'hit' or 'stand'");
                }
            }
        }
    }
}
