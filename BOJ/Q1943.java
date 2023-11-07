import java.io.*;

/* [점화식 정의]
 * d[i] = 현재 동전 단위를 사용해서 i원씩 나눠가지도록 만들 수 있는지
 * [풀이]
 * 동전 단위 오름차순으로, 각 동전 단위마다 d 배열 갱신
 * 단, d[sum/2]부터 시작해야함. => 반복마다 갱신이 되기에
 */
public class Q1943 {
  static int n;
  static int[] coins;
  static int t = 3;
  static int sum = 0;
  static boolean[] d;
  static String answer = "";

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (t-- > 0) {
      //init
      n = Integer.parseInt(br.readLine());
      coins = new int[100001];
      sum = 0;
      for (int i = 0; i < n; i++) {
        String[] tmp = br.readLine().split(" ");
        coins[Integer.parseInt(tmp[0])] = Integer.parseInt(tmp[1]);
        sum += Integer.parseInt(tmp[0]) * Integer.parseInt(tmp[1]);
      }

      //모든 동전 금액의 합이 홀수라면 0
      if (sum % 2 == 1) {
        answer = answer + "0\n";
        continue;
      }

      d = new boolean[sum / 2 + 1];
      d[0] = true;

      //solution
      for (int coin = 1; coin < coins.length; coin++) {
        if (coins[coin] == 0) continue;

        for (int cost = sum/2; cost - coin >= 0; cost--) {
          if (!d[cost - coin]) continue;

          for (int i = 1; i <= coins[coin]; i++) {
            if (cost + coin * (i-1) > sum/2) break;
            d[cost + coin * (i-1)] = true;
          }
        }
      }

      //answer
      if (d[sum/2]) answer = answer + "1\n";
      else answer = answer + "0\n";
    }


    //print answer
    System.out.print(answer);
  }
}