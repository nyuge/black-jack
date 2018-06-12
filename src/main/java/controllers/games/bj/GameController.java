package controllers.games.bj;

// Built-in modules

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Hand-made modules
import common.ClientMessage;
import controllers.games.bj.UserCommand;
import controllers.games.PlayingCardGameBaseController;
import models.cards.CardStackBase;
import models.cards.PlayingCard;
import models.games.CommandType;
import models.games.bj.GameSituation;
import models.games.bj.Rule;
import models.players.Dealer;
import models.players.User;
import views.games.bj.GameView;


/**
 * BlackJack is a game with the playing cards that players approach to 21 scores.
 */
public class GameController extends PlayingCardGameBaseController implements Rule {
    private GameView view;
    private BigDecimal payout;

    public GameController(CardStackBase cardStack, User user, Scanner scanner) {
        super(cardStack, user, scanner);
        this.view = new GameView(scanner);
    }

    public void processTask() {
        BigDecimal bet = this.getBet();
        if (null == bet) {
            return;
        }

        GameSituation situation = this.genInitialGameSituation(bet);
        boolean hasUserBet = situation.getHasUserBet();

        LinkedList<GameSituation> situationsCanBeOperated = new LinkedList<>();
        List<GameSituation> situationsCannotBeOperated = new LinkedList<>();

        situation.setCanRequestUserCommand(true);
        situationsCanBeOperated.offerFirst(situation);

        while (null != (situation = situationsCanBeOperated.pollFirst())) {
            if (!situation.getCanRequestUserCommand()) {
                situationsCannotBeOperated.add(situation);
            } else {
                List<GameSituation> situations = this.operateGameSituationByUser((GameSituation) situation);
                situationsCanBeOperated.addAll(situations);
            }
        }

        CardStackBase cardStack = this.getCardStack();
        BigDecimal totalPayout = BigDecimal.ZERO;
        for (GameSituation endedSituation : situationsCannotBeOperated) {
            endedSituation = Rule.operateGameSituationByDealer(endedSituation, cardStack);

            endedSituation.setIsUserBeingBlackJack(Rule.isBlackJack(endedSituation.getUser()));
            endedSituation.setIsCompetitorBeingBlackJack(Rule.isBlackJack(endedSituation.getCompetitor()));
            // judge the game WIN, DRAW or LOSE after set the statuses of the players
            endedSituation.setJudge(Rule.judgeGame(endedSituation));

            BigDecimal payout = endedSituation.getHasUserBet()
                    ? Rule.calcPayout(endedSituation).setScale(2, BigDecimal.ROUND_HALF_DOWN)
                    : BigDecimal.ZERO;

            this.view.showGameResult(endedSituation);
            totalPayout = totalPayout.add(payout);
        }

        if (hasUserBet) {
            this.view.showPayout(totalPayout);
        }

        // TODO return payout to the user

    }

    private GameSituation genInitialGameSituation(BigDecimal bet) {
        int numCard;
        PlayingCard card;
        CardStackBase cardStack = this.getCardStack();

        User user = this.getUser();
        for (numCard = 0; numCard < 2; numCard++) {
            card = cardStack.poll();
            user.hit(card);
        }
        user.setScore(Rule.calcScore(user));

        Dealer dealer = new Dealer();
        for (numCard = 0; numCard < 2; numCard++) {
            card = cardStack.poll();
            dealer.hit(card);
        }
        dealer.setScore(Rule.calcScore(dealer));

        return new GameSituation(user, dealer, bet);
    }

    private List<GameSituation> operateGameSituationByUser(GameSituation situation) {
        UserCommand command;
        while (true) {
            this.view.showUserOperationMenu(situation);
            command = this.view.readUserCommand();
            if (null == command) {
                System.out.println(ClientMessage.CL_0101);
                continue;
            }

            // validations
            if (command.getCommandType().equals(CommandType.BET) && !situation.getHasUserBet()) {
                System.out.println(ClientMessage.CL_0102);
                continue;
            }
            if (command.equals(UserCommand.SPLIT) && !situation.getCanSplitUserHand()) {
                System.out.println(ClientMessage.CL_0103);
                continue;
            }

            break;
        }

        switch (command) {
            case CHECK_USER_STATUS:
                return showUserStatus(situation);
            case CHECK_DEALER_STATUS:
                return showDealerStatus(situation);
            case HIT:
                return hit(situation);
            case STAND:
                return stand(situation);
            case SPLIT:
                return split(situation);
            case DOUBLE_DOWN:
                return doubleDown(situation);
            case SURRENDER:
                return surrender(situation);
            case INSURANCE:
                return insurance(situation);
            default:
                // any command cannot reach here
        }

        return null;
    }

    private List<GameSituation> showUserStatus(GameSituation situation) {
        this.view.showPlayerStatus(situation.getUser());

        List<GameSituation> situations = new LinkedList<>();
        situations.add(situation);

        return situations;
    }

    private List<GameSituation> showDealerStatus(GameSituation situation) {
        this.view.showPlayerStatus(situation.getCompetitor());

        List<GameSituation> situations = new LinkedList<>();
        situations.add(situation);

        return situations;
    }

    private List<GameSituation> hit(GameSituation situation) {
        CardStackBase cardStack = this.getCardStack();
        User user = situation.getUser();
        String userName = user.getName();

        PlayingCard card = cardStack.poll();
        System.out.println(userName + " get " + card);
        user.hit(card);

        int userScore = Rule.calcScore(user);
        System.out.println("Your current score is " + userScore);
        user.setScore(userScore);

        boolean isUserBusted = Rule.isBusted(user);
        situation.setIsUserBusted(isUserBusted);
        if (isUserBusted) {
            System.out.println(userName + " bust !!!");
            situation.setCanRequestUserCommand(false);
        }

        List<GameSituation> situations = new LinkedList<>();
        situations.add(situation);

        return situations;
    }

    private List<GameSituation> stand(GameSituation situation) {
        boolean isUserBusted = Rule.isBusted(situation.getUser());
        situation.setIsUserBusted(isUserBusted);
        situation.setCanRequestUserCommand(false);

        List<GameSituation> situations = new LinkedList<>();
        situations.add(situation);

        return situations;
    }

    private List<GameSituation> split(GameSituation situation) {
        User user = situation.getUser();
        // TODO is it proper that duplication of user by new User() ? if not, I have to mend it
        User newUser = new User();
        PlayingCard card = user.getHand().getCards().remove(0);
        newUser.hit(card);
        GameSituation newSituation = new GameSituation(newUser, situation.getCompetitor(), situation.getBet());

        BigDecimal bet = situation.getBet();
        BigDecimal refundBet = user.payBet(bet);
        newSituation.setBet(refundBet);

        List<GameSituation> situations = new LinkedList<>();
        situations.add(situation);
        situations.add(newSituation);

        return situations;
    }

    private List<GameSituation> doubleDown(GameSituation situation) {
        CardStackBase cardStack = this.getCardStack();
        User user = situation.getUser();
        String userName = user.getName();

        BigDecimal bet = situation.getBet();
        BigDecimal refundBet = user.payBet(bet);
        situation.setBet(bet.add(refundBet));

        PlayingCard card = cardStack.poll();
        System.out.println(userName + " get " + card);
        user.hit(card);

        int userScore = Rule.calcScore(user);
        System.out.println("Your current score is " + userScore);
        user.setScore(userScore);

        boolean isUserBusted = Rule.isBusted(user);
        situation.setIsUserBusted(isUserBusted);
        if (isUserBusted) {
            System.out.println(userName + " bust !!!");
            situation.setCanRequestUserCommand(false);
        }

        List<GameSituation> situations = new LinkedList<>();
        situations.add(situation);

        return situations;
    }

    private List<GameSituation> surrender(GameSituation situation) {
        situation.setIsUserBusted(false);
        situation.setCanRequestUserCommand(false);

        BigDecimal halfBet = situation.getBet().divide(BigDecimal.valueOf(2.), 2);
        situation.setBet(halfBet);
        User user = situation.getUser();
        user.setCapital(user.getCapital().add(halfBet));

        List<GameSituation> situations = new LinkedList<>();
        situations.add(situation);

        return situations;
    }

    private List<GameSituation> insurance(GameSituation situation) {
        situation.setHasUserRequestedInsurance(true);

        BigDecimal bet = situation.getBet();
        BigDecimal halfBet = bet.divide(BigDecimal.valueOf(2.), 2);
        User user = situation.getUser();
        BigDecimal refundBet = user.payBet(halfBet);
        situation.setBet(bet.add(refundBet));

        List<GameSituation> situations = new LinkedList<>();
        situations.add(situation);

        return situations;
    }

}