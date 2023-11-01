import java.io.*;
import java.util.*;

public class Q2631 {
  static int[] nums;
  static int n;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }

    //solution
    int longestAscendingLength = getLongestAscendingLength();

    //print answer
    System.out.println(n - longestAscendingLength);
  }

  private static int getLongestAscendingLength() {
    //d[i] : i번째 num까지 살펴봤을 때, 가장 긴 오름차순 수열의 길이
    //d[i] = d[0] ~ d[i-1]의 중 가장 긴 값 + 1 (단, d[a] 수열의 마지막 숫자보다 i번째 숫자가 더 큰 경우만 해당)
    int[] d = new int[n];

    for (int i = 0; i < n; i++) {
      d[i] = 1;
      for (int u = 0; u < i; u++) {
        if (nums[u] < nums[i])
          d[i] = Math.max(d[i], d[u] + 1);
      }
    }

    return Arrays.stream(d).max().getAsInt();
  }

}