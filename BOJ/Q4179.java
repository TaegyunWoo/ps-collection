import java.io.*;
import java.util.*;

public class Q4179 {
  static final int[] dr = {-1, 1, 0, 0};
  static final int[] dc = {0, 0, -1, 1};
  static int r;
  static int c;
  static char[][] map;
  static int[] jihunStart;
  static List<int[]> fireStart = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    r = Integer.parseInt(tmp[0]);
    c = Integer.parseInt(tmp[1]);
    map = new char[r][c];
    for (int i = 0; i < r; i++) {
      String s = br.readLine();
      for (int u = 0; u < c; u++) {
        map[i][u] = s.charAt(u);

        if (map[i][u] == 'J') jihunStart = new int[] {i, u};
        else if (map[i][u] == 'F') fireStart.add(new int[] {i, u});
      }
    }

    //solution
    int answer = solution();

    //print answer
    System.out.println(answer == -1 ? "IMPOSSIBLE" : answer);
  }

  private static int solution() {
    Deque<int[]> jihunDeque = new ArrayDeque<>();
    Deque<int[]> fireDeque = new ArrayDeque<>();
    jihunDeque.offerLast(new int[] {jihunStart[0], jihunStart[1], 0});
    for (int[] fire : fireStart) {
      fireDeque.offerLast(new int[] {fire[0], fire[1], 0});
    }

    int minute = 0;

    while (true) {
      while (!jihunDeque.isEmpty()) {
        if (jihunDeque.peekFirst()[2] != minute) break;

        int[] current = jihunDeque.pollFirst();
        int currentR = current[0];
        int currentC = current[1];
        int currentMinute = current[2];

        if (map[currentR][currentC] == 'F') continue;

        for (int i = 0; i < 4; i++) {
          int nextR = currentR + dr[i];
          int nextC = currentC + dc[i];
          int nextMinute = currentMinute + 1;

          //탈출시
          if (nextR < 0 || nextR >= r) return nextMinute;
          if (nextC < 0 || nextC >= c) return nextMinute;

          //벽인 경우
          if (map[nextR][nextC] == '#') continue;
          //불인 경우
          if (map[nextR][nextC] == 'F') continue;
          //방문했던 곳인 경우
          if (map[nextR][nextC] == 'J') continue;

          map[nextR][nextC] = 'J';
          jihunDeque.offerLast(new int[] {nextR, nextC, nextMinute});
        }
      }

      while (!fireDeque.isEmpty()) {
        if (fireDeque.peekFirst()[2] != minute) break;

        int[] current = fireDeque.pollFirst();
        int currentR = current[0];
        int currentC = current[1];
        int currentMinute = current[2];

        for (int i = 0; i < 4; i++) {
          int nextR = currentR + dr[i];
          int nextC = currentC + dc[i];
          int nextMinute = currentMinute + 1;

          if (nextR < 0 || nextR >= r) continue;
          if (nextC < 0 || nextC >= c) continue;
          //벽인 경우
          if (map[nextR][nextC] == '#') continue;
          //방문했던 곳인 경우
          if (map[nextR][nextC] == 'F') continue;

          map[nextR][nextC] = 'F';
          fireDeque.offerLast(new int[] {nextR, nextC, nextMinute});
        }
      }

      minute++;

      if (jihunDeque.isEmpty()) break;
    }

    return -1;
  }
}