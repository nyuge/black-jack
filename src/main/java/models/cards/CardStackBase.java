package models.cards;

// Hand-made modules
import models.cards.PlayingCard;
import models.cards.Rank;
import models.cards.Suit;

/**
 * This is the base class as a stack (or queue) of playing cards
 */
public interface CardStackBase {
    public abstract int size();

    public abstract PlayingCard poll();
}