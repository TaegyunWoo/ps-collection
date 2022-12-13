import java.util.*;
import java.io.*;

public class Q2458 {
  static int n; //학생 수
  static int m; //비교 횟수
  static boolean[][] graph;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    //graph 초기화
    graph = new boolean[n + 1][n + 1];
    for (int i = 1; i < n + 1; i++) {
      graph[i][i] = true;
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph[a][b] = true;
    }

    br.close();

    //플로이드 워셜
    floyd();

    int answer = 0;
    for (int k = 1; k < n + 1; k++) {
      //k번째 학생보다 큰 학생들 찾기
      int tallerCount = countTallerStudents(k);
      //k번째 학생보다 작은 학생들 찾기
      int smallerCount = countSmallerStudents(k);
      //만약 tallerCount + smallerCount 가 n - 1 이라면 k번째 학생의 순서를 정확히 알 수 있음
      if (tallerCount + smallerCount == n - 1) answer++;
    }

    System.out.println(answer);
  }

  private static void floyd() {
    for (int k = 1; k < n + 1; k++) {
      for (int a = 1; a < n + 1; a++) {
        for (int b = 1; b < n + 1; b++) {
          if (graph[a][k] && graph[k][b]) graph[a][b] = true;
        }
      }
    }
  }

  private static int countTallerStudents(int k) {
    int count = 0;
    for (int other = 1; other < n + 1; other++) {
      if (graph[k][other]) count++;
    }

    return count - 1; //자기자신을 센 경우 빼기
  }

  private static int countSmallerStudents(int k) {
    int count = 0;
    for (int other = 1; other < n + 1; other++) {
      if (graph[other][k]) count++;
    }

    return count - 1; //자기자신을 센 경우 빼기
  }
}
