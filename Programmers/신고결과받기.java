import java.util.*;

class Solution {
  HashMap<String, HashSet<String>> reportMap = new HashMap<>();
  HashMap<String, Integer> reportCountMap = new HashMap<>();

  public int[] solution(String[] id_list, String[] report, int k) {
    //reportMap 초기화
    initReportMap(id_list);
    //reportCountMap 초기화
    initReportCountMap(id_list);

    //신고처리
    reportAll(report);
    //정지된 id 집합 구하기
    HashSet<String> bannedIdSet = getBannedId(k);

    //각각의 '신고한 id 집합'과 '정지된 id 집합'의 교집합 크기 구하기
    HashMap<String, Integer> answerMap = new HashMap<>();
    for (String key : reportMap.keySet()) {
      int count = getIntersectionSize(reportMap.get(key), bannedIdSet);
      answerMap.put(key, count);
    }

    //정답출력
    int[] answer = new int[id_list.length];
    int idx = 0;
    for (String id : id_list) {
      answer[idx++] = answerMap.get(id);
    }

    return answer;
  }

  private void initReportMap(String[] id_list) {
    for (String id : id_list) {
      reportMap.put(id, new HashSet<String>());
    }
  }

  private void initReportCountMap(String[] id_list) {
    for (String id : id_list) {
      reportCountMap.put(id, 0);
    }
  }

  private void reportAll(String[] reportAry) {
    for (String report : reportAry) {
      String[] parsed = report.split(" ");
      String reporter = parsed[0];
      String reported = parsed[1];

      boolean addSucceed = reportMap.get(reporter).add(reported); //신고자와 피신고자 추가

      if (!addSucceed) continue; //이미 신고했던 사람이라면

      reportCountMap.put(reported, reportCountMap.get(reported) + 1); //신고 횟수 추가
    }
  }

  private HashSet<String> getBannedId(int k) {
    HashSet<String> bannedIdSet = new HashSet<>();
    for (String id : reportCountMap.keySet()) {
      if (reportCountMap.get(id) < k) continue;
      bannedIdSet.add(id);
    }
    return bannedIdSet;
  }

  private int getIntersectionSize(HashSet<String> a, HashSet<String> b) {
    int count = 0;
    for (String item : a) {
      if (b.contains(item)) count++;
    }
    return count;
  }
}