/**
 * idea : 가장 가벼운 사람과 가장 무거운 사람이 함께 탄다.
 *        만약 limit을 초과해서 함께 타지 못하는 경우, 가장 무거운 사람만 혼자 탄다.
 */

import java.util.*;

class Solution {
  public int solution(int[] people, int limit) {
    int answer = 0;

    //정렬
    Arrays.sort(people);

    //계산
    int frontPoint = 0;
    int rearPoint = people.length - 1;
    while (frontPoint <= rearPoint) {
      int weightSum = people[frontPoint] + people[rearPoint];

      if (frontPoint == rearPoint) {
        answer++;
        break;
      }

      if (weightSum > limit) { // (가장 가벼운 무게 + 가장 무거운 무게) > limit 이면
        //무거운 사람 혼자 타기
        rearPoint--;
        answer++;

      } else { // (가장 가벼운 무게 + 가장 무거운 무게) <= limit 이면
        //같이 타기
        frontPoint++;
        rearPoint--;
        answer++;
      }
    }

    return answer;
  }
}