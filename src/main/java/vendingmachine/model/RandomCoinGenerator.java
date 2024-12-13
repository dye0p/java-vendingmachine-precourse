package vendingmachine.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.Coin;

public class RandomCoinGenerator {

    private static final List<Integer> COIN_INDEX = Arrays.asList(
            Coin.COIN_500.getAmount(),
            Coin.COIN_100.getAmount(),
            Coin.COIN_50.getAmount(),
            Coin.COIN_10.getAmount());

    public static Map<Integer, Integer> createRandomCoins(int amount) {
        Map<Integer, Integer> coinMap = new LinkedHashMap<>();

        for (Integer coinIndex : COIN_INDEX) {
            coinMap.put(coinIndex, 0);
        }

        while (amount != 0) {
            int randomCoin = Randoms.pickNumberInList(COIN_INDEX);
            if (randomCoin <= amount) {
                amount -= randomCoin;
                coinMap.put(randomCoin, coinMap.getOrDefault(randomCoin, 0) + 1);
            }
        }

        return coinMap;
    }
}
