import java.io.*;
import java.util.*;

public class Q2493 {
  static int n;
  static int[] numAry;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    numAry = new int[n];
    String[] tmp = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      numAry[i] = Integer.parseInt(tmp[i]);
    }

    //solution
    String answer = solution();

    //print answer
    System.out.println(answer);
  }

  private static String solution() {
    Deque<int[]> deque = new ArrayDeque<>();
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < numAry.length; i++) {
      int num = numAry[i];

      if (deque.isEmpty()) {
        result.append("0 ");
        deque.offerLast(new int[] {num, i});
        continue;
      }

      int peekNum = deque.peekLast()[0];
      int peekIdx = deque.peekLast()[1];
      if (peekNum < num) {
        deque.pollLast();
        i--;
      } else if (peekNum > num) {
        result.append(peekIdx + 1);
        result.append(" ");
        deque.offerLast(new int[] {num, i});
      }
    }

    return result.toString();
  }
}