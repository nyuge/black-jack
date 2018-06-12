package models.cards;

// Built-in modules
import java.util.LinkedList;
import java.util.List;

// Hand-made modules
import models.cards.PlayingCard;
import models.cards.PlayingCardComparator;


public class Hand {
    private static PlayingCardComparator COMPARATOR = new PlayingCardComparator();
    private List<PlayingCard> cards = new LinkedList<>();

    public List<PlayingCard> getCards() {
        return cards;
    }

    public void setCards(List<PlayingCard> cards) {
        this.cards = cards;
    }

    private void initialize() {
        this.cards.clear();
    }

    public void addCards(PlayingCard card) {
        this.cards.add(card);
    }

    public void addCards(List<PlayingCard> cards) {
        this.cards.addAll(cards);
    }

    public int size() {
        return this.cards.size();
    }

    public String castCardsToStringWithMasks(int upCardIndex) {
        StringBuilder sb = new StringBuilder("[");

        for (int numCardOrder = 0; numCardOrder < this.size(); numCardOrder++) {
            if (numCardOrder == upCardIndex) {
                sb.append(this.cards.get(upCardIndex).toString());
            } else {
                sb.append("?");
            }
            sb.append(", ");
        }

        int lenStringBuffer = sb.length();
        sb.delete(lenStringBuffer - 2, lenStringBuffer);
        sb.append("]");

        return sb.toString();
    }

    @Override
    public String toString() {
        List<PlayingCard> sortedCards = new LinkedList<>(this.getCards());
        sortedCards.sort(COMPARATOR);

        return sortedCards.toString();
    }
}