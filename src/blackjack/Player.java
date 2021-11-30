package blackjack;

public class Player {

    private String name;
    private int score = 0;
    private Card[] hand = new Card[11];
    private int handSize = 0;
    private boolean isBust = false;
    private boolean isBlackjack = false;

    public Player() {
        this.name = "";
        this.score = 0;
        this.handSize = 0;
    }

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.handSize = 0;
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

    public Card getCard(int index) {
        return hand[index];
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
            score = 0;
        }
        if (score == 21) {
            isBlackjack = true;
        }
    }

    public void printHand() {
        System.out.println(this.name + "'s hand:");
        for (int i = 0; i < handSize; i++) {
            System.out.print(">>>>> ");
            if (hand[i].getValue() == 1) {
                System.out.print("A");
            } else if (hand[i].getValue() == 11) {
                System.out.print("J");
            } else if (hand[i].getValue() == 12) {
                System.out.print("Q");
            } else if (hand[i].getValue() == 13) {
                System.out.print("K");
            } else {
                System.out.print(hand[i].getValue());
            }

            System.out.print(" of ");

            if (hand[i].getSuit() == 0) {
                System.out.print("Clubs");
            } else if (hand[i].getSuit() == 1) {
                System.out.print("Diamonds");
            } else if (hand[i].getSuit() == 2) {
                System.out.print("Hearts");
            } else if (hand[i].getSuit() == 3) {
                System.out.print("Spades");
            }
            System.out.println();
        }
    }

    public void printScore() {
        System.out.println(this.name + "'s score = " + Integer.toString(score));
    }

}
