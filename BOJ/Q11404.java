import java.util.*;
import java.io.*;

public class Q11404 {
  static int n; //도시 개수
  static int m; //버스 개수
  static final int INF = (int) 1e9;
  static int[][] graph;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    m = Integer.parseInt(br.readLine());

    //graph 초기화
    graph = new int[n + 1][n + 1];
    for (int i = 0; i < n + 1; i++) {
      Arrays.fill(graph[i], INF);
      graph[i][i] = 0;
    }
    for (int i = 0; i < m; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      //같은 시작/도착 도시를 갖는 노선에 대한 처리
      if (graph[a][b] > c) {
        graph[a][b] = c;
      }
    }

    //플로이드 워셜
    floyd();

    //정답 출력
    for (int i = 1; i < n + 1; i++) {
      StringBuilder answer = new StringBuilder();
      for (int u = 1; u < n + 1; u++) {
        if (graph[i][u] == INF) {
          answer.append("0 ");
        } else {
          answer.append(graph[i][u] + " ");
        }
      }
      answer.delete(answer.length() - 1, answer.length());
      System.out.print(answer);
      System.out.println();
    }
  }

  private static void floyd() {
    for (int k = 1; k < n + 1; k++) {
      for (int a = 1; a < n + 1; a++) {
        for (int b = 1; b < n + 1; b++) {
          graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
        }
      }
    }
  }
}
