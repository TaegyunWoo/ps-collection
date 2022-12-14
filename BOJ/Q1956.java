import java.util.*;
import java.io.*;

public class Q1956 {
  static int v; //마을 개수
  static int e; //길 개수
  static int[][] graph;
  static final int INF = (int) 1e9;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    v = Integer.parseInt(st.nextToken());
    e = Integer.parseInt(st.nextToken());

    //그래프 초기화
    graph = new int[v + 1][v + 1];
    for (int i = 0; i < v + 1; i++) {
      Arrays.fill(graph[i], INF);
      /*
       * 자기자신을 0으로 초기화하는 것은 생략한다. (사이클을 만들어야 하기 때문에)
       * graph[i][i] = 0
       */
    }
    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      graph[a][b] = c;
    }

    br.close();

    //플로이드 워셜
    floyd();

    //정답 출력
    int answer = INF;
    for (int i = 1; i < v + 1; i++) {
      answer = Math.min(answer, graph[i][i]);
    }
    if (answer == INF) {
      System.out.println(-1);
    } else {
      System.out.println(answer);
    }

  }

  private static void floyd() {
    for (int k = 1; k < v + 1; k++) {
      for (int a = 1; a < v + 1; a++) {
        for (int b = 1; b < v + 1; b++) {
          graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
        }
      }
    }
  }
}
