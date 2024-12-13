package vendingmachine.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Coins {

    private Map<Integer, Integer> randomCoins;

    public Coins(Map<Integer, Integer> randomCoins) {
        this.randomCoins = randomCoins;
    }

    public Map<Integer, Integer> returnChange(int inputAmount, int amount) {

        //자판기에 있는 동전이 반환해야 하는 잔돈보다 적으면 자판기에 있는 모든 동전을 반환

        Map<Integer, Integer> returnCoin = new LinkedHashMap<>();
        if (amount < inputAmount) {
            for (Entry<Integer, Integer> integerIntegerEntry : randomCoins.entrySet()) {
                if (integerIntegerEntry.getValue() > 0) {
                    returnCoin.put(integerIntegerEntry.getKey(), integerIntegerEntry.getValue());
                }
            }
            return returnCoin;
        }

        if (amount > inputAmount) { // 자판기의 금액이 잔돈보다 많은 경우
            //잔돈 만큼만 반환해야 한다.
            for (Entry<Integer, Integer> integerIntegerEntry : randomCoins.entrySet()) {
                int returnSum = 0; //반환 금액이 inputAmount와 동일하면 리턴

                if (integerIntegerEntry.getValue() > 0) {
                    returnSum += integerIntegerEntry.getValue();

                    returnCoin.put(integerIntegerEntry.getKey(), integerIntegerEntry.getValue());
                }

                if (returnSum == inputAmount) {
                    return returnCoin;
                }
            }
        }

        for (Entry<Integer, Integer> integerIntegerEntry : randomCoins.entrySet()) {
            if (integerIntegerEntry.getValue() > 0) {
                returnCoin.put(integerIntegerEntry.getKey(), integerIntegerEntry.getValue());
            }
        }
        return returnCoin;
    }
}
