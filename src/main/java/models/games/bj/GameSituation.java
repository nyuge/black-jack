package models.games.bj;

// Built-in modules
import java.math.BigDecimal;

// Hand-made modules
import models.games.GameSituationBase;
import models.players.PlayerBase;
import models.players.User;


public class GameSituation extends GameSituationBase {
    private boolean canRequestUserCommand;
    private boolean canSplitUserHand;
    private boolean hasUserRequestedInsurance;
    private boolean isUserBeingBlackJack;
    private boolean isCompetitorBeingBlackJack;
    private boolean isUserBusted;

    public GameSituation(User user, PlayerBase competitor, BigDecimal bet) {
        this.setUser(user);
        this.setCompetitor(competitor);
        this.setBet(bet);

        // if no-bet has been chosen, the commands in the games are simplified.
        boolean hasUserBet = bet.compareTo(BigDecimal.ZERO) > 0;
        this.setHasUserBet(hasUserBet);
    }

    public boolean getCanRequestUserCommand() {
        return canRequestUserCommand;
    }

    public boolean getCanSplitUserHand() {
        return canSplitUserHand;
    }

    public boolean getHasUserRequestedInsurance() {
        return hasUserRequestedInsurance;
    }

    public boolean getIsUserBeingBlackJack() {
        return isUserBeingBlackJack;
    }

    public boolean getIsCompetitorBeingBlackJack() {
        return isCompetitorBeingBlackJack;
    }

    public boolean getIsUserBusted() {
        return isUserBusted;
    }

    public void setCanRequestUserCommand(boolean canRequestUserCommand) {
        this.canRequestUserCommand = canRequestUserCommand;
    }

    public void setCanSplitUserHand(boolean canSplitUserHand) {
        this.canSplitUserHand = canSplitUserHand;
    }

    public void setHasUserRequestedInsurance(boolean hasUserRequestedInsurance) {
        this.hasUserRequestedInsurance = hasUserRequestedInsurance;
    }

    public void setIsUserBeingBlackJack(boolean isUserBeingBlackJack) {
        this.isUserBeingBlackJack = isUserBeingBlackJack;
    }

    public void setIsCompetitorBeingBlackJack(boolean isCompetitorBeingBlackJack) {
        this.isCompetitorBeingBlackJack = isCompetitorBeingBlackJack;
    }

    public void setIsUserBusted(boolean isUserBusted) {
        this.isUserBusted = isUserBusted;
    }

}