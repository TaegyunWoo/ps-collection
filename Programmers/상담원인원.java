import java.util.*;

class Solution {
  static final int INF = (int) 1e9;
  int[] mentoCase;
  int answer = INF;

  public int solution(int k, int n, int[][] reqs) {
    mentoCase = new int[k];
    brute(k, n, reqs, 0);
    return answer;
  }

  private void brute(int k, int n, int[][] reqs, int depth) {
    if (depth == k) {
      //최소 대기시간 구하기
      int result = getMinWaitTime(k, reqs);
      answer = Math.min(answer, result);
      return;
    }

    //남은 멘토를 모두 할당
    if (depth == k-1) {
      mentoCase[depth] = n;
      brute(k, 0, reqs, depth+1);
      return;
    }

    //남은 유형들에 1씩은 분배할 수 있을 정도로만 반복
    for (int i = 1; i <= n - (k - depth) + 1; i++) {
      mentoCase[depth] = i;
      brute(k, n-i, reqs, depth+1);
    }
  }

  private int getMinWaitTime(int k, int[][] reqs) {
    int[][] endTimeAry = new int[k][20]; //endTimeAry[유형][i번째 멘토의 상담끝시각]
    int waitTimeSum = 0;

    //각 참가자마다 반복
    for (int[] req : reqs) {
      int start = req[0];
      int end = req[1];
      int type = req[2] - 1;

      //해당 참가자의 최소 대기시간 구하기
      int minWaitTime = INF;
      int minWaitMentoIdx = 0;
      for (int i = 0; i < mentoCase[type]; i++) {
        int waitTime = endTimeAry[type][i] - start;

        if (waitTime < minWaitTime) {
          minWaitMentoIdx = i;

          if (waitTime <= 0) { //바로 상담할 수 있다면
            minWaitTime = 0;
            break;
          } else { //바로 상담할 수 없다면
            minWaitTime = waitTime;
          }
        }

      }

      //가장 빨리 상담할 수 있는 멘토의 상담 끝시간 갱신
      endTimeAry[type][minWaitMentoIdx] = start + end + minWaitTime;
      //대기시간 총합
      waitTimeSum += minWaitTime;
    }

    return waitTimeSum;
  }
}