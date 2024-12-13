package vendingmachine.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import vendingmachine.model.Coins;
import vendingmachine.model.Item;
import vendingmachine.model.Items;
import vendingmachine.model.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {

    private final InputView inputView;
    private final OutputView outputView;
    private int inputAmount;

    public VendingMachineController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        //보유 금액 입력
        VendingMachine vendingMachine = tryReadAmount();

        //동전 생성
        Map<Integer, Integer> randomCoins = vendingMachine.creatRandomCoin();

        Coins coins = new Coins(randomCoins);

        //자판기가 보유한 동전 출력
        outputView.printMachineCoins(randomCoins);

        //상품명, 가격, 수량을 입력
        Items items = tryReadItems();

        //투입 금액 입력
        inputAmount = tryReadInputAmount();

        // 남은 금액이 상품의 최저 가격보다 적거나, 모든 상품이 소진된 경우 바로 잔돈을 돌려준다.
        // 남은 금액이 상품의 최저 가격보다 낮지 않을 경우, 모든 상품이 소진되지 않은 경우 계속 실행
        while (!items.isAllItemOutOfStock() && !items.isMinPriceItemMore(inputAmount)) {
            outputView.printInputAmount(inputAmount);

            Item purchaseItem = tryPurchaseItem(items);

            inputAmount = items.purchase(purchaseItem, inputAmount);
        }

        outputView.printInputAmount(inputAmount);

        //잔돈 반환
        Map<Integer, Integer> returnCoin = coins.returnChange(inputAmount, vendingMachine.getAmount());

        outputView.printReturnCoin(returnCoin);
    }

    private Item tryPurchaseItem(Items items) {
        return requestRead(() -> {
            String itemName = inputView.readPurchaseItem();
            return items.findByName(itemName);
        });
    }


    private int tryReadInputAmount() {
        return requestRead(() -> {
            inputAmount = inputView.readInputAmount();

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
