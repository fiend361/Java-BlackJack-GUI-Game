package blackjack;

import java.util.Random;
import java.util.Scanner;

public class Game {
    public Player[] players = new Player[4];
    public Card[] cards = new Card[52];
    public int maxScore;

    public void generateCards() {
        for (int suit = 0; suit < 4; suit++) {
            for (int rank = 0; rank < 10; rank++) {
                cards[suit * 13 + rank] = new Card(rank + 1, suit, rank);
            }
            cards[suit * 13 + 10] = new Card(11, suit, 10);
            cards[suit * 13 + 11] = new Card(12, suit, 11);
            cards[suit * 13 + 12] = new Card(13, suit, 12);
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
        for (int i = 0; i < players.length; i++) {
            System.out.println("Enter the name of player " + (i + 1) + ": ");
            players[i] = new Player(scanner.nextLine());

            players[i].addCard(drawCard());
            players[i].addCard(drawCard());
        }
        // scanner.close();
    }

    public void updateMaxScore() {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getScore() > maxScore) {
                maxScore = players[i].getScore();
            }
        }
    }
}
