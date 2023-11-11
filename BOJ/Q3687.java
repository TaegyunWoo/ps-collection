import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q3687 {
  static int t;
  static int n;
  static String maxResult;
  static String minResult;
  static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    t = Integer.parseInt(br.readLine());

    //solution
    while (t-- > 0) {
      n = Integer.parseInt(br.readLine());

      if (n % 2 == 0) { //n이 짝수일 때, 가장 큰 수
        getMaxOfEven();
      } else { //n이 홀수일 때, 가장 큰 수
        getMaxOfOdd();
      }
      //가장 작은 수 구하기
      getMin();

      answer.append(minResult + " " + maxResult + "\n");
    }

    //print answer
    System.out.print(answer.toString());
  }

  private static void getMaxOfEven() {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < n/2; i++) {
      result.append("1");
    }

    maxResult = result.toString();
  }

  private static void getMaxOfOdd() {
    StringBuilder result = new StringBuilder("7");

    for (int i = 0; i < (n/2) - 1; i++) {
      result.append("1");
    }

    maxResult = result.toString();
  }

  private static void getMin() {
    StringBuilder[] d = new StringBuilder[101]; //d[a] = a개의 성냥으로 만들 수 있는 가장 작은 수
    for (int i = 9; i < d.length; i++) {
      d[i] = new StringBuilder("999999999999999999999999999999999999999999999999999");
    }
    d[2] = new StringBuilder("1"); d[3] = new StringBuilder("7");
    d[4] = new StringBuilder("4"); d[5] = new StringBuilder("2");
    d[6] = new StringBuilder("6"); d[7] = new StringBuilder("8");
    d[8] = new StringBuilder("10");

    for (int i = 9; i <= n; i++) {
      for (int u = 2; u <= 7; u++) {
        StringBuilder a = new StringBuilder(d[i-u]);

        if (u == 6) a.append(0);
        else a.append(d[u]);

        if (a.length() < d[i].length()
        || (a.length() == d[i].length() && a.compareTo(d[i]) < 0)) {
          d[i] = a;
        }
      }
    }

    minResult = d[n].toString();
  }

}