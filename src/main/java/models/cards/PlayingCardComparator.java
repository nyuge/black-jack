package models.cards;

// Built-in modules
import java.util.Comparator;

// Hand-made modules
import models.cards.PlayingCard;


/**
 * This is a comparator class for playing cards
 *
 * Playing cards are sorted in descending order of rank and suit.
 * In the point of suit, the 4 suits should be arranged as follows:
 * CLUBS < DIAMONDS < HEARTS < SPADES.
 */
public class PlayingCardComparator implements Comparator<PlayingCard> {
    @Override
    public int compare(PlayingCard card1, PlayingCard card2) {
        return card1.compareTo(card2);
    }
}