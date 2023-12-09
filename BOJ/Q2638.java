import java.io.*;
import java.util.*;

public class Q2638 {
  static final int[] dr = {-1, 1, 0, 0};
  static final int[] dc = {0, 0, -1, 1};
  static int n, m;
  static int[][] board;
  static boolean[][] visited;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    n = Integer.parseInt(tmp[0]);
    m = Integer.parseInt(tmp[1]);
    board = new int[n][m];
    for (int i = 0; i < n; i++) {
      tmp = br.readLine().split(" ");
      for (int u = 0; u < m; u++) {
        board[i][u] = Integer.parseInt(tmp[u]);
      }
    }

    //solution
    int times = 0;
    while (true) {
      boolean doMore = bfs();
      for (int i = 0; i < n; i++) {
        System.out.println(Arrays.toString(board[i]));
      }
      System.out.println();
      times++;
      if (!doMore) break;
    }

    //print answer
    System.out.println(times-1);
  }

  private static boolean bfs() {
    Deque<int[]> deque = new ArrayDeque<>();
    Deque<int[]> disapearDeque = new ArrayDeque<>();
    boolean[][] visited = new boolean[n][m];

    deque.offerLast(new int[] {0, 0});
    visited[0][0] = true;

    while (!deque.isEmpty()) {
      int[] current = deque.pollFirst();
      int currentR = current[0];
      int currentC = current[1];

      for (int i = 0; i < 4; i++) {
        int nextR = currentR + dr[i];
        int nextC = currentC + dc[i];

        if (nextR < 0 || nextR >= n) continue;
        if (nextC < 0 || nextC >= m) continue;
        if (visited[nextR][nextC]) continue;

        int[] next = {nextR, nextC};
        visited[nextR][nextC] = true;

        if (board[nextR][nextC] == 1) {
          disapearDeque.offerLast(next);
        } else {
          deque.offerLast(next);
        }
      }
    }

    if (disapearDeque.isEmpty()) return false;

    //치즈 지우기
    int size = disapearDeque.size();
    for (int i = 0; i < size; i++) {
      int[] point = disapearDeque.pollFirst();
      int countAir = 0;
      for (int u = 0; u < 4; u++) {
        int arroundR = point[0] + dr[u];
        int arroundC = point[1] + dc[u];
        if (visited[arroundR][arroundC] && board[arroundR][arroundC] == 0) countAir++;
      }
      if (countAir >= 2) {
        disapearDeque.offerLast(point);
      }
    }
    while (!disapearDeque.isEmpty()) {
      int[] point = disapearDeque.pollFirst();
      board[point[0]][point[1]] = 0;
    }

    return true;
  }
}