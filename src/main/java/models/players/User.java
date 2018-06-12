package models.players;

// Built-in modules
import java.math.BigDecimal;

// Hand-made modules
import models.players.PlayerBase;


/**
 * User is an implementation of a person
 * who join in a game with the playing card as a competitor.
 */
public class User extends PlayerBase {
    private BigDecimal capital;

    public User() {
        this.setName("You");
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal payBet(BigDecimal bet) {
        this.capital = this.capital.subtract(bet);
        return bet;
    }

}