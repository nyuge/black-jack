package controllers.games;

// Built-in modules
import java.math.BigDecimal;
import java.util.Scanner;

// Hand-made modules
import common.EventList;
import controllers.AbstractController;
import validators.BetFinancingValidator;
import views.games.BetFinancingView;


/**
 * This implements the financing tasks in the casino.
 */
public class BetFinancingController extends AbstractController {
    private BetFinancingView view;

    protected BetFinancingController(Scanner scanner) {
        this.view = new BetFinancingView(scanner);
    }

    public BigDecimal getBet() {
        BetFinancingValidator validator = new BetFinancingValidator();

        this.view.showStart();

        while (true) {
            BigDecimal bet = this.view.readBet();
            if (null == bet) {
                return null;
            }

            EventList eventList = new EventList();
            validator.validateBet(bet, eventList);
            if (!eventList.isEmpty()) {
                eventList.showMessages();
                continue;
            }

            return bet;
        }
    }

}