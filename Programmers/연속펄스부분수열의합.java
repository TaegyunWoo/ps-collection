import java.util.*;

class Solution {
  // infoAry[a][0][0] = 0~a까지의 연속 펄스 부분 수열의 원소 총합 (+로 시작), infoAry[a][0] = 0~a까지의 연속 펄스 부분 수열의 원소 총합 (-로 시작)
  // infoAry[a][b][1] = 곱한 펄스 수열의 마지막 부호
  long[][][] infoAry;

  public long solution(int[] sequence) {
    //init
    infoAry = new long[sequence.length][2][2];

    //info set
    infoAry[0][0][0] = sequence[0]; infoAry[0][0][1] = 1;
    infoAry[0][1][0] = sequence[0] * -1; infoAry[0][1][1] = -1;
    for (int i = 1; i < sequence.length; i++) {
      infoAry[i][0][1] = infoAry[i-1][0][1] * -1;
      infoAry[i][0][0] = infoAry[i-1][0][0] + (sequence[i] * infoAry[i][0][1]);
      infoAry[i][1][1] = infoAry[i-1][1][1] * -1;
      infoAry[i][1][0] = infoAry[i-1][1][0] + (sequence[i] * infoAry[i][1][1]);
    }

    //정답 구하기
    long answer = 0;
    long max1 = -50000000001L;
    long min1 = 50000000001L;
    long max2 = -50000000001L;
    long min2 = 50000000001L;
    for (int i = 0; i < infoAry.length; i++) {
      max1 = Math.max(max1, infoAry[i][0][0]);
      min1 = Math.min(min1, infoAry[i][0][0]);
      max2 = Math.max(max2, infoAry[i][1][0]);
      min2 = Math.min(min2, infoAry[i][1][0]);
    }
    if (min1 > 0) min1 = 0;
    if (min2 > 0) min2 = 0;
    answer = Math.max(max1 - min1, max2 - min2);

    return answer;
  }
}