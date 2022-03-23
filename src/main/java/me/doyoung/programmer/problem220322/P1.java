package me.doyoung.programmer.problem220322;

public class P1 {
    public String solution(String s) {
        int length = s.length();
        final int beginIndex = (length - 1) / 2;
        final int endIndex = length / 2 + 1;
        return s.substring(beginIndex, endIndex);
    }
}
