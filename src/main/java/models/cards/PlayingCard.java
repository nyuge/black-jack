package models.cards;

// Built-in modules
import java.util.Objects;

// Hand-made modules
import models.cards.Rank;
import models.cards.Suit;


public class PlayingCard implements Comparable {
    private Suit suit;
    private Rank rank;

    public PlayingCard(Suit suit, Rank rank) {
        this.setSuit(suit);
        this.setRank(rank);
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    private String getSuitString() {
        switch (this.getSuit()) {
            case CLUBS:
                return "CLUB-";
            case DIAMONDS:
                return "DIAMOND-";
            case HEARTS:
                return "HEART-";
            case SPADES:
                return "SPADE-";
            default:
                return "";
        }
    }

    private String getRankString() {
        switch (this.getRank()) {
            case ACE:
                return "A";
            case TWO:
                return "2";
            case THREE:
                return "3";
            case FOUR:
                return "4";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case NINE:
                return "9";
            case TEN:
                return "10";
            case JACK:
                return "J";
            case QUEEN:
                return "Q";
            case KING:
                return "K";
            case JOKER:
                return "JOKER";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return this.getSuitString() + this.getRankString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayingCard)) {
            return false;
        }

        PlayingCard that = (PlayingCard) obj;
        return this.suit == that.suit &&
                this.rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.suit, this.rank);
    }

    @Override
    public int compareTo(Object obj) {
        if (!(obj instanceof PlayingCard)) {
            return 1;
        }

        PlayingCard that = (PlayingCard) obj;

        if (this.getRank().compareTo(that.getRank()) > 0) {
            return 1;
        } else if (this.getRank().compareTo(that.getRank()) == 0) {
            if (this.getSuit().compareTo(that.getSuit()) > 0) {
                return 1;
            } else if (this.getSuit().compareTo(that.getSuit()) == 0) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}