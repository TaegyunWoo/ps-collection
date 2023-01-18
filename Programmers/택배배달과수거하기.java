class Solution {
  public long solution(int cap, int n, int[] deliveries, int[] pickups) {
    long answer = 0;

    int accumulateD = 0; //배달해야하는 박스 개수
    int accumulateP = 0; //픽업해야하는 박스 개수

    //가장 먼 집부터 완료시키는 것이 목표
    for (int i = n - 1; i >= 0; i--) {
      int distance = i + 1;
      accumulateD += deliveries[i]; //배달해야하는 박스 개수 추가
      accumulateP += pickups[i]; //픽업해야하는 박스 개수 추가

      /*
       * 배달/픽업 해야하는 박스가 남지 않을 때까지 반복 (센터 왕복 작업)
       * 배달과 픽업을 동시에 하므로 'or' 조건
       */
      while (accumulateD > 0 || accumulateP > 0) {
        accumulateD -= cap; //음수를 허용하여, 남은 공간은 앞집에서 사용할 수 있도록 처리
        accumulateP -= cap; //음수를 허용하여, 남은 공간은 앞집에서 사용할 수 있도록 처리
        answer += distance * 2; //센터를 들렸으므로 거리 더하기
      }
    }

    return answer;
  }
}