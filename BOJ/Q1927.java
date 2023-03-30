import java.io.*;
import java.util.*;

public class Q1927 {
  public static int n;
  public static int[] inputAry;
  public static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
  public static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    inputAry = new int[n];
    for (int i = 0; i < n; i++) {
      inputAry[i] = Integer.parseInt(br.readLine());
    }

    //Solution
    solution();

    //정답 출력
    System.out.println(answer.toString());
  }

  private static void solution() {
    for (int input : inputAry) {
      if (input == 0) {
        if (priorityQueue.isEmpty()) answer.append("0\n");
        else answer.append(priorityQueue.poll() + "\n");
      } else {
        priorityQueue.offer(input);
      }
    }
  }
}
