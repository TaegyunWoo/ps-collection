import java.util.*;

class Solution {
  HashSet<String> kindOfGem = new HashSet<>();
  int maxRangeSize = (int) 1e9;

  public int[] solution(String[] gems) {
    initKindOfGem(gems);

    int minStart = 0;
    int[] answer = null;
    while (true) {
      int[] range = getRange(minStart, gems);

      if (range == null) break;

      if (maxRangeSize > range[1] - range[0]) {
        maxRangeSize = range[1] - range[0];
        answer = range;
      }

      minStart = range[0] + 1;
    }

    answer[0]++;
    answer[1]++;

    return answer;
  }

  private void initKindOfGem(String[] gems) {
    for (String gem : gems) {
      kindOfGem.add(gem);
    }
  }

  private int[] getRange(int minStart, String[] gems) {
    HashMap<String, Integer> gemCountMap = new HashMap<>();
    int left = minStart;
    int right = left;

    //끝점을 늘려가며 조건 확인
    while (true) {
      if (right >= gems.length) return null;

      String gem = gems[right];

      if (gemCountMap.containsKey(gem)) {
        gemCountMap.put(gem, gemCountMap.get(gem) + 1);
      } else {
        gemCountMap.put(gem, 1);
      }

      //조건을 만족한다면
      if (gemCountMap.size() == kindOfGem.size()) break;

      right++;
    }

    //시작점을 줄여가며 조건 확인
    while (true) {
      if (left >= gems.length) return null;

      String gem = gems[left];

      //조건을 만족하지 않는다면
      if (gemCountMap.size() != kindOfGem.size()) {
        left--;
        break;
      }

      if (gemCountMap.get(gem) == 1) {
        gemCountMap.remove(gem);
      } else {
        gemCountMap.put(gem, gemCountMap.get(gem) - 1);
      }

      left++;
    }

    return new int[] {left, right};
  }
}