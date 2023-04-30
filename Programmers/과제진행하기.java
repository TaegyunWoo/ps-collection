import java.util.*;

class Solution {
  String[] answer;
  int answerIdx = 0;

  public String[] solution(String[][] plans) {
    answer = new String[plans.length];

    //정렬
    Arrays.sort(plans, (a, b) -> getTimeSub(a[1], b[1]));

    //계산
    Deque<String[]> delayedPlanStack = new ArrayDeque<>();
    String[] current = plans[0];
    int nowTime = getIntTime(current[1]);

    for (int i = 1; i < plans.length; i++) {
      String[] next = plans[i];

      if (getIntTime(next[1]) > nowTime + Integer.parseInt(current[2])) {
        answer[answerIdx++] = current[0];

        if (delayedPlanStack.isEmpty()) {
          nowTime = getIntTime(next[1]);
          current = next;
        } else {
          nowTime += Integer.parseInt(current[2]);
          current = delayedPlanStack.pollLast();
          i--;
        }

      } else if (getIntTime(next[1]) == nowTime + Integer.parseInt(current[2])) {
        answer[answerIdx++] = current[0];
        current = next;
        nowTime = getIntTime(next[1]);

      } else {
        int playableTime = getIntTime(next[1]) - nowTime;
        current[2] = String.valueOf(Integer.parseInt(current[2]) - playableTime);
        delayedPlanStack.offerLast(current);
        current = next;
        nowTime = getIntTime(next[1]);
      }
    }

    //가장 마지막 과제 수행
    answer[answerIdx++] = plans[plans.length-1][0];

    //stack 비우기
    while (!delayedPlanStack.isEmpty()) {
      answer[answerIdx++] = delayedPlanStack.pollLast()[0];
    }

    return answer;
  }

  private int getTimeSub(String timeA, String timeB) {
    return getIntTime(timeA) - getIntTime(timeB);
  }

  private int getIntTime(String time) {
    String[] timeAry = time.split(":");
    return Integer.parseInt(timeAry[0]) * 60 + Integer.parseInt(timeAry[1]);
  }
}