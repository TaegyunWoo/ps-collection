import java.util.*;

class Solution {
  static final int INF = (int) 1e9;
  int n;
  int[] weak;
  int[] dist;
  boolean[] visited;
  HashSet<Integer> weakPointSet = new HashSet<>();
  int answer = INF;

  public int solution(int n, int[] weak, int[] dist) {
    //init
    this.n = n;
    this.weak = weak;
    this.dist = new int[dist.length];
    Arrays.sort(dist);
    for (int i = 0; i < dist.length; i++) {
      this.dist[i] = dist[dist.length - i - 1];
    }
    visited = new boolean[n];
    for (int w : weak) {
      weakPointSet.add(w);
    }

    //solution
    dfs(0, 0);

    return answer == INF ? -1 : answer;
  }

  private void dfs(int depth, int checkedWeak) {
    if (checkedWeak == weakPointSet.size()) {
      answer = Math.min(answer, depth);
      return;
    }
    if (depth == dist.length) return;

    for (int i = 0; i < weak.length; i++) {
      int startPoint = weak[i];
      int visitedPoint = startPoint;
      int countMove = 0;
      int countCheckWeak = 0;

      if (visited[startPoint]) continue;

      for (int u = 0; u <= dist[depth]; u++) {
        if (visited[visitedPoint]) break; //이미 방문한 지점이라면
        if (weakPointSet.contains(visitedPoint)) countCheckWeak++; //방문할 곳이 취약지점이라면
        visited[visitedPoint] = true;
        visitedPoint = (visitedPoint + 1) % n;
        countMove++;
      }

      dfs(depth+1, checkedWeak + countCheckWeak); //다음 친구 호출

      //visited 복구
      while (countMove-- > 0) {
        visitedPoint = (visitedPoint == 0) ? n - 1 : visitedPoint - 1;
        visited[visitedPoint] = false;
      }
    } //밖 for문 종료

  }
}