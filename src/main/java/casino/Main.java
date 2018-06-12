package casino;

// Built-in modules
import java.math.BigDecimal;
import java.util.Scanner;

// Hand-made modules
import controllers.games.bj.GameController;
import models.cards.Deck;
import models.cards.Pile;
import models.players.User;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deck deck = new Deck(0);  // numIncludeJoker is 0
        Pile cardStack = new Pile(6, 0); // numIncludeDeck is 6, numIncludeJoker is 0
        User user = new User();
        // TODO reset capital value
        user.setCapital(BigDecimal.valueOf(20));

        GameController bj = new GameController(cardStack, user, scanner); // <defaults> cardStack: Pile, canBeBet: false
        bj.processTask();

        scanner.close();
    }
}