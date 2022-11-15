package lotto;

import java.util.HashMap;
import java.util.List;

public class Calculator {
    HashMap<Rank, Integer> countOfWins = new HashMap<>() {{
        put(Rank.FIFTH_PLACE, 0);
        put(Rank.FOURTH_PLACE, 0);
        put(Rank.THIRD_PLACE, 0);
        put(Rank.SECOND_PLACE, 0);
        put(Rank.FIRST_PLACE, 0);
    }};

    public void calculate(List<Lotto> Lotteries, List<Integer> winningNumbers, int bonusNumber) {
        for (Lotto lotto : Lotteries) {
            int count = countMatchingNumber(lotto, winningNumbers);
            if (count < 3) {
                continue;
            }
            Rank rank = getRank(count, lotto.hasNumber(bonusNumber));
            if (rank != null) {
                countOfWins.put(rank, countOfWins.get(rank) + 1);
            }
        }
    }

    public int countMatchingNumber(Lotto lotto, List<Integer> numbers) {
        int count = 0;
        for (Integer number : numbers) {
            if (lotto.hasNumber(number)) {
                count++;
            }
        }
        return count;
    }

    public Rank getRank(int count, boolean hasBonusNumber) {
        if (count == 5 && hasBonusNumber) {
            return Rank.SECOND_PLACE;
        }
        for (Rank rank : Rank.values()) {
            if (count == rank.getNumberOfMatch()) {
                return rank;
            }
        }
        return null;
    }
}