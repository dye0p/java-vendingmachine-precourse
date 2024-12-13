package vendingmachine.model;

import java.util.Map;
import vendingmachine.exception.ErrorMessage;

public class VendingMachine {

    private final int amount;

    public VendingMachine(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount % 10 != 0) {
            throw new IllegalArgumentException(ErrorMessage.DIVIDE_AMOUNT.getErrorMessage());
        }
    }

    public Map<Integer, Integer> creatRandomCoin() {
        return RandomCoinGenerator.createRandomCoins(amount);
    }

    public int getAmount() {
        return amount;
    }
}
