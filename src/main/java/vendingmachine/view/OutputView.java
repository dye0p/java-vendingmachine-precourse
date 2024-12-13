package vendingmachine.view;

import java.util.Map;

public class OutputView {

    private static final String NEXT_LINE = System.lineSeparator();

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printMachineCoins(Map<Integer, Integer> randomCoins) {
        System.out.println(NEXT_LINE + "자판기가 보유한 동전");

        String coinResult = OutputFormatter.formatMachineCoins(randomCoins);
        System.out.println(coinResult);
    }

    public void printInputAmount(int amount) {
        String format = String.format("투입 금액: %d원", amount);
        System.out.println(NEXT_LINE + format);
    }

    public void printReturnCoin(Map<Integer, Integer> returnCoin) {
        System.out.println("잔돈");

        String s = OutputFormatter.formatMachineCoins(returnCoin);
        System.out.println(s);
    }
}
