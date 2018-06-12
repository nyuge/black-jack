package models.players;

// Hand-made modules
import models.cards.Hand;
import models.cards.PlayingCard;


public abstract class PlayerBase {
    private Hand hand = new Hand();
    private String name;
    private int score;

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void hit(PlayingCard card) {
        this.hand.addCards(card);
    }
}