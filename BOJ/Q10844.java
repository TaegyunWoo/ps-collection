import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10844 {
  static long[][] d;
  static int n;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    br.close();

    d = new long[n + 1][10];
    dp();

    //정답 출력
    long answer = 0;
    for (int i = 1; i < 10; i++) { //0으로 시작되는 계단수는 정답에 포함X
      answer += d[n][i];
      answer %= 1000000000;
    }
    System.out.println(answer);
  }

  private static void dp() {
    //초기화
    for (int i = 0; i < 10; i++) {
      d[1][i] = 1;
    }

    /*
     * <점화식 정리>
     * d[i][u] = 숫자 u로 시작하는 i 자리의 계단수 갯수
     * d[i][u] = d[i-1][u-1] + d[i-1][u+1] (단, u는 1이상 8이하)
     * d[i][0] = d[i-1][u+1]
     * d[i][9] = d[i-1][u-1]
     */
    for (int i = 2; i <= n; i++) {
      for (int u = 0; u < 10; u++) {
        if (u == 0)
          d[i][u] = d[i-1][u+1];
        else if (u == 9)
          d[i][u] = d[i-1][u-1];
        else
          d[i][u] = (d[i-1][u-1] + d[i-1][u+1]) % 1000000000;
      }
    }
  }
}
