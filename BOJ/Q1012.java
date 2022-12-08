import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1012 {
  static boolean[][] map;
  static boolean[][] visited;
  static int t; //테스트 케이스 수
  static int m; //밭 가로 길이
  static int n; //밭 세로 길이
  static int k; //배추 개수
  static int[] nc = {-1, 1, 0, 0};
  static int[] nr = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    t = Integer.parseInt(br.readLine());

    //테스트 케이스마다 반복
    while (t-- > 0) {
      int answer = 0;
      StringTokenizer st = new StringTokenizer(br.readLine());
      m = Integer.parseInt(st.nextToken());
      n = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());

      map = new boolean[m][n];
      visited = new boolean[m][n];
      for (int i = 0; i < k; i++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        map[x][y] = true; //배추가 있음
      }

      //각 좌표(노드) 마다 반복
      for (int i = 0; i < m; i++) {
        for (int u = 0; u < n; u++) {
          if (visited[i][u] || !map[i][u]) continue;
          dfs(i, u); //탐색
          answer++;
        }
      }

      System.out.println(answer);
    }
  }

  private static void dfs(int nowX, int nowY) {
    visited[nowX][nowY] = true; //방문처리

    for (int i = 0; i < 4; i++) {
      int nearX = nowX + nc[i];
      int nearY = nowY + nr[i];
      if (nearX < 0 || nearX >= m) continue; //존재하지 않는 좌표(x)인 경우
      if (nearY < 0 || nearY >= n) continue; //존재하지 않는 좌표(y)인 경우
      if (visited[nearX][nearY]) continue; //이미 방문한 좌표인 경우
      if (!map[nearX][nearY]) continue; //배추가 없는 좌표인 경우
      dfs(nearX, nearY);
    }

  }

}
