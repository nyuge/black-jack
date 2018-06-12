package views.games.bj;

// Built-in modules
import java.util.Scanner;

// Hand-made modules
import controllers.games.bj.UserCommand;
import models.games.bj.GameSituation;
import models.games.bj.Rule;
import models.players.PlayerBase;
import models.players.User;
import views.games.PlayingCardGameBaseView;


public class GameView extends PlayingCardGameBaseView {
    public GameView(Scanner scanner) {
        super(scanner);
    }

    public void showUserOperationMenu(GameSituation situation) {
        System.out.println("=== Choose your next operation ===");

        System.out.println(UserCommand.CHECK_USER_STATUS.genCommandMenu("1"));
        System.out.println(UserCommand.CHECK_DEALER_STATUS.genCommandMenu("2"));
        System.out.println(UserCommand.HIT.genCommandMenu("3"));
        System.out.println(UserCommand.STAND.genCommandMenu("4"));

        if (situation.getCanSplitUserHand()) {
            System.out.println(UserCommand.SPLIT.genCommandMenu("5"));
        }

        if (situation.getHasUserBet()) {
            System.out.println(UserCommand.DOUBLE_DOWN.genCommandMenu("6"));
            System.out.println(UserCommand.SURRENDER.genCommandMenu("7"));
            System.out.println(UserCommand.INSURANCE.genCommandMenu("8"));
        }

        System.out.print("Your choice > ");
    }

    public UserCommand readUserCommand() throws NumberFormatException {
        String inputStr = this.getScanner().nextLine();
        switch (inputStr) {
            case "1":
                return UserCommand.CHECK_USER_STATUS;
            case "2":
                return UserCommand.CHECK_DEALER_STATUS;
            case "3":
                return UserCommand.HIT;
            case "4":
                return UserCommand.STAND;
            case "5":
                return UserCommand.SPLIT;
            case "6":
                return UserCommand.DOUBLE_DOWN;
            case "7":
                return UserCommand.SURRENDER;
            case "8":
                return UserCommand.INSURANCE;
            default:
                return null;
        }
    }

    public void showPlayerStatus(PlayerBase player) {
        System.out.println(player.getName() +
                ": "  +
                player.getHand() +
                " -> " +
                GameView.castScoreWithBustStatus(player.getScore()));
    }

    private static String castScoreWithBustStatus(int score) {
        if (Rule.isBusted(score)) {
            return Integer.toString(score) + " (BUST)";
        } else {
            return Integer.toString(score);
        }
    }

    public void showGameResult(GameSituation situation) {
        User user = situation.getUser();
        PlayerBase dealer = situation.getCompetitor();

        System.out.println("=== Result ===");
        this.showPlayerStatus(user);
        this.showPlayerStatus(dealer);
        System.out.println(user.getName() + " " + situation.getJudge() + ".");
    }

}