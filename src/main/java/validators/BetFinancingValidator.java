package validators;

// Built-in modules
import java.math.BigDecimal;

// Hand-made modules
import common.ClientMessage;
import common.EventList;



/**
 * This is the validation class used in betting processes.
 */
public class BetFinancingValidator {
    public BetFinancingValidator() {}

    public void validateBet(BigDecimal bet, EventList eventList) {
        if (bet.compareTo(BigDecimal.ZERO) < 0) {
            eventList.addMessage(ClientMessage.CL_0001);
        }
    }

}