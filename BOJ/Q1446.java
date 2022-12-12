import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1446 {
  static int n;
  static int d;
  static int[] shortestTable;
  static ArrayList<ArrayList<Node>> graph;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    d = Integer.parseInt(st.nextToken());

    //최단거리 테이블 초기화
    shortestTable = new int[10001];
    Arrays.fill(shortestTable, Integer.MAX_VALUE);

    //그래프 초기화
    graph = new ArrayList<>();
    for (int i = 0; i <= 10000; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < d; i++) {
      graph.get(i).add(new Node(i + 1, 1));
    }

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int startNode = Integer.parseInt(st.nextToken());
      int endNode = Integer.parseInt(st.nextToken());
      int distance = Integer.parseInt(st.nextToken());
      graph.get(startNode).add(new Node(endNode, distance));
    }

    br.close();

    dij(0);

    //정답 출력
    System.out.println(shortestTable[d]);
  }

  private static void dij(int startIndex) {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    queue.offer(new Node(startIndex, 0));
    shortestTable[startIndex] = 0;

    //큐가 빌때까지 반복
    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();
      int currentIndex = currentNode.destinationNodeIndex;
      int currentDistance = currentNode.distance;

      //현재 노드가 방문한 노드인가 (dequeue된 거리 값이 최단거리 테이블의 값보다 크면, 이미 갱신된 노드임)
      if (shortestTable[currentIndex] < currentDistance) continue;

      ArrayList<Node> nextNodeList = graph.get(currentIndex);
      for (Node nextNode : nextNodeList) {
        int nextNodeIndex = nextNode.destinationNodeIndex;
        int distanceOfStartToNext = shortestTable[currentIndex] + nextNode.distance;

        //만약, 최단거리테이블에 저장된 '시작~다음 노드의 거리' 보다 그래프 상의 '시작~다음 노드의 거리'가 더 짧다면
        if (shortestTable[nextNodeIndex] > distanceOfStartToNext) {
          shortestTable[nextNodeIndex] = distanceOfStartToNext; //최단거리 테이블 갱신
          queue.offer(new Node(nextNode.destinationNodeIndex, distanceOfStartToNext)); //enqueue
        }
      }
    }

  }

  public static class Node implements Comparable<Node> {
    public int destinationNodeIndex;
    public int distance;

    public Node(int destinationNodeIndex, int distance) {
      this.destinationNodeIndex = destinationNodeIndex;
      this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
      if (this.distance < other.distance) return -1;
      return 1;
    }
  }
}
