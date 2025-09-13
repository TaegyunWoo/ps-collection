import java.io.*;
import java.util.*;

public class Q2589 {
  private static int[] dr = {-1, 1, 0, 0};
  private static int[] dc = {0, 0, -1, 1};
  private static int height;
  private static int width;
  private static char[][] map;
  private static boolean[][] visited;
  private static int answer = 0;

  public static void main(String[] args) throws IOException {
    //초기화
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    height = Integer.parseInt(tmp[0]);
    width = Integer.parseInt(tmp[1]);
    map = new char[height][width];
    visited = new boolean[height][width];
    for (int i = 0; i < height; i++) {
      String line = br.readLine();
      for (int u = 0; u < line.length(); u++) {
        map[i][u] = line.charAt(u);
      }
    }

    //솔루션
    for (int a = 0; a < height; a++) {
      for (int b = 0; b < width; b++) {
        if (map[a][b] == 'W') continue;
        if (visited[a][b]) continue;

        int[] start = {a, b, 0};
        bfs(start);
        visited = new boolean[height][width];
      }
    }

    //출력
    System.out.println(answer);
  }

  private static void bfs(int[] start) {
    Deque<int[]> deque = new ArrayDeque();
    deque.offerLast(start);
    visited[start[0]][start[1]] = true;

    while(!deque.isEmpty()) {
      int[] current = deque.pollFirst();
      for (int i = 0; i < 4; i++) {
        int nextR = current[0] + dr[i];
        int nextC = current[1] + dc[i];
        int nextCount = current[2] + 1;

        if (nextR < 0 || nextR >= height) continue;
        if (nextC < 0 || nextC >= width) continue;
        if (map[nextR][nextC] == 'W') continue;
        if (visited[nextR][nextC]) continue;

        visited[nextR][nextC] = true;
        deque.offerLast(new int[] {nextR, nextC, nextCount});
        answer = Math.max(answer, nextCount);
      }
    }
  }
}