package me.doyoung.programmer.problem220128;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int[] solution(int[] lottos, int[] winNums) {
        final Set<Integer> winNumSet = convertSet(winNums);
        final int matchCount = getMatchCount(lottos, winNumSet);
        final int zeroCount = getZeroCount(lottos);
        return new int[]{
                getRankByCount(matchCount + zeroCount),
                getRankByCount(matchCount)
        };
    }

    private int getRankByCount(int matchCount) {
        if (matchCount < 2) {
            return 6;
        }
        return 7 - matchCount;
    }

    private int getZeroCount(int[] lottos) {
        int zeroCount = 0;
        for (int lotto : lottos) {
            if (lotto == 0) {
                zeroCount++;
            }
        }
        return zeroCount;
    }

    private int getMatchCount(int[] lottos, Set<Integer> winNumSet) {
        int matchCount = 0;
        for (int lotto : lottos) {
            if (winNumSet.contains(lotto)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    private Set<Integer> convertSet(int[] winNums) {
        final Set<Integer> winNumSet = new HashSet<>();
        for (int winNum : winNums) {
            winNumSet.add(winNum);
        }
        return winNumSet;
    }
}
