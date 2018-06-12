package views.games;

// Built-in modules
import java.util.Scanner;

// Hand-made modules
import views.games.BetFinancingView;


/**
 * The view base class for games with playing card
 */
public class PlayingCardGameBaseView extends BetFinancingView {
    /**
     * Constructor
     *
     * @param scanner Scanner object from 'Main'
     */
    public PlayingCardGameBaseView(Scanner scanner) {
        super(scanner);
    }
}