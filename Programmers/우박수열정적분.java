import java.util.*;

class Solution {
  double[] sumAry;

  public double[] solution(int k, int[][] ranges) {
    //우박 수열 구하기
    List<Integer> collatzList = getCollatzList(k);

    //누적합 배열 초기화 (sum[a] = [a-1, a] 구간의 넓이)
    sumAry = new double[collatzList.size()];
    for (int i = 1; i < sumAry.length; i++) {
      sumAry[i] = (double) Math.min(collatzList.get(i-1), collatzList.get(i));
      sumAry[i] += (double) Math.abs(collatzList.get(i) - collatzList.get(i-1)) / 2;
    }

    //누적합
    for (int i = 2; i < sumAry.length; i++) {
      sumAry[i] += sumAry[i-1];
    }

    //정답
    double[] answer = new double[ranges.length];
    int answerIdx = 0;
    for (int[] range : ranges) {
      int start = range[0];
      int end = sumAry.length + range[1] - 1;
      if (start > end) answer[answerIdx++] = -1;
      else answer[answerIdx++] = sumAry[end] - sumAry[start];
    }

    return answer;
  }

  private List<Integer> getCollatzList(int k) {
    List<Integer> result = new ArrayList<>();

    while (k != 1) {
      result.add(k);
      if (k % 2 == 0) k /= 2;
      else k = (k * 3) + 1;
    }

    result.add(1);

    return result;
  }
}