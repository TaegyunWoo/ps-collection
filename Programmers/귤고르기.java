import java.util.*;

class Solution {
  HashMap<Integer, Integer> map = new HashMap<>();
  PriorityQueue<Info> queue = new PriorityQueue<>();

  public int solution(int k, int[] tangerine) {
    for (int t : tangerine) {
      if (map.containsKey(t)) {
        map.put(t, map.get(t)+1);
      } else {
        map.put(t, 1);
      }
    }

    for (int size : map.keySet()) {
      queue.offer(new Info(size, map.get(size)));
    }

    int answer = 0;
    while (k > 0) {
      k -= queue.poll().count;
      answer++;
    }

    return answer;
  }

  class Info implements Comparable<Info> {
    public int size;
    public int count;
    public Info(int s, int c) {
      this.size = s;
      this.count = c;
    }
    @Override
    public int compareTo(Info other) {
      return other.count - this.count;
    }
  }
}