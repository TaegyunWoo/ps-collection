import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q2583 {
  static int m; //세로
  static int n; //가로
  static int k; //직사각형 개수
  static boolean[][] map;
  static boolean[][] visited;
  static int[] nc = {-1, 1, 0, 0};
  static int[] nr = {0, 0, -1, 1};
  static int areaSize = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    m = Integer.parseInt(st.nextToken());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    map = new boolean[n][m];
    visited = new boolean[n][m];

    //map 초기화
    for (int i = 0; i < k; i++) {
      st = new StringTokenizer(br.readLine());
      int leftLowX = Integer.parseInt(st.nextToken());
      int leftLowY = Integer.parseInt(st.nextToken());
      int rightTopX = Integer.parseInt(st.nextToken());
      int rightTopY = Integer.parseInt(st.nextToken());

      //(leftLowX, leftLowY) ~ (rightTopX, rightTopY) 까지 직사각형 표시
      for (int u = leftLowX; u < rightTopX; u++) {
        for (int j = leftLowY; j < rightTopY; j++) {
          map[u][j] = true; //직사각형 표시
        }
      }
    }
    br.close();

    //dfs
    List<Integer> sizeList = new ArrayList<>();
    for (int x = 0; x < n; x++) {
      for (int y = 0; y < m; y++) {
        if (visited[x][y] || map[x][y]) continue;
        areaSize = 0;
        dfs(x, y);
        sizeList.add(areaSize);
      }
    }

    //정답출력
    sizeList.sort(Integer::compareTo);
    StringBuilder sb = new StringBuilder();
    sizeList.stream().forEach(size -> sb.append(size + " "));
    sb.substring(0, sb.lastIndexOf(" "));

    System.out.println(sizeList.size());
    System.out.println(sb);
  }

  static void dfs(int x, int y) {
    visited[x][y] = true; //방문처리
    areaSize++;

    for (int i = 0; i < 4; i++) {
      int nearX = x + nc[i];
      int nearY = y + nr[i];

      //(nearX, nearY)를 방문할 수 있는지 확인
      if (nearX < 0 || nearX >= n) continue;
      if (nearY < 0 || nearY >= m) continue;
      if (visited[nearX][nearY]) continue;
      if (map[nearX][nearY]) continue;

      dfs(nearX, nearY);
    }
  }
}
