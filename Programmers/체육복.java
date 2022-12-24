import java.util.*;

class Solution {
  HashMap<Integer, Integer> studentMap = new HashMap<>();

  public int solution(int n, int[] lost, int[] reserve) {
    for (int i = 1; i <= n; i++) {
      studentMap.put(i, 1);
    }
    for (int i = 0; i < lost.length; i++) {
      studentMap.put(lost[i], studentMap.get(lost[i]) - 1);
    }
    for (int i = 0; i < reserve.length; i++) {
      studentMap.put(reserve[i], studentMap.get(reserve[i]) + 1);
    }

    for (int i = 1; i <= n; i++) {
      int currentClothes = studentMap.get(i);
      if (currentClothes != 2) continue;
      if (studentMap.containsKey(i-1)) {
        if (studentMap.get(i-1) == 0) {
          studentMap.put(i-1, 1);
          studentMap.put(i, 1);
          continue;
        }
      }
      if (studentMap.containsKey(i+1)) {
        if (studentMap.get(i+1) == 0) {
          studentMap.put(i+1, 1);
          studentMap.put(i, 1);
        }
      }
    }

    int answer = 0;
    for (int i = 1; i <= n; i++) {
      if (studentMap.get(i) != 0) answer++;
    }

    return answer;
  }
}