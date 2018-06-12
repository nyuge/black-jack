package models.cards;

// Built-in modules
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// Hand-made modules
import models.cards.CardStackBase;
import models.cards.PlayingCard;
import models.cards.Rank;
import models.cards.Suit;


public class Pile implements CardStackBase {
    private static Random RANDOM_INT_GENERATOR = new Random();
    private Map<PlayingCard, Integer> cards = new HashMap<>();
    private int numIncludeDeck;
    private int numIncludeJoker;

    public Pile() {
        this.setNumIncludeDeck(6);
        this.setNumIncludeJoker(0);
        this.initialize();
    }

    public Pile(int numIncludeDeck, int numIncludeJoker) {
        this.setNumIncludeDeck(numIncludeDeck);
        this.setNumIncludeJoker(numIncludeJoker);
        this.initialize();
    }

    public Map<PlayingCard, Integer> getCards() {
        return cards;
    }

    public int getNumIncludeDeck() {
        return numIncludeDeck;
    }

    public int getNumIncludeJoker() {
        return numIncludeJoker;
    }

    public void setCards(Map<PlayingCard, Integer> cards) {
        this.cards = cards;
    }

    public void setNumIncludeDeck(int numIncludeDeck) {
        this.numIncludeDeck = numIncludeDeck;
    }

    public void setNumIncludeJoker(int numIncludeJoker) {
        this.numIncludeJoker = numIncludeJoker;
    }

    @Override
    public int size() {
        int numTotalCards = 0;
        for (int numRemainedCards : this.cards.values()) {
            numTotalCards += numRemainedCards;
        }

        return numTotalCards;
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
                    this.cards.put(card, this.numIncludeDeck);
                }
            }
        }

        PlayingCard card = new PlayingCard(Suit.JOKER, Rank.JOKER);
        this.cards.put(card, this.numIncludeJoker);
    }

    public PlayingCard poll() {
        int randomInt, numTotalCards, numRemainedCards;

        randomInt = RANDOM_INT_GENERATOR.nextInt(this.size());
        numTotalCards = 0;
        for (PlayingCard card : this.cards.keySet()) {
            numRemainedCards = this.cards.get(card);

            if ((numTotalCards += numRemainedCards) > randomInt) {
                this.cards.put(card, numRemainedCards - 1);
                return card;
            }
        }

        return new PlayingCard(Suit.JOKER, Rank.JOKER);
    }

}