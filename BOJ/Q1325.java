import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1325 {
  static ArrayList<ArrayList<Integer>> graph;
  static boolean[] visited;
  static int n;
  static int m;
  static PriorityQueue<Integer> answerQueue;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();
    visited = new boolean[n + 2];

    for (int i = 0; i <= n + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int nodeA = Integer.parseInt(st.nextToken());
      int nodeB = Integer.parseInt(st.nextToken());
      graph.get(nodeB).add(nodeA);
    }

    br.close();

    //BFS
    int currentMaxHackNum = 0;
    answerQueue = new PriorityQueue<>();
    for (int startNode = 1; startNode <= n + 1; startNode++) {
      int hackNum = bfs(startNode);

      if (hackNum > currentMaxHackNum) {
        answerQueue = new PriorityQueue<>(); //초기화
        answerQueue.offer(startNode); //추가
        currentMaxHackNum = hackNum;
      } else if (hackNum == currentMaxHackNum) {
        answerQueue.offer(startNode); //추가
      }

      visited = new boolean[n+2]; //visit 초기화
    }

    //출력
    for (int node : answerQueue) {
      System.out.print(node + " ");
    }
  }

  private static int bfs(int startNode) {
    int count = 0;
    Deque<Integer> deque = new ArrayDeque<>();
    visited[startNode] = true;
    deque.addLast(startNode);
    count++;

    while (!deque.isEmpty()) {
      int nowNode = deque.pollLast();
      List<Integer> nearList = graph.get(nowNode);

      for (int nearNode : nearList) {
        if (visited[nearNode]) continue;
        visited[nearNode] = true;
        deque.addLast(nearNode);
        count++;
      }
    }
    return count;
  }
}
