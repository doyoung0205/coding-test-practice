package me.doyoung.programmer.problem220127;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public String solution(final String newId) {
        final String lowerNewId = toLowerCase(newId);
        final String removedSpecialNewId = removeSpecialChar(lowerNewId);
        final String removedContinuousDotNewId = removeContinuousDot(removedSpecialNewId);
        final String removedBothEndsDotNewId = removeBothEndsDot(removedContinuousDotNewId);
        final String replacedBlankNewId = replaceBlank(removedBothEndsDotNewId);
        final String lengthCheckedNewId = checkLength(replacedBlankNewId);
        return appendMinChar(lengthCheckedNewId);
    }

    private String appendMinChar(String newId) {
        if (isEmpty(newId)) {
            return newId;
        }
        if (newId.length() <= 2) {
            final String lastChar = newId.substring(newId.length() - 1);
            final StringBuilder newIdBuilder = new StringBuilder(newId);
            while (newIdBuilder.length() < 3) {
                newIdBuilder.append(lastChar);
            }
            return newIdBuilder.toString();

        }

        return newId;
    }

    private String checkLength(String newId) {
        if (isEmpty(newId)) {
            return newId;
        }
        final int length = newId.length();
        if (length > 15) {
            return removeBothEndsDot(newId.substring(0, 15));
        }
        return newId;
    }


    private String replaceBlank(String newId) {
        if (isEmpty(newId)) {
            return "a";
        }
        // 빈 문자열이라면, new_id에 "a"를 대입합니다.
        return newId.replaceAll("\\s", "a");
    }

    private String removeBothEndsDot(String newId) {
        // 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
        final Pattern pattern = Pattern.compile("(^\\.)|(\\.$)");
        final Matcher matcher = pattern.matcher(newId);
        return matcher.replaceAll("");
    }

    private String removeContinuousDot(String newId) {
        final Pattern pattern = Pattern.compile("[.]+");
        final Matcher matcher = pattern.matcher(newId);
        return matcher.replaceAll(".");
    }

    private String removeSpecialChar(String newId) {
        final Pattern pattern = Pattern.compile("[^a-z0-9-_. ]");
        final Matcher matcher = pattern.matcher(newId);
        return matcher.replaceAll("");
    }

    // 모든 대문자를 대응되는 소문자로 치환합니다.
    private String toLowerCase(String newId) {
        if (isEmpty(newId)) {
            return newId;
        }
        return newId.toLowerCase();
    }

    private boolean isEmpty(String newId) {
        return newId == null || newId.length() == 0;
    }

}
