import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2294 {
  static int n; //동전 종류 개수
  static int k; //목표금액
  static int[] coinAry; //동전 단위
  static int[] d;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    coinAry = new int[n];

    for (int i = 0; i < n; i++) {
      coinAry[i] = Integer.parseInt(br.readLine());
    }

    br.close();

    //DP
    dp();

    //정답 출력
    if (d[k] == 1000000000)
      System.out.println("-1");
    else
      System.out.println(d[k]);
  }

  /**
   * <점화식 정리>
   *  d[i] = 금액 i 를 만족하는 최소 동전 수
   *  d[i] = min( d[i-동전단위1], d[i-동전단위2], d[i-동전단위3], ... ) + 1
   */
  private static void dp() {
    //초기화
    d = new int[k + 1];
    for (int i = 1; i <= k; i++) {
      d[i] = 1000000000;
    }
    for (int i = 0; i < n; i++) {
      int cost = coinAry[i];
      if (cost > k) continue;
      d[cost] = 1;
    }

    //DP
    for (int currentCost = 1; currentCost <= k; currentCost++) {
      for (int i = 0; i < n; i++) {
        if (currentCost - coinAry[i] < 0) continue;
        if (d[currentCost - coinAry[i]] == 0) continue; //만들 수 없는 금액이라면 생략
        d[currentCost] = Math.min(d[currentCost], d[currentCost - coinAry[i]] + 1);
      }
    }
  }
}
