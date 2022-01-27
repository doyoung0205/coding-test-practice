package me.doyoung.programmer.problem220127;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    Solution solution;

    @BeforeEach
    void tearDown() {
        solution = new Solution();
    }

    @Test
    @DisplayName("모든 대문자를 대응되는 소문자로 치환합니다.")
    void upper() {
        final String upperString = "ASD";
        final String recommendedId = solution.solution(upperString);
        assertEquals("asd", recommendedId);
    }

    @Test
    @DisplayName("알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.")
    void specialCharacter() {
        final String specialString = "감 _123-.1212..!@#$%^&*()_+=";
        final String recommendedId = solution.solution(specialString);
        assertEquals("a_123-.1212._", recommendedId);
    }

    @Test
    @DisplayName("마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.")
    void removeContinuousDot() {
        final String continuousDot = ".1212..2....";
        final String recommendedId = solution.solution(continuousDot);
        assertEquals("1212.2", recommendedId);
    }

    @Test
    @DisplayName("마침표(.)가 처음이나 끝에 위치한다면 제거합니다.")
    void removeBothEndsDot() {
        final String continuousDot = ".12a12.2.";
        final String recommendedId = solution.solution(continuousDot);
        assertEquals("12a12.2", recommendedId);
    }

    @Test
    @DisplayName("빈 문자열이라면, new_id에 \"a\"를 대입합니다.")
    void replaceBlank() {
        final String continuousDot = "b b b";
        final String recommendedId = solution.solution(continuousDot);
        assertEquals("babab", recommendedId);
    }

    @Test
    @DisplayName("길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.")
    void checkLength() {
        final String continuousDot = "bbbbbbbbbbbbbb.bbbb";
        final String recommendedId = solution.solution(continuousDot);
        assertEquals("bbbbbbbbbbbbbb", recommendedId);
    }

    @Test
    @DisplayName("new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.")
    void appendMinChar() {
        final String continuousDot = ".bb";
        final String recommendedId = solution.solution(continuousDot);
        assertEquals("bbb", recommendedId);
    }

    @Test
    @DisplayName("다양한 케이스")
    void test() {
        assertAll(
                () -> assertEquals("bat.y.abcdefghi", solution.solution("..!@BaT#*..y.abcdefghijklm")),
                () -> assertEquals("z--", solution.solution("z-+.^.")),
                () -> assertEquals("aaa", solution.solution("=.=")),
                () -> assertEquals("123_.def", solution.solution("123_.def")),
                () -> assertEquals("abcdefghijklmn", solution.solution("abcdefghijklmn.p"))
        );
    }


}
