package me.doyoung.programmer.problem220322;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class P1Test {
    @Test
    void name() {
        final P1 p1 = new P1();

        assertEquals(p1.solution("abcde"), "c");

        assertEquals(p1.solution("qwer"), "we");
    }
}
