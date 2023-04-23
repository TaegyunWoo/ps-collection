import java.util.*;

class Solution {
  HashMap<Integer, Integer> answerMap = new HashMap<>();

  public int[] solution(int e, int[] sAry) {
    //모든 약수 개수 구하기
    int[] countDivisorAry = new int[e+1];
    for (int i = 1; i <= e; i++) {
      for (int u = 1; u <= e/i; u++) {
        countDivisorAry[i*u]++;
      }
    }

    //Sort desc
    Integer[] starts = new Integer[sAry.length];
    for (int i = 0; i < sAry.length; i++) {
      starts[i] = sAry[i];
    }
    Arrays.sort(starts, Comparator.reverseOrder());

    //계산
    int from = e;
    int maxCount = -1;
    int maxNum = -1;

    for (int i = 0; i < starts.length; i++) {
      int s = starts[i];

      //(from ~ s)까지의 약수 개수 구하기
      for (int u = from; u >= s; u--) {
        if (maxCount <= countDivisorAry[u]) {
          maxCount = countDivisorAry[u];
          maxNum = u;
        }
      }

      //정답 저장
      answerMap.put(s, maxNum);

      //from 업데이트
      from = s - 1;
    }

    //정답 구하기
    int[] answer = new int[sAry.length];
    int answerIdx = 0;
    for (int i = 0; i < sAry.length; i++) {
      answer[answerIdx++] = answerMap.get(sAry[i]);
    }

    return answer;
  }
}