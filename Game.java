package blackjack;

import java.util.Random;
import java.util.Scanner;
import static java.lang.Math.max;

public class Game {
    public Player[] players = new Player[4];
    public Card[] cards = new Card[52];
    public int maxScore = 0;

    public void generateCards() {
        for (int suit = 0; suit < 4; suit++) {
            for (int rank = 0; rank < 10; rank++) {
                cards[suit * 13 + rank] = new Card(rank + 1, suit, rank);
            }
            cards[suit * 13 + 10] = new Card(10, suit, 10);
            cards[suit * 13 + 11] = new Card(10, suit, 11);
            cards[suit * 13 + 12] = new Card(10, suit, 12);
        }
    }

    public Card drawCard() {
        Random random = new Random();
        int index = random.nextInt(52);

        while (cards[index] == null) {
            index = random.nextInt(52);
        }

        Card card = cards[index];
        cards[index] = null;

        return card;
    }

    public void setInformation() {
        Scanner scanner = new Scanner(System.in);

        // Dealer
        players[0] = new Player("Dealer");

        players[0].addCard(drawCard());

        System.out.println("Dealer has this card:");
        players[0].printHand();
        players[0].printScore();

        players[0].addCard(drawCard());

        for (int i = 1; i < 4; i++) {
            System.out.println("\nEnter the name of player " + i + ": ");
            players[i] = new Player(scanner.nextLine());

            players[i].addCard(drawCard());
            players[i].addCard(drawCard());

            System.out.println("You have these cards:");
            players[i].printHand();
            players[i].printScore();
        }
    }

    public void updateMaxScore() {
        for (int i = 1; i < 4; i++) {
            if (!players[i].isBust()) {
                maxScore = max(maxScore, players[i].getScore());
            }
        }
    }
}
