package vendingmachine.view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
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

    public static List<String> convertItems(String inputItems) {

        //콤마로 구분하고, 세미콜론으로 상품을 구별
        return splitItems(inputItems);
    }

    private static List<String> splitItems(String inputItems) {
        try {
            String[] split = inputItems.trim().split(";");

            List<String> items = new ArrayList<>();
            for (String s : split) {
                String replace = s.replace("[", "").replace("]", "");

                items.add(replace);
            }

            return items;
        } catch (PatternSyntaxException exception) {
            throw new IllegalArgumentException("구분자로 구분해야 합니다.");
        }
    }
}
