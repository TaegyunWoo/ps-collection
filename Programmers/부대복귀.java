import java.util.*;

class Solution {
  List<List<Integer>> graph = new ArrayList<>();

  public int[] solution(int n, int[][] roads, int[] sources, int destination) {
    //Init
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < roads.length; i++) {
      graph.get(roads[i][0]).add(roads[i][1]);
      graph.get(roads[i][1]).add(roads[i][0]);
    }

    //bfs
    int[] answer = new int[sources.length];
    int answerIdx = 0;
    for (int i = 0; i < sources.length; i++) {
      answer[answerIdx++] = bfs(sources[i], destination);
    }

    return answer;
  }

  private int bfs(int start, int destination) {
    Deque<Integer> deque = new ArrayDeque<>();
    int[] countAry = new int[graph.size()];
    Arrays.fill(countAry, -1);

    deque.offerLast(start);
    countAry[start] = 0;

    if (start == destination) return 0;

    while (!deque.isEmpty()) {
      int current = deque.pollFirst();
      List<Integer> nextList = graph.get(current);

      for (int next : nextList) {
        if (countAry[next] != -1) continue;
        countAry[next] = countAry[current] + 1;
        deque.offerLast(next);

        if (next == destination) return countAry[next];
      }
    }

    return -1;
  }
}