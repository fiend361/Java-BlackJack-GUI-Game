package blackjack;

public class Player {

    private String name;
    private int score = 0;
    private Card[] hand = new Card[11];
    private int handSize = 0;
    private boolean isBust = false;
    private boolean isBlackjack = false;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.numOfCards = 0;
        this.hand = new Card[52];
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Card[] getHand() {
        return hand;
    }

    public boolean isBust() {
        return isBust;
    }

    public boolean isBlackjack() {
        return isBlackjack;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    public void setBust(boolean isBust) {
        this.isBust = isBust;
    }

    public void setBlackjack(boolean isBlackjack) {
        this.isBlackjack = isBlackjack;
    }

    public void addCard(Card card) {
        hand[handSize] = card;
        handSize++;
        score += card.getValue();
        if (score > 21) {
            isBust = true;
        }
        if (score == 21) {
            isBlackjack = true;
        }
    }

}
