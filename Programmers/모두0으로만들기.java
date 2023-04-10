import java.util.*;

class Solution {
  static long[] a;
  static List<List<Integer>> graph = new ArrayList<>();
  static boolean[] visited;
  static long count = 0;
  static long sum = 0;
  static long value = 0;

  public long solution(int[] originA, int[][] edges) {
    //to long
    a = new long[originA.length];
    for (int i = 0; i < a.length; i++) {
      a[i] = originA[i];
    }

    //graph 초기화
    for (int i = 0; i < a.length; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < edges.length; i++) {
      graph.get(edges[i][0]).add(edges[i][1]);
      graph.get(edges[i][1]).add(edges[i][0]); //양방향
    }
    visited = new boolean[a.length];

    //가능 여부 판별
    for (int i = 0; i < a.length; i++)
      sum += a[i];
    if (sum != 0) return -1;

    //계산
    dfs(0, a);

    return count;
  }

  private long dfs(int current, long[] a) {
    List<Integer> nextList = graph.get(current);

    visited[current] = true;

    for (int i = 0; i < nextList.size(); i++) {
      int next = nextList.get(i);

      if (visited[next]) continue;

      value = dfs(next, a);

      a[current] += value;

      count += Math.abs(value);
    }

    return a[current];
  }
}