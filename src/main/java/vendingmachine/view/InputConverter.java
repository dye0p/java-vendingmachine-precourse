package vendingmachine.view;

import vendingmachine.exception.ErrorMessage;

public class InputConverter {

    public static int convertCoin(String inputCoin) {

        return parseCoin(inputCoin);
    }

    private static int parseCoin(String inputCoin) {
        try {
            return Integer.parseInt(inputCoin.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT.getErrorMessage());
        }
    }
}
