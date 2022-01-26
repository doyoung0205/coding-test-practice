package me.doyoung.programmer.problem220126;

class Solution {
    private static final String REPORT_REGEX = " ";

    public int[] solution(final String[] idList, final String[] reports,
                          final int flagCount) {
        final int[] answer = new int[idList.length];
        final String[][] reportHistories = new String[idList.length][0];

        for (final String report : reports) {
            final String[] reportInfo = report.split(REPORT_REGEX);
            final String target = reportInfo[1];
            final String reporter = reportInfo[0];

            int idIndex = indexOf(idList, target);
            final String[] reportHistory = reportHistories[idIndex];
            if (!contains(reportHistory, reporter)) {
                reportHistories[idIndex] = add(reportHistory, reporter);
            }
        }

        for (final String[] reportHistory : reportHistories) {
            if (reportHistory.length >= flagCount) {
                for (String reporter : reportHistory) {
                    final int idIndex = indexOf(idList, reporter);
                    answer[idIndex] = answer[idIndex] + 1;
                }
            }
        }

        return answer;
    }

    private String[] add(final String[] stringArray, final String target) {
        final int length = stringArray.length;
        final String[] newArray = new String[length + 1];
        System.arraycopy(stringArray, 0, newArray, 0, length);
        newArray[length] = target;
        return newArray;
    }


    private boolean contains(final String[] stringArray, final String target) {
        for (final String string : stringArray) {
            if (string != null && string.equals(target)) {
                return true;
            }
        }
        return false;
    }

    private int indexOf(final String[] idList, final String target) {
        for (int index = 0; index < idList.length; index++) {
            final String id = idList[index];
            if (id != null && id.equals(target)) {
                return index;
            }
        }
        throw new IllegalArgumentException("해당 타겟을 찾지 못하였습니다.");
    }
}

//    public int[] solution(String[] idList, String[] reports, int flagCount) {
//        int[] answer = new int[idList.length];
//
//        final Map<String, Set<String>> reportTargetMap = new HashMap<>();
//        final Map<String, Integer> idMap = new HashMap<>();
//        for (String id : idList) {
//            idMap.put(id, 0);
//        }
//
//        for (String report : reports) {
//            final String[] reportInfo = report.split(REGEX);
//            final String target = reportInfo[1];
//            final String reporter = reportInfo[0];
//            if (!reportTargetMap.containsKey(target)) {
//                reportTargetMap.put(target, Set.of(reporter));
//            }
//            final Set<String> reporterSet = new HashSet<>(reportTargetMap.get(target));
//            reporterSet.add(reporter);
//            reportTargetMap.put(target, reporterSet);
//        }
//
//        for (final Map.Entry<String, Set<String>> target : reportTargetMap.entrySet()) {
//            final Set<String> reporterSet = target.getValue();
//            if (reporterSet.size() >= flagCount) {
//                for (final String reporter : reporterSet) {
//                    idMap.put(reporter, idMap.get(reporter) + 1);
//                }
//            }
//        }
//
//        for (int i = 0; i < idList.length; i++) {
//            final Integer reportCount = idMap.get(idList[i]);
//            answer[i] = reportCount;
//        }
//
//        return answer;
//    }
