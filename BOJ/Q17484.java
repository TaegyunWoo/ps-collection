import java.io.*;
import java.util.*;

public class Q17484 {
  public static final int INF = (int) 1e9;
  public static int n;
  public static int m;
  public static int[][] map;
  public static int[] permutationResult;
  public static int answer = INF;

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    map = new int[n][m];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int u = 0; u < m; u++) {
        map[i][u] = Integer.parseInt(st.nextToken());
      }
    }

    //Solution
    solution();

    //정답 출력
    System.out.println(answer);
  }

  private static void solution() {
    permutationResult = new int[n-1];
    permutation(0, -1);
  }

  private static void permutation(int depth, int beforeMove) {
    if (depth == n-1) {
      //해당 경로의 최소 비용 계산
      int tmp = getMinFuel();

      answer = Math.min(answer, tmp);
      return;
    }

    for (int i = 0; i < 3; i++) {
      if (i == beforeMove) continue;

      permutationResult[depth] = i;
      permutation(depth+1, i);
    }
  }

  private static int getMinFuel() {
    int[] start = map[0];
    int result = INF;

    for (int i = 0; i < start.length; i++) {
      int sum = start[i];
      int currentR = 0;
      int currentC = i;
      int nextR = currentR + 1;
      int nextC = currentC;

      for (int u = 0; u < permutationResult.length; u++) {
        if (permutationResult[u] == 0) {
          nextC--;
        } else if (permutationResult[u] == 2) {
          nextC++;
        }

        if (nextC < 0 || nextC >= m) {
          sum = INF;
          break;
        }
        sum += map[nextR][nextC];
        nextR++;
      }

      result = Math.min(result, sum);
    }

    return result;
  }
}
