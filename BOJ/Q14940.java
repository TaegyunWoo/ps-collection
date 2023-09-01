import java.util.*;
import java.io.*;

public class Q14940 {
  static final int[] dr = {-1, 1, 0, 0};
  static final int[] dc = {0, 0, -1, 1};
  static int n; //가로
  static int m; //세로
  static int[][] map;
  static int[][] count;
  static int startR;
  static int startC;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    n = Integer.parseInt(tmp[0]);
    m = Integer.parseInt(tmp[1]);
    map = new int[n][m];
    for (int i = 0; i < n; i++) {
      tmp = br.readLine().split(" ");
      for (int u = 0; u < m; u++) {
        map[i][u] = Integer.parseInt(tmp[u]);
      }
    }
    count = new int[n][m];
    for (int i = 0; i < n; i++) {
      Arrays.fill(count[i], -1);
    }
    for (int i = 0; i < n; i++) {
      for (int u = 0; u < m; u++) {
        if (map[i][u] == 0) {
          count[i][u] = 0;
        } else if (map[i][u] == 2) {
          startR = i;
          startC = u;
        }
      }
    }

    //solution
    bfs(startR, startC);

    //print answer
    StringBuilder answer = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int u = 0; u < m; u++) {
        answer.append(count[i][u]);
        answer.append(" ");
      }
      answer.append("\n");
    }
    System.out.println(answer.toString());
  }

  private static void bfs(int startR, int startC) {
    Deque<int[]> deque = new ArrayDeque<>();
    deque.offerLast(new int[] {startR, startC});
    count[startR][startC] = 0;

    while (!deque.isEmpty()) {
      int[] current = deque.pollFirst();
      int currentR = current[0];
      int currentC = current[1];

      for (int i = 0; i < 4; i++) {
        int nextR = currentR + dr[i];
        int nextC = currentC + dc[i];

        if (nextR < 0 || nextR >= n) continue;
        if (nextC < 0 || nextC >= m) continue;
        if (count[nextR][nextC] != -1) continue;

        count[nextR][nextC] = count[currentR][currentC] + 1;
        deque.offerLast(new int[] {nextR, nextC});
      }
    }
  }
}