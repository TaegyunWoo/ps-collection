import java.io.*;
import java.util.*;

public class Q4485 {
  static final int MAX = (int) 1e9;
  static final int[] dr = {-1, 1, 0, 0};
  static final int[] dc = {0, 0, -1, 1};
  static int n;
  static int[][] map;
  static int[][] loseCount;
  static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = 0;

    while (true) {
      //init
      t++;
      n = Integer.parseInt(br.readLine());
      if (n == 0) break;
      map = new int[n][n];
      loseCount = new int[n][n];
      for (int i = 0; i < n; i++) {
        String[] tmp = br.readLine().split(" ");
        for (int u = 0; u < n; u++) {
          map[i][u] = Integer.parseInt(tmp[u]);
        }
      }
      for (int i = 0; i < n; i++) {
        Arrays.fill(loseCount[i], MAX);
      }

      bfs();

      answer.append("Problem " + t + ": " + loseCount[n-1][n-1] + "\n");
    }

    //print answer
    System.out.println(answer.toString());
  }

  private static void bfs() {
    Deque<int[]> deque = new ArrayDeque<>();
    deque.offerLast(new int[] {0, 0});
    loseCount[0][0] = map[0][0];

    while (!deque.isEmpty()) {
      int[] current = deque.pollFirst();
      int currentR = current[0];
      int currentC = current[1];

      for (int i = 0; i < 4; i++) {
        int nextR = currentR + dr[i];
        int nextC = currentC + dc[i];

        if (nextR < 0 || nextR >= n) continue;
        if (nextC < 0 || nextC >= n) continue;

        int nextLose = loseCount[currentR][currentC] + map[nextR][nextC];
        if (loseCount[nextR][nextC] <= nextLose) continue;

        deque.offerLast(new int[] {nextR, nextC});
        loseCount[nextR][nextC] = nextLose;
      }
    }
  }
}