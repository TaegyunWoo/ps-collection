import java.util.*;
import java.io.*;

public class Q21921 {
  public static int n;
  public static int x;
  public static int[] visitAry;
  public static int[] accumulateAry;
  public static int maxVisit = 0;
  public static int maxVisitCount = 0;

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    x = Integer.parseInt(st.nextToken());
    visitAry = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      visitAry[i] = Integer.parseInt(st.nextToken());
    }

    //Solution
    solution();

    //정답 출력
    if (maxVisit == 0) {
      System.out.println("SAD");
    } else {
      System.out.println(maxVisit + "\n" + maxVisitCount);
    }
  }

  private static void solution() {
    accumulateAry = new int[n];

    //누적합 배열 만들기
    accumulateAry[0] = visitAry[0];
    for (int i = 1; i < n; i++) {
      accumulateAry[i] = accumulateAry[i-1] + visitAry[i];
    }

    //투포인터로 정답 찾기
    maxVisit = accumulateAry[x-1]; //초기화
    maxVisitCount = 1; //초기화
    int left = 0;
    int right = x;
    while (right < n) {
      int result = accumulateAry[right] - accumulateAry[left];

      if (maxVisit < result) {
        maxVisit = result;
        maxVisitCount = 1;
      } else if (maxVisit == result) {
        maxVisitCount++;
      }

      right++;
      left++;
    }
  }
}
