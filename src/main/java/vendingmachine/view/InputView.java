package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputView {

    private static final String NEXT_LINE = System.lineSeparator();

    public int readAmount() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");

        String inputCoin = Console.readLine();

        return InputConverter.convertCoin(inputCoin);
    }

    public List<String> readeItem() {
        System.out.println(NEXT_LINE + "상품명과 가격, 수량을 입력해 주세요.");

        String inputItems = Console.readLine();

        return InputConverter.convertItems(inputItems);
    }

    public int readInputAmount() {
        System.out.println(NEXT_LINE + "투입 금액을 입력해 주세요.");

        String inputAmount = Console.readLine();

        return InputConverter.convertAmount(inputAmount);
    }

    public String readPurchaseItem() {
        System.out.println("구매할 상품명을 입력해 주세요.");

        return Console.readLine().trim();
    }
}
