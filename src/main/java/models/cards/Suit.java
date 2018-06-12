package models.cards;

/**
 * This is the enum of the suits in playing cards.
 *
 * Joker should be marked as no-suit, however,
 * it would be better that Joker has the unique category in the point of comprehensive internal processes,
 * Then, Joker has "Joker" fictional enum.
 */
public enum Suit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES,
    JOKER
}