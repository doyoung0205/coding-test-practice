package me.doyoung.programmer.socar220320;

public class P1Main {
    public static void main(String[] args) {
        final P1 p1 = new P1();
        int[][] roads =
                new int[][]{new int[]{5, 4, 6}, new int[]{5, 2, 5}, new int[]{0, 4, 2}, new int[]{2, 3, 3}, new int[]{1, 2, 7}, new int[]{0, 1, 3}};
        int rentalTime = 17;
        int rentalOfficeCount = 6;
        final int[] solution = p1.solution(rentalOfficeCount, rentalTime, roads);
//        System.out.println("solution = " + Arrays.toString(Arrays.stream(solution).toArray()));
    }
}
