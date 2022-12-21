import java.util.*;

class Solution {
  ArrayList<Integer> answer = new ArrayList<>();
  int[] dayAry;

  public int[] solution(int[] progresses, int[] speeds) {
    dayAry = new int[progresses.length];
    for (int i = 0; i < progresses.length; i++) {
      double tmp = 100 - progresses[i];
      dayAry[i] = (int) Math.ceil(tmp / speeds[i]);
    }

    for (int i = 0; i < dayAry.length; i++) {
      int count = 1;
      int day = dayAry[i];
      if (day == -1) continue;

      for (int u = i + 1; u < dayAry.length; u++) {
        if (dayAry[u] == -1) continue;
        if (day < dayAry[u]) break;
        count++;
        dayAry[u] = -1;
      }
      answer.add(count);
    }

    return answer.stream().mapToInt(i -> i).toArray();
  }
}