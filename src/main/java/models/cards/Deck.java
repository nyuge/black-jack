package models.cards;

// Built-in modules
import java.util.Collections;
import java.util.LinkedList;

// Hand-made modules
import models.cards.CardStackBase;
import models.cards.PlayingCard;
import models.cards.Rank;
import models.cards.Suit;


public class Deck implements CardStackBase {
    private LinkedList<PlayingCard> cards = new LinkedList<>();
    private int numIncludeJoker;

    public Deck() {
        this.setNumIncludeJoker(0);
        this.initialize();
        this.shuffle();
    }

    public Deck(int numIncludeJoker) {
        this.setNumIncludeJoker(numIncludeJoker);
        this.initialize();
        this.shuffle();
    }

    public LinkedList<PlayingCard> getCards() {
        return cards;
    }

    public int getNumIncludeJoker() {
        return numIncludeJoker;
    }

    public void setCards(LinkedList<PlayingCard> cards) {
        this.cards = cards;
    }

    public void setNumIncludeJoker(int numIncludeJoker) {
        this.numIncludeJoker = numIncludeJoker;
    }

    @Override
    public int size() {
        return this.cards.size();
    }

    private void initialize() {
        this.cards.clear();

        for (Suit suit : Suit.values()) {
            if (suit == Suit.JOKER) {
                break;
            }

            for (Rank rank : Rank.values()) {
                if (rank == Rank.JOKER) {
                    break;
                } else {
                    PlayingCard card = new PlayingCard(suit, rank);
                    this.cards.add(card);
                }
            }
        }

        for (int iter = 0; iter < this.numIncludeJoker; iter++) {
            PlayingCard card = new PlayingCard(Suit.JOKER, Rank.JOKER);
            this.cards.add(card);
        }
    }

    private void shuffle() {
        Collections.shuffle(this.cards);
    }

    public PlayingCard poll() {
        return this.cards.pollFirst();
    }

    public PlayingCard pollFirst() {
        return this.cards.pollFirst();
    }

    public PlayingCard pollLast() {
        return this.cards.pollLast();
    }
}