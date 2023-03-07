import java.io.*;
import java.util.*;

/**
 * [점화식 정의]
 * d[x][y] = y번째 객실까지 살펴봤을 때, x번째 소형 기관차가 나를 수 있는 최대 승객 수
 *
 * [점화식]
 * d[x][y] = max(x번째 소형 기관차가 y번째 객실을 포함하지 않는 경우, x번째 소형 기관차로 y번째 객실을 마지막으로 총 m개의 객실을 갖는 경우)
 * 단, m = 소형 기관차가 끌 수 있는 최대 객실 수
 *
 * d[x][y] = max( d[x][y-1], d[x-1][y-m] + (acc[y] - acc[y-m]) )
 */
public class Q2616 {
  public static int n; //기관차가 끌던 객차 수
  public static int k; //소형 기관차가 끌 수 있는 최대 객차 수
  public static int[] accumulateTable = new int[50001];
  public static int[][] d = new int[4][50001];

  public static void main(String[] args) throws IOException {
    //입력 처리
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= n; i++) {
      accumulateTable[i] = Integer.parseInt(st.nextToken());;
    }
    k = Integer.parseInt(br.readLine());

    if (k == 0) {
      System.out.println(0);
      return;
    }

    //누적합
    for (int i = 1; i <= n; i++) {
      accumulateTable[i] += accumulateTable[i-1];
    }

    //DP
    for (int train = 1; train <= 3; train++) {
      for (int i = train * k; i <= n; i++) {
        d[train][i] = Math.max(d[train][i-1], d[train-1][i-k] + (accumulateTable[i] - accumulateTable[i-k]));
      }
    }

    System.out.println(d[3][n]);
  }
}
