import java.util.*;

class Solution {
  int[] start;
  int[] end;
  int[] lever;
  int[][] countAry;
  int[] dr = {-1, 1, 0, 0};
  int[] dc = {0, 0, -1, 1};

  public int solution(String[] maps) {
    //Init
    for (int i = 0; i < maps.length; i++) {
      for (int u = 0; u < maps[i].length(); u++) {
        if (maps[i].charAt(u) == 'S') start = new int[] {i, u};
        if (maps[i].charAt(u) == 'E') end = new int[] {i, u};
        if (maps[i].charAt(u) == 'L') lever = new int[] {i, u};
      }
    }

    int answer = 0;

    //BFS : Start ~ Lever
    bfs(start, lever, maps);
    if (countAry[lever[0]][lever[1]] == -1) return -1; //레버에 도달할 수 없다면

    answer = countAry[lever[0]][lever[1]];

    //BFS : Lever ~ End
    bfs(lever, end, maps);
    if (countAry[end[0]][end[1]] == -1) return -1; //출구에 도달할 수 없다면

    answer += countAry[end[0]][end[1]];

    return answer;
  }

  private void bfs(int[] start, int[] end, String[] maps) {
    //Init countAry
    countAry = new int[maps.length][maps[0].length()];
    for (int i = 0; i < countAry.length; i++) {
      Arrays.fill(countAry[i], -1);
    }

    Deque<int[]> deque = new ArrayDeque<>();
    deque.offerLast(start);
    countAry[start[0]][start[1]] = 0;

    while (!deque.isEmpty()) {
      int[] current = deque.pollFirst();

      for (int i = 0; i < 4; i++) {
        int nextR = current[0] + dr[i];
        int nextC = current[1] + dc[i];

        if (nextR < 0 || nextR >= maps.length) continue;
        if (nextC < 0 || nextC >= maps[0].length()) continue;
        if (countAry[nextR][nextC] != -1) continue;
        if (maps[nextR].charAt(nextC) == 'X') continue;

        countAry[nextR][nextC] = countAry[current[0]][current[1]] + 1;
        deque.offerLast(new int[] {nextR, nextC});

        if (nextR == end[0] && nextC == end[1]) return;
      }
    }
  }
}