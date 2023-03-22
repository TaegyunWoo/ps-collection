import java.io.*;
import java.util.*;

public class Q2512 {
  public static int n;
  public static int[] requestAry;
  public static int budget;
  public static int answer = 0;

  public static void main(String[] args) throws IOException {
    //Input 처리
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    requestAry = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      requestAry[i] = Integer.parseInt(st.nextToken());
    }
    budget = Integer.parseInt(br.readLine());

    //Solution
    solution();

    //정답 출력
    System.out.println(answer);
  }

  private static void solution() {
    Arrays.sort(requestAry); //요청 예산 오름차순 정렬

    int min = 1;
    int max = requestAry[n-1];
    int mid = (min + max) / 2;

    while (min <= max) {
      int sumResult = sum(mid);

      if (sumResult > budget) {
        max = mid - 1;

      } else if (sumResult < budget) {
        min = mid + 1;

      } else {
        break;
      }

      mid = (min + max) / 2;
    }

    answer = mid;
  }

  private static int sum(int limit) {
    int result = 0;

    for (int request : requestAry) {
      result += (request < limit) ? request : limit;
    }

    return result;
  }
}
