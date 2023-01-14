import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q27210 {

  static int n;
  static int[] stoneAry;
  static List<Integer> toNumList = new ArrayList<>();
  static int[][] d;
  static int answer = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    stoneAry = new int[n];
    String[] s = br.readLine().split(" ");
    for (int i = 0; i < s.length; i++) {
      stoneAry[i] = Integer.parseInt(s[i]);
    }

    initToNumAry();

    /*
     * d[i][0] = i번째 요소를 부분수열에 포함시킬 때, 요소들의 최대합
     * d[i][1] = i번째 요소를 부분수열에 포함시킬 때, 요소들의 최소합
     */
    d = new int[n][2];
    d[0][0] = toNumList.get(0);
    d[0][1] = toNumList.get(0);
    answer = Math.abs(d[0][0]);
    for (int i = 1; i < toNumList.size(); i++) {
      int current = toNumList.get(i);
      if (d[i-1][0] + current > current) d[i][0] = d[i-1][0] + current; //앞요소와 이어 부분수열을 만드는 경우 최대합
      else d[i][0] = current; //현재 요소가 부분수열의 시작점일 때 최대합
      if (d[i-1][1] + current < current) d[i][1] = d[i-1][1] + current; //앞요소와 이어 부분수열을 만드는 경우 최소합
      else d[i][1] = current; //현재 요소가 부분수열의 시작점일 때 최대합
      answer = Math.max(answer, Math.abs(d[i][0]));
      answer = Math.max(answer, Math.abs(d[i][1]));
    }

    System.out.println(answer);
  }

  private static void initToNumAry() {
    int before = stoneAry[0];
    int sum = (stoneAry[0] == 1) ? 1 : -1;
    for (int i = 1; i < n; i++) {
      if (before == stoneAry[i]) {
        if (before == 1) sum++;
        else sum--;
      } else {
        toNumList.add(sum);
        sum = (stoneAry[i] == 1) ? 1 : -1;
        before = stoneAry[i];
      }
    }
    toNumList.add(sum);

    for (int i : toNumList) {
      System.out.print(i + " ");
    }
  }

}