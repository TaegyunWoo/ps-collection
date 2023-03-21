import java.io.*;
import java.util.*;

public class Q2164 {
  public static int n;
  public static Deque<Integer> deque = new ArrayDeque<>();

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());

    //Solution
    for (int i = 1; i <= n; i++) {
      deque.offerLast(i);
    }

    int step = 0;
    while (deque.size() > 1) {
      if (step % 2 == 0) deque.pollFirst();
      else deque.offerLast(deque.pollFirst());
      step++;
    }

    //정답 출력
    System.out.println(deque.pollFirst());
  }
}
