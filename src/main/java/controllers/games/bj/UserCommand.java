package controllers.games.bj;

import models.games.CommandType;


/**
 * This enum includes the commands that the user can take at BlackJack.
 */
public enum UserCommand {
    CHECK_USER_STATUS(CommandType.CHECK_STATUS, "check your hand"),
    CHECK_DEALER_STATUS(CommandType.CHECK_STATUS, "check dealer\'s hand"),
    HIT(CommandType.OPERATE_HAND, "hit"),
    STAND(CommandType.STAY, "stand"),
    SPLIT(CommandType.OPERATE_HAND, "split"),
    DOUBLE_DOWN(CommandType.BET, "double down"),
    SURRENDER(CommandType.BET, "surrender"),
    INSURANCE(CommandType.BET, "insurance");

    private final CommandType commandType;
    private final String commandName;

    UserCommand(CommandType commandType, String commandName) {
        this.commandType = commandType;
        this.commandName = commandName;
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

    public String genCommandMenu(String commandId) {
        return this.commandName + ": " + commandId;
    }

    @Override
    public String toString() {
        return this.commandName;
    }
}
