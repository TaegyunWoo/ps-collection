import java.util.*;

class Solution {
  PriorityQueue<NumInfo> leftQueue = new PriorityQueue<>();
  PriorityQueue<NumInfo> rightQueue = new PriorityQueue<>();

  public int solution(int[] a) {
    //Init
    for (int i = 0; i < a.length; i++) {
      rightQueue.offer(new NumInfo(a[i], i));
    }

    //계산
    int answer = 0;
    for (int currentIndex = 0; currentIndex < a.length; currentIndex++) {
      NumInfo leftInfo;
      NumInfo rightInfo;

      //rightQueue 업데이트
      while (currentIndex >= rightQueue.peek().index) {
        rightQueue.poll();
        if (rightQueue.isEmpty()) break;
      }

      //큐가 비어있는 경우 (양쪽 끝인 경우)
      if (leftQueue.isEmpty() || rightQueue.isEmpty()) {
        answer++;
        leftQueue.offer(new NumInfo(a[currentIndex], currentIndex));
        continue;
      }

      leftInfo = leftQueue.peek(); //왼쪽의 최소 값
      rightInfo = rightQueue.peek(); //오른쪽의 최소 값

      if (leftInfo.num > a[currentIndex] || rightInfo.num > a[currentIndex]) { //currentIndex 번째 풍선을 유지할 수 있는 경우
        answer++;
      }

      leftQueue.offer(new NumInfo(a[currentIndex], currentIndex));
    }

    return answer;
  }

  class NumInfo implements Comparable<NumInfo> {
    public int num;
    public int index;
    public NumInfo(int num, int index) {
      this.num = num;
      this.index = index;
    }
    @Override
    public int compareTo(NumInfo other) {
      return this.num - other.num;
    }
  }
}