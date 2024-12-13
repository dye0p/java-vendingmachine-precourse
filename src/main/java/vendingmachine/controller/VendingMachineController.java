package vendingmachine.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import vendingmachine.model.Item;
import vendingmachine.model.Items;
import vendingmachine.model.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {

    private final InputView inputView;
    private final OutputView outputView;

    public VendingMachineController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        //보유 금액 입력
        VendingMachine vendingMachine = tryReadAmount();

        //동전 생성
        Map<Integer, Integer> randomCoins = vendingMachine.creatRandomCoin();

        //자판기가 보유한 동전 출력
        outputView.printMachineCoins(randomCoins);

        //상품명, 가격, 수량을 입력
        Items items = tryReadItems();

        //투입 금액 입력
        int amount = tryReadInputAmount();

        //투입 금액 출력
        outputView.printInputAmount(amount);

        //구매할 상품 입력
        Item item = tryPurchaseItem(items);
    }

    private Item tryPurchaseItem(Items items) {
        return requestRead(() -> {
            String itemName = inputView.readPurchaseItem();
            return items.findByName(itemName);
        });
    }


    private int tryReadInputAmount() {
        return requestRead(() -> {
            int inputAmount = inputView.readInputAmount();

            if (inputAmount < 0) {
                throw new IllegalArgumentException("투입 금액은 0원 이상이어야 합니다.");
            }

            return inputAmount;
        });
    }

    private Items tryReadItems() {
        return requestRead(() -> {
            List<String> readItems = inputView.readeItem();

            List<Item> items = new ArrayList<>();
            for (String readItem : readItems) {
                String[] split = readItem.split(",");

                String name = split[0];
                int price = Integer.parseInt(split[1]);
                int quantity = Integer.parseInt(split[2]);

                Item item = new Item(name, price, quantity);

                items.add(item);
            }

            return new Items(items);
        });
    }

    private VendingMachine tryReadAmount() {
        return requestRead(() -> {
            int amount = inputView.readAmount();
            return new VendingMachine(amount);
        });
    }


    private <T> T requestRead(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }
}
