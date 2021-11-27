package blackjack;

public class Card {

    private int value;
    private int suit;
    private int rank;

    public Card(int value, int suit, int rank) {
        this.value = value;
        this.suit = suit;
        this.rank = rank;
    }

    public Card(Card c) {
        this.value = c.getValue();
        this.suit = c.getSuit();
        this.rank = c.getRank();
    }

    public int getValue() {
        return value;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

}
