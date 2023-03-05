import java.io.*;
import java.util.StringTokenizer;

public class Q1806 {
  public static int n;
  public static int s;
  public static long[] accumulateTable;

  public static void main(String[] args) throws IOException {
    //입력 처리
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    s = Integer.parseInt(st.nextToken());
    accumulateTable = new long[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      accumulateTable[i] = Integer.parseInt(st.nextToken());
    }

    //자기자신만으로 조건이 만족되는 경우
    for (long num : accumulateTable) {
      if (num >= s) {
        System.out.println(1);
        return;
      }
    }

    //누적합
    for (int i = 1; i < accumulateTable.length; i++) {
      accumulateTable[i] += accumulateTable[i-1];
    }

    //투포인터
    int answer = (int) 1e9;
    int leftIndex = 0;
    int rightIndex = 1;

    while (true) {
      long sum;
      if (leftIndex == 0) {
        sum = accumulateTable[rightIndex];
      } else {
        sum = accumulateTable[rightIndex] - accumulateTable[leftIndex - 1];
      }

      if (sum >= s) {
        answer = Math.min(answer, rightIndex - leftIndex + 1);
        leftIndex++;
      } else {
        rightIndex++;
      }

      if (rightIndex >= n || leftIndex >= rightIndex) break;
    }

    if (answer == (int) 1e9) {
      System.out.println(0);
    } else {
      System.out.println(answer);
    }
  }
}
