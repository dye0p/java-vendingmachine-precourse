package vendingmachine.controller;

import java.util.function.Supplier;
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
