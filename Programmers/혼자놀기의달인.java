import java.util.*;

class Solution {
  PriorityQueue<Integer> bigSizeGroupQueue = new PriorityQueue<>(Collections.reverseOrder());

  public int solution(int[] cards) {
    boolean[] visited = new boolean[cards.length];

    for (int boxNum = 0; boxNum < cards.length; boxNum++) {
      if (visited[boxNum]) continue;
      int groupSize = getGroupSize(cards, visited, boxNum);
      bigSizeGroupQueue.offer(groupSize);
    }

    if (bigSizeGroupQueue.size() == 1) return 0;

    return bigSizeGroupQueue.poll() * bigSizeGroupQueue.poll();
  }

  private int getGroupSize(int[] cards, boolean[] visited, int currentBoxNum) {
    int size = 1;
    visited[currentBoxNum] = true;

    while (true) {
      int nextBoxNum = cards[currentBoxNum] - 1;
      if (visited[nextBoxNum]) break;
      visited[nextBoxNum] = true;
      currentBoxNum = nextBoxNum;
      size++;
    }

    return size;
  }
}