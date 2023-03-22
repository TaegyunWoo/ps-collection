import java.io.*;
import java.util.*;
import java.math.*;

public class Q13305 {
  public static int n;
  public static int[] distanceAry;
  public static int[] costAry;
  public static BigInteger answer;

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    distanceAry = new int[n-1];
    costAry = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n-1; i++) {
      distanceAry[i] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      costAry[i] = Integer.parseInt(st.nextToken());
    }

    //Solution
    solution();

    //정답 출력
    System.out.println(answer.toString());
  }

  private static void solution() {
    int distanceSum = 0;
    int minCostIdx = 0;
    int minCost = costAry[minCostIdx];
    answer = BigInteger.ZERO;

    for (int i = 0; i < distanceAry.length; i++) {
      distanceSum += distanceAry[i];

      //현재까지 탐색한 cost 중, 가장 작은 cost를 발견했다면
      //혹은 마지막까지 탐색했다면
       if (minCost > costAry[i+1]
           || i == distanceAry.length - 1) {
        answer = answer.add(
            BigInteger.valueOf(distanceSum)
                .multiply(
                    BigInteger.valueOf(costAry[minCostIdx])
                )
        );

        distanceSum = 0;
        minCostIdx = i + 1;
        minCost = costAry[minCostIdx];
      }
    }
  }
}
