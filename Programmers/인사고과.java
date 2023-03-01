import java.util.*;

class Solution {
  int answer = 1;

  public int solution(int[][] scores) {
    int[] target = scores[0];

    sort(scores);

    int beforeB = -1;
    for (int[] score : scores) {
      int sum = score[0] + score[1];
      if (beforeB <= score[1]) {
        beforeB = score[1];

        if (target[0] + target[1] < sum) answer++;

      } else {
        if (target.equals(score)) return -1;
      }
    }

    return answer;
  }

  private void sort(int[][] scores) {
    Arrays.sort(scores, (a, b) -> {
      if (a[0] == b[0]) return a[1] - b[1];
      return b[0] - a[0];
    });
  }
}