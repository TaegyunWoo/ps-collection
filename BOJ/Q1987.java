import java.io.*;
import java.util.*;

public class Q1987 {
  static final int[] dr = {-1, 1, 0, 0};
  static final int[] dc = {0, 0, -1, 1};
  static int r;
  static int c;
  static char[][] map;
  static int answer = 1;

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
      }
    }

    //solution
    Set<Character> visitCharSet = new HashSet<>();
    visitCharSet.add(map[0][0]);
    dfs(0, 0, visitCharSet, 0);

    //print answer
    System.out.println(answer);
  }

  private static void dfs(int currentR, int currentC, Set<Character> visitCharSet, int depth) {
    for (int i = 0; i < 4; i++) {
      int nextR = currentR + dr[i];
      int nextC = currentC + dc[i];

      if (nextR < 0 || nextR >= r) continue;
      if (nextC < 0 || nextC >= c) continue;
      if (visitCharSet.contains(map[nextR][nextC])) continue;

      visitCharSet.add(map[nextR][nextC]);
      answer = Math.max(answer, visitCharSet.size());
      dfs(nextR, nextC, visitCharSet, depth+1);
      visitCharSet.remove(map[nextR][nextC]);
    }
  }
}