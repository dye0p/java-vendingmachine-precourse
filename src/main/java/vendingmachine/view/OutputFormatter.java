package vendingmachine.view;

import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

public class OutputFormatter {

    private static final String NEXT_LINE = System.lineSeparator();

    public static String formatMachineCoins(Map<Integer, Integer> randomCoins) {
        StringJoiner sj = new StringJoiner(NEXT_LINE);
        for (Entry<Integer, Integer> integerIntegerEntry : randomCoins.entrySet()) {
            String format = String.format("%d원 - %d개", integerIntegerEntry.getKey(), integerIntegerEntry.getValue());
            sj.add(format);
        }

        return sj.toString();
    }
}
