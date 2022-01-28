package me.doyoung.programmer.problem220128;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution solution;

    @BeforeEach
    void tearDown() {
        solution = new Solution();
    }

    @Test
    void solution() {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        final int[] result = this.solution.solution(lottos, win_nums);
        assertArrayEquals(new int[]{3, 5}, result);
    }
}
