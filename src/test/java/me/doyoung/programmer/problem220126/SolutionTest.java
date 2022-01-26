package me.doyoung.programmer.problem220126;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SolutionTest {

    @Test
    void report() {
        // given
        final Solution solution = new Solution();
        final String[] idList = {"muzi", "frodo", "apeach", "neo"};
        final String[] reports = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        final int flagCount = 2;
        // when
        final int[] result = solution.solution(idList, reports, flagCount);
        // then
        /*
        frodo <- muzi, apeach O
        neo <- frodo, muzi O
        muzi <- apeach X
        muzi, muzi, frodo, apeach
        2, 1, 1, 0
        * */
        assertArrayEquals(result, new int[]{2, 1, 1, 0});

    }
}
