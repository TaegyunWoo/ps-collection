import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1520 {
  static int m; //세로 크기
  static int n; //가로 크기
  static int[][] map;
  static boolean[][] visited;
  static int[] dc = {-1, 1, 0, 0};
  static int[] dr = {0, 0, -1, 1};
  static int[][] d;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    m = Integer.parseInt(st.nextToken());
    n = Integer.parseInt(st.nextToken());

    map = new int[m][n];
    visited = new boolean[m][n];
    d = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int u = 0; u < n; u++) {
        d[i][u] = -1;
      }
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      for (int u = 0; u < n; u++) {
        int v = Integer.parseInt(st.nextToken());
        map[i][u] = v;
      }
    }

    br.close();

    dfs(0, 0);

    System.out.println(d[0][0]);

    for (int i = 0; i < m; i++) {
      for (int u = 0; u < n; u++) {
        System.out.print(d[i][u] + " ");
      }
      System.out.println();
    }
  }

  private static int dfs(int x, int y) {
    //목적지에 도착했다면
    if (y == m-1 && x == n-1) {
      return 1;
    }

    //방문했던 곳이면
    if (d[y][x] != -1) {
      return d[y][x];
    }

    //첫 방문 시 초기화
    d[y][x] = 0;

    //4방면 탐색
    for (int i = 0; i < 4; i++) {
      int nc = x + dc[i];
      int nr = y + dr[i];

      //존재하지 않는 위치인 경우
      if (nc < 0 || nc >= n) continue;
      if (nr < 0 || nr >= m) continue;

      //인근 노드가 내리막길인 경우
      if (map[y][x] > map[nr][nc]) {
        d[y][x] += dfs(nc, nr);
      }
    }

    return d[y][x];
  }
}
