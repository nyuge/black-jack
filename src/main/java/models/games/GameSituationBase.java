package models.games;

// Built-in modules
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Hand-made modules
import models.games.Judge;
import models.players.PlayerBase;
import models.players.User;

/**
 * This is a base class for GameSituation classes.
 * GameSituation classes hold the status of the each games.
 *
 * Notes: Competitor or competitors should be implemented in the sub-class.
 */
public abstract class GameSituationBase {
    private User user;
    private List<PlayerBase> competitors;
    private boolean hasUserBet;
    private BigDecimal bet;
    private Judge judge;

    public User getUser() {
        return user;
    }

    public List<PlayerBase> getCompetitors() {
        return competitors;
    }

    public PlayerBase getCompetitor() {
        return competitors.get(0);
    }

    public boolean getHasUserBet() {
        return hasUserBet;
    }

    public BigDecimal getBet() {
        return bet;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCompetitors(List<PlayerBase> competitors) {
        this.competitors = competitors;
    }

    public void setCompetitor(PlayerBase competitor) {
        List<PlayerBase> competitors = new ArrayList<>();
        competitors.add(competitor);

        this.competitors = competitors;
    }

    public void setHasUserBet(boolean hasUserBet) {
        this.hasUserBet = hasUserBet;
    }

    public void setBet(BigDecimal bet) throws NumberFormatException {
        if (bet.compareTo(BigDecimal.ZERO) < 0) {
            throw new NumberFormatException("Betting price should be non-zero positive number !!!");
        } else {
            this.bet = bet;
        }
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }
}