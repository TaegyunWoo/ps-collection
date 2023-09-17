import java.io.*;
import java.util.*;

public class Q5972 {
  static final int INF = (int) 1e9;
  static int n;
  static int m;
  static List<List<Node>> graph = new ArrayList<>();
  static int[] countCow;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    n = Integer.parseInt(tmp[0]);
    m = Integer.parseInt(tmp[1]);
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < m; i++) {
      tmp = br.readLine().split(" ");
      int a = Integer.parseInt(tmp[0]);
      int b = Integer.parseInt(tmp[1]);
      int cow = Integer.parseInt(tmp[2]);
      graph.get(a).add(new Node(b, cow));
      graph.get(b).add(new Node(a, cow));
    }
    countCow = new int[n+1];
    Arrays.fill(countCow, INF);

    //solution
    bfs(1);

    //print answer
    System.out.println(countCow[n]);
  }

  private static void bfs(int startNum) {
    Deque<Integer> deque = new ArrayDeque<>();
    deque.offerLast(startNum);
    countCow[startNum] = 0;

    while (!deque.isEmpty()) {
      int currentNum = deque.pollFirst();

      List<Node> nextList = graph.get(currentNum);
      for (Node next : nextList) {
        int startToNextCow = countCow[currentNum] + next.cow;
        if (countCow[next.num] <= startToNextCow) continue;
        countCow[next.num] = startToNextCow;
        deque.offerLast(next.num);
      }
    }
  }

  public static class Node {
    public int num;
    public int cow;
    public Node(int num, int cow) {
      this.num = num;
      this.cow = cow;
    }
  }
}