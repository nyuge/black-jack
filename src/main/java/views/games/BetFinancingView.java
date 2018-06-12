package views.games;

// Built-in modules
import java.math.BigDecimal;
import java.util.Scanner;

// Hand-made modules
import views.AbstractView;


public class BetFinancingView extends AbstractView {
    /**
     * Constructor
     *
     * @param scanner Scanner object from 'Main'
     */
    public BetFinancingView(Scanner scanner) {
        super(scanner);
    }

    public void showStart() {
        System.out.println("How much do you want to bet the game?");
    }

    public BigDecimal readBet() {
        System.out.println("Ten coins: t / Five coins: f / One coin: o / No bet: n");
        System.out.print("Your bet > ");

        switch(scanner.nextLine()) {
            case "t":
            case "10":
                return BigDecimal.valueOf(10.);
            case "f":
            case "5":
                return BigDecimal.valueOf(5.);
            case "o":
            case "1":
                return BigDecimal.valueOf(1.);
            case "n":
            case "0":
                return BigDecimal.ZERO;
            case "r":
            case "q":
                return null;
            default:
                return BigDecimal.valueOf(-1.);
        }
    }

    public void showPayout(BigDecimal payout) {
        System.out.println("=== Cash flow ===");
        System.out.println("You can get " + payout + " coins.");
    }

}