package me.doyoung.programmer.socar220320;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class P1 {
    /***
     *
     * @param rentalOfficeCount 대여소 갯수
     * @param rentalTime 자전거 대여시간
     * @param roads 각 지점을 연결하는 자전거 도로와 도로를 이용하는 데 걸리는 시간을 나타내는 2차원 정수배]
     *              {보관소 A 번호, 보관소 B 번호, 이동 시간}
     * @return
     */
    public int[] solution(int rentalOfficeCount, int rentalTime, int[][] roads) {
        System.out.println("rentalTime  = " + rentalTime);
        System.out.println("rentalOfficeCount = " + rentalOfficeCount);

        Map<Integer, List<RentalOffice>> rentalOfficeMap = new HashMap<>();
        for (int[] road : roads) {

            final RentalOffice office = new RentalOffice(road);

            addRentalOffice(rentalOfficeMap, office);
            addRentalOffice(rentalOfficeMap, office.reverse());

        }


        final RentalOffice rentalOffice = getRentalOffice(rentalOfficeMap, 0);
        final List<Integer> answers = new ArrayList<>();
        search(answers, new Histories(rentalOffice), rentalOfficeMap, 0, rentalTime);

        if (answers.isEmpty()) {
            return new int[]{-1};
        }

        return new HashSet<>(answers).stream().mapToInt(Integer::intValue).toArray();
    }

    private void addRentalOffice(Map<Integer, List<RentalOffice>> rentalOfficeMap, RentalOffice rentalOffice) {
        final List<RentalOffice> rentalOffices = rentalOfficeMap
                .getOrDefault(rentalOffice.getId(), new ArrayList<>());
        rentalOffices.add(rentalOffice);
        rentalOfficeMap.put(rentalOffice.getId(), rentalOffices);
    }

    private RentalOffice getRentalOffice(Map<Integer, List<RentalOffice>> rentalOfficeMap, Integer id) {
        final List<RentalOffice> rentalOffices = rentalOfficeMap.get(id);
        for (RentalOffice rentalOffice : rentalOffices) {
            if (id.equals(rentalOffice.getId())) {
                return rentalOffice;
            }
        }
        throw new IllegalArgumentException();
    }

    private Histories search(final List<Integer> answers, final Histories histories,
                             final Map<Integer, List<RentalOffice>> rentalOfficeMap, Integer key, final int rentalTime) {

        final List<RentalOffice> rentalOffices = rentalOfficeMap.get(key);

        for (RentalOffice rentalOffice : rentalOffices) {

            final Histories newHistories = histories.add(rentalOffice.getNextId(), rentalOffice.getMoveTime());
            if (newHistories.compareHistoryTime(rentalTime) == 0) {
                answers.add(newHistories.getLastId());
                continue;
            }

            if (newHistories.compareHistoryTime(rentalTime) == -1) {
                final int nextId = rentalOffice.getNextId();
                final Histories result = search(answers, newHistories, rentalOfficeMap, nextId, rentalTime);
                if (result.isEmpty()) {
                    continue;
                }
                return result;
            }
        }
        return Histories.getEmptyHistories();
    }

    static class Histories {
        private final List<Integer> histories = new ArrayList<>();
        private final BigInteger historyTime;

        public Histories(BigInteger historyTime) {
            this.historyTime = historyTime;
        }

        public Histories(List<Integer> histories, BigInteger historyTime) {
            this.histories.addAll(histories);
            this.historyTime = historyTime;
        }

        public Histories(RentalOffice rentalOffice) {
            this.histories.add(rentalOffice.getId());
            this.historyTime = BigInteger.ZERO;
        }

        public static Histories getEmptyHistories() {
            return new Histories(BigInteger.ZERO);
        }


        public boolean isEmpty() {
            return this.histories.isEmpty();
        }

        public List<Integer> getHistories() {
            return histories;
        }

        public BigInteger getHistoryTime() {
            return historyTime;
        }

        @Override
        public String toString() {
            return "Histories{" +
                    "histories=" + histories +
                    ", historyTime=" + historyTime +
                    '}';
        }

        public int compareHistoryTime(int rentalTime) {
            return this.historyTime.compareTo(BigInteger.valueOf(rentalTime));
        }

        public Histories add(int id, int moveTime) {
            final ArrayList<Integer> histories = new ArrayList<>(this.getHistories());
            histories.add(id);

            return new Histories(histories, this.getHistoryTime().add(BigInteger.valueOf(moveTime)));
        }

        public Integer getLastId() {
            return this.histories.get(this.histories.size() - 1);
        }
    }

    class RentalOffice {
        private final int id;
        private final int nextId;
        private final int moveTime;

        public RentalOffice(int[] road) {
            this.id = road[0];
            this.nextId = road[1];
            this.moveTime = road[2];
        }

        public RentalOffice(int id, int nextId, int moveTime) {
            this.id = id;
            this.nextId = nextId;
            this.moveTime = moveTime;
        }

        public int getId() {
            return id;
        }

        public int getNextId() {
            return nextId;
        }

        public int getMoveTime() {
            return moveTime;
        }

        @Override
        public String toString() {
            return "RentalOffice{" +
                    "id=" + id +
                    ", nextId=" + nextId +
                    ", moveTime=" + moveTime +
                    '}';
        }

        public RentalOffice reverse() {
            return new RentalOffice(nextId, id, moveTime);
        }
    }
}
