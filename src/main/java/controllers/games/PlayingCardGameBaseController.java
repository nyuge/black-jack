package controllers.games;

// Built-in modules
import java.util.Scanner;

// Hand-made modules
import controllers.games.BetFinancingController;
import models.cards.CardStackBase;
import models.players.User;
import views.games.PlayingCardGameBaseView;


/**
 * This is the base class for the games with playing carda.
 */
public abstract class PlayingCardGameBaseController extends BetFinancingController {
    private CardStackBase cardStack;
    private User user;
    private PlayingCardGameBaseView view;

    protected PlayingCardGameBaseController(CardStackBase cardStack, User user, Scanner scanner) {
        super(scanner);
        this.setCardStack(cardStack);
        this.setUser(user);
        this.view = new PlayingCardGameBaseView(scanner);
    }

    public CardStackBase getCardStack() {
        return cardStack;
    }

    public User getUser() {
        return user;
    }

    public void setCardStack(CardStackBase cardStack) {
        this.cardStack = cardStack;
    }

    public void setUser(User user) {
        this.user = user;
    }

}