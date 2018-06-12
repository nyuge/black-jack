package models.games.bj;

// Built-in modules
import java.math.BigDecimal;
import java.util.List;

// Hand-made modules
import models.cards.CardStackBase;
import models.cards.PlayingCard;
import models.cards.Hand;
import models.games.bj.GameSituation;
import models.games.Judge;
import models.players.PlayerBase;
import models.players.User;


/**
 * This is an interface that includes the game logic of BlackJack
 */
public interface Rule {
    static int calcScore(PlayerBase player) {
        int score = 0;
        int numIncludeAce = 0;

        for (PlayingCard card : player.getHand().getCards()) {
            switch (card.getRank()) {
                case ACE:
                    numIncludeAce++;
                    score += 1;
                    break;
                case TWO:
                    score += 2;
                    break;
                case THREE:
                    score += 3;
                    break;
                case FOUR:
                    score += 4;
                    break;
                case FIVE:
                    score += 5;
                    break;
                case SIX:
                    score += 6;
                    break;
                case SEVEN:
                    score += 7;
                    break;
                case EIGHT:
                    score += 8;
                    break;
                case NINE:
                    score += 9;
                    break;
                default:
                    score += 10;
            }
        }

        while (numIncludeAce > 0) {
            score += 10;
            if (Rule.isBusted(score)) {
                score -= 10;
                break;
            } else {
                numIncludeAce--;
            }
        }

        return score;
    }

    static boolean isBlackJack(PlayerBase player) {
        return player.getScore() == 21 &&
                player.getHand().getCards().size() == 2;
    }

    static boolean isBusted(int score) {
        return score > 21;
    }

    static boolean isBusted(PlayerBase player) {
        return player.getScore() > 21;
    }

    static boolean canSplit(Hand hand) {
        List<PlayingCard> cards = hand.getCards();
        return cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank();
    }

    static boolean canSplit(PlayerBase player) {
        List<PlayingCard> cards = player.getHand().getCards();
        return cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank();
    }

    static Judge judgeGame(GameSituation situation) {
        int userScore = situation.getUser().getScore();
        int competitorScore = situation.getCompetitor().getScore();
        boolean isUserBeingBlackJack = situation.getIsUserBeingBlackJack();
        boolean isCompetitorBeingBlackJack = situation.getIsCompetitorBeingBlackJack();

        if (Rule.isBusted(userScore)) {
            return Judge.LOSE;
        }
        if (Rule.isBusted(competitorScore)) {
            return Judge.WIN;
        }
        if (userScore == competitorScore) {
            if (isUserBeingBlackJack && isCompetitorBeingBlackJack) {
                return Judge.DRAW;
            } else if (isUserBeingBlackJack) {
                return Judge.WIN;
            } else if (isCompetitorBeingBlackJack) {
                return Judge.LOSE;
            } else {
                return Judge.DRAW;
            }
        } else if (userScore > competitorScore) {
            return Judge.WIN;
        } else {
            return Judge.LOSE;
        }
    }

    static GameSituation operateGameSituationByDealer(GameSituation situation, CardStackBase cardStack) {
        User user = situation.getUser();
        PlayerBase dealer = situation.getCompetitor();

        // Loop for the dealer operations
        if (!situation.getIsUserBusted()) {
            int userScore = user.getScore();
            int dealerScore = dealer.getScore();

            while (dealerScore < 17) {
                if (dealerScore > userScore) {
                    break;
                }

                PlayingCard card = cardStack.poll();
                dealer.hit(card);

                dealerScore = Rule.calcScore(dealer);
                dealer.setScore(dealerScore);
            }
        }

        return situation;
    }

    static BigDecimal calcPayout(GameSituation situation) {
        BigDecimal bet = situation.getBet();

        switch (situation.getJudge()) {
            case WIN:
                if (situation.getIsUserBeingBlackJack()) {
                    return bet.multiply(BigDecimal.valueOf(2.5));
                } else {
                    return bet.multiply(BigDecimal.valueOf(2.0));
                }
            case LOSE:
                return bet.multiply(BigDecimal.ZERO);
            default:
                return bet;
        }
    }
}