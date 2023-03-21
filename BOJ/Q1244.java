import java.io.*;
import java.util.*;

public class Q1244 {
  public static int n; //스위치 개수
  public static int[] switchAry; //스위치 상태 배열
  public static int k; //학생 수
  public static int[][] studentAry; //학생 상태 배열

  public static void main(String[] args) throws IOException {
    //Input 처리
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    switchAry = new int[n+1];
    for (int i = 1; i <= n; i++) {
      switchAry[i] = Integer.parseInt(st.nextToken());
    }
    k = Integer.parseInt(br.readLine());
    studentAry = new int[k][2];
    for (int i = 0; i < k; i++) {
      st = new StringTokenizer(br.readLine());
      studentAry[i][0] = Integer.parseInt(st.nextToken());
      studentAry[i][1] = Integer.parseInt(st.nextToken());
    }

    //Solution
    for (int i = 0; i < studentAry.length; i++) {
      int gender = studentAry[i][0];
      int num = studentAry[i][1];
      if (gender == 1) calBoy(num);
      else calGirl(num);
    }

    //정답 출력
    for (int i = 1; i < switchAry.length; i++) {
      if (i % 20 == 1 && i != 1) System.out.println();
      System.out.print(switchAry[i] + " ");
    }
  }

  private static void calBoy(int num) {
    int idx = num;
    for (int i = 2; idx < switchAry.length; i++) {
      if (switchAry[idx] == 0) switchAry[idx] = 1;
      else switchAry[idx] = 0;
      idx = i * num;
    }
  }

  private static void calGirl(int num) {
    int left = num - 1;
    int right = num + 1;

    if (switchAry[num] == 0) switchAry[num] = 1;
    else switchAry[num] = 0;

    while (left >= 1 && right < switchAry.length) {
      if (switchAry[left] != switchAry[right]) break;

      if (switchAry[left] == 0) switchAry[left] = 1;
      else switchAry[left] = 0;
      if (switchAry[right] == 0) switchAry[right] = 1;
      else switchAry[right] = 0;

      left--;
      right++;
    }
  }
}
