import java.util.*;

class Solution {
  ArrayList<ArrayList<Integer>> graph;
  boolean[] visited;

  public int solution(int n, int[][] wires) {
    int answer = (int) 1e9;

    //graph 초기화
    graph = new ArrayList<>();
    for (int i = 0; i < n + 1; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < wires.length; i++) {
      int a = wires[i][0];
      int b = wires[i][1];
      graph.get(a).add(b); //양방향
      graph.get(b).add(a); //양방향
    }

    //visited 초기화
    visited = new boolean[n+1];

    //간선 하나씩 끊어서 확인
    for (int i = 0; i < wires.length; i++) {
      int a = wires[i][0];
      int b = wires[i][1];

      //끊기
      graph.get(a).remove(Integer.valueOf(b));
      graph.get(b).remove(Integer.valueOf(a));

      //차이 구하기
      int gap = bfs(1);
      for (int u = 1; u < n+1; u++) {
        if (visited[u]) continue;
        gap -= bfs(u);
        break;
      }
      gap = Math.abs(gap);
      answer = Math.min(answer, gap);

      //다시 연결
      graph.get(a).add(b);
      graph.get(b).add(a);

      //visited 다시 초기화
      visited = new boolean[n+1];
    }

    return answer;
  }

  private int bfs(int startIndex) {
    int nodeCount = 1;
    Deque<Integer> deque = new ArrayDeque<>();
    deque.offerLast(startIndex);
    visited[startIndex] = true;

    while (!deque.isEmpty()) {
      int currentIndex = deque.pollFirst();
      ArrayList<Integer> nearNodeList = graph.get(currentIndex);

      for (int nearNodeIndex : nearNodeList) {
        if (visited[nearNodeIndex]) continue;
        visited[nearNodeIndex] = true;
        deque.offerLast(nearNodeIndex);
        nodeCount++;
      }
    }

    return nodeCount;
  }
}