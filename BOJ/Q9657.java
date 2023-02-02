import java.io.*;

/**
 * 1개, 3개, 4개의 돌을 가져와, 상근이가 한번이라도 이길 수 있다면 상근은 승리자 (무조건 이기는 수를 선택해야 하므로)
 * 1개, 3개, 4개의 돌을 가져와봐도, 상근이가 한번도 못이기면 상근은 패배자
 */
public class Q9657 {
  // d[i] = i개의 돌이 남았을 때, 상근이가 승리할 수 있는 방법이 있다면 true, 아니면 false
  static boolean[] d;
  static int n = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    d = new boolean[1001];

    String answer = solution();

    System.out.println(answer);
  }

  private static String solution() {
    d[1] = true; //상근이가 돌을 1개 가져오면 남은 것이 없기 때문에 상근 승리
    d[2] = false; //상근이가 돌을 1개 가져와도 무조건 1개가 남기 때문에 상근 패배
    d[3] = true; //상근이가 돌을 3개 가져오면 남은 것이 없기 때문에 상근 승리
    d[4] = true; //상근이가 돌을 4개 가져오면 남은 것이 없기 때문에 상근 승리

    for (int i = 5; i <= n; i++) {
      /*
       * "상근이가 i개의 돌에서 가져갔을 때의 결과"인 (i-1,i-3,i-4) 중
       * "상근이가 지는 경우"인 결과를 선택해야, i개의 돌에서 상근이가 이길 수 있다.
       * 왜냐하면 현재 i개의 돌에서 상근이가 돌을 가져갔을 때,
       * 바로 직후의 결과가 "상근이가 지는 결과"가 아닌 "상영이가 지는 결과"이므로
       *
       */
      if (d[i-1] && d[i-3] && d[i-4]) d[i] = false;
      else d[i] = true;
    }

    return d[n] ? "SK" : "CY";
  }
}
