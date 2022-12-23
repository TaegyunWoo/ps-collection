import java.util.*;

class Solution {
  PriorityQueue<Integer> queue = new PriorityQueue<>();

  public int solution(int[] scoville, int K) {
    int count = 0;

    //PriorityQueue 초기화
    for (int i = 0; i < scoville.length; i++) {
      queue.offer(scoville[i]);
    }

    //계산
    while (true) {
      int smallestScoville = queue.poll();
      if (smallestScoville >= K) { //가장 작은 값이 k 이상인 경우
        return count;
      }
      if (queue.isEmpty()) { //가장 작은 값이 k 미만이고 우선순위 큐가 빈 경우
        return -1;
      }
      int nextScoville = queue.poll();
      queue.offer(smallestScoville + nextScoville * 2);
      count++;
    }
  }
}