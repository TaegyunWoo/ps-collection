import java.io.*;
import java.util.*;

public class Q17406 {
  public static final int INF = (int) 1e9;
  public static int n; //행 개수
  public static int m; //열 개수
  public static int[][] table; //배열
  public static int k; //회전 연산 개수
  public static int[][] computeAry; //연산 종류
  public static int[] permutationResult;
  public static boolean[] visited;
  public static int answer = INF;

  public static void main(String[] args) throws IOException {
    //Input 처리
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    table = new int[n+1][m+1];
    for (int i = 1; i < table.length; i++) {
      st = new StringTokenizer(br.readLine());
      for (int u = 1; u < table[i].length; u++) {
        table[i][u] = Integer.parseInt(st.nextToken());
      }
    }
    computeAry = new int[k][3];
    for (int i = 0; i < computeAry.length; i++) {
      st = new StringTokenizer(br.readLine());
      computeAry[i][0] = Integer.parseInt(st.nextToken());
      computeAry[i][1] = Integer.parseInt(st.nextToken());
      computeAry[i][2] = Integer.parseInt(st.nextToken());
    }
    permutationResult = new int[k];
    visited = new boolean[k];

    //Solution
    permutation(0, k);

    System.out.println(answer);
  }

  private static void permutation(int depth, int n) {
    if (depth == n) {
      //회전 및 계산
      answer = Math.min(answer, compute());
      return;
    }

    for (int i = 0; i < n; i++) {
      if (visited[i]) continue;

      visited[i] = true;
      permutationResult[depth] = i;
      permutation(depth+1, n);

      visited[i] = false;
    }
  }

  private static int compute() {
    int[][] testTable = copyTable();

    for (int i = 0; i < permutationResult.length; i++) {
      int[] order = computeAry[permutationResult[i]];
      int startR = order[0] - order[2];
      int startC = order[1] - order[2];
      int endR = order[0] + order[2];
      int endC = order[1] + order[2];
      int preValue = 0;

      while (startR < endR && startC < endC) {
        preValue = testTable[startR][startC];
        for (int c = startC + 1; c <= endC; c++) {
          int tmp = testTable[startR][c];
          testTable[startR][c] = preValue;
          preValue = tmp;
        }

        for (int r = startR + 1; r <= endR; r++) {
          int tmp = testTable[r][endC];
          testTable[r][endC] = preValue;
          preValue = tmp;
        }

        for (int c = endC - 1; c >= startC; c--) {
          int tmp = testTable[endR][c];
          testTable[endR][c] = preValue;
          preValue = tmp;
        }

        for (int r = endR - 1; r >= startR; r--) {
          int tmp = testTable[r][startC];
          testTable[r][startC] = preValue;
          preValue = tmp;
        }

        startR++;
        startC++;
        endR--;
        endC--;
      }
    }

    return calculateTableValue(testTable);
  }

  private static int calculateTableValue(int[][] table) {
    int result = INF;

    for (int i = 1; i < table.length; i++) {
      int tmp = 0;
      for (int u = 1; u < table[i].length; u++) {
        tmp += table[i][u];
      }
      result = Math.min(result, tmp);
    }

    return result;
  }

  private static int[][] copyTable() {
    int[][] copyAry = new int[n+1][m+1];
    for (int i = 0; i < table.length; i++) {
      for (int u = 0; u < table[i].length; u++) {
        copyAry[i][u] = table[i][u];
      }
    }
    return copyAry;
  }
}
