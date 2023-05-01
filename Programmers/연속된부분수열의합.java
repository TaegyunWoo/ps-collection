import java.util.*;

class Solution {
  List<int[]> answerList = new ArrayList<>();

  public int[] solution(int[] sequence, int k) {
    //누적합 배열
    long[] sum = new long[sequence.length];
    for (int i = 0; i < sequence.length; i++) {
      sum[i] = sequence[i];
    }
    for (int i = 1; i < sequence.length; i++) {
      sum[i] += sum[i-1];
    }

    //가능한 수열 저장
    int start = -1;
    for (int end = 0; end < sequence.length; end++) {
      long result;
      if (start == -1) result = sum[end];
      else result = sum[end] - sum[start];

      if (result > k) {
        start++;
        while (sum[end] - sum[start] > k) {
          start++;
        }
        if (sum[end] - sum[start] == k) {
          answerList.add(new int[] {start, end});
        }
      } else if (result == k) {
        answerList.add(new int[] {start, end});
      }
    }

    //수열 중 정답 찾기
    answerList.sort((a, b) -> {
      if (a[1] - a[0] == b[1] - b[0]) return a[0] - b[0];
      return (a[1] - a[0]) - (b[1] - b[0]);
    });

    int[] answer = answerList.get(0);
    answer[0]++; //start는 1 더한 것이 정답
    return answer;
  }
}