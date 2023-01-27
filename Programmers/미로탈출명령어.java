import java.util.*;

class Solution {
  char[] route;
  int[] dr = {1, 0, 0, -1};
  int[] dc = {0, -1, 1, 0};
  char[] stepAry = {'d', 'l', 'r', 'u'}; //(d, l, r, u) 우선순위
  boolean getAnswer = false;
  String answer;

  public String solution(int n, int m, int x, int y, int r, int c, int k) {
    route = new char[k];

    // (k - 최단거리) 가 0 보다 작다면, 도달할 수 없으므로 return "impossible"
    // (k - 최단거리) 가 0 보다 크고, 홀수라면 k번 이동해서 도달할 수 없으므로 return "impossible"
    int left = k - (Math.abs(x - r) + Math.abs(y - c));
    if (left < 0 || left % 2 != 0) return "impossible";

    backtracking(n, m, x-1, y-1, r-1, c-1, k, 0);

    return answer == null ? "impossible" : answer;
  }

  private void backtracking(int n, int m, int x, int y, int r, int c, int k, int depth) {
    if (getAnswer) return; //정답을 구한 경우 (d,l,r,u 순서대로 경로를 탐색하므로 가장 먼저 구한 경로가 정답)
    if (k - depth < Math.abs(x - r) + Math.abs(y - c)) { //남은 k번 이동으로 도달할 수 없는 경우
      return;
    }
    if (depth == k && x == r && y == c) { //k번째 이동에서 (r,c)에 도착한 경우
      answer = String.valueOf(route);
      getAnswer = true;
      return;
    }

    for (int i = 0; i < 4; i++) {
      int nextX = x + dr[i];
      int nextY = y + dc[i];
      char step = stepAry[i];

      if (nextX < 0 || nextX >= n) continue;
      if (nextY < 0 || nextY >= m) continue;

      route[depth] = step;
      backtracking(n, m, nextX, nextY, r, c, k, depth + 1);
    }
  }
}