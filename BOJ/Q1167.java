import java.io.*;
import java.util.*;

/**
 * [트리의 지름 알고리즘]
 * 1. 임의의 한 노드에서부터 가장 거리가 먼 노드를 고른다.
 * 2. 1번에서 고른 노드에서 다시 거리가 가장 먼 노드를 고른다.
 * 3. 2번에서 계산한 거리가 트리의 지름이다.
 *
 * [직관적인 증명]
 * 1. '노드 = 구슬', '엣지 = 실' 이라고 가정해보자.
 * 2. 먼저 임의의 한 구슬을 잡고 위로 쭉 든다.
 * 3. 그리고 가장 아래에 있는 (가장 거리가 먼) 구슬을 다시 잡는다.
 * 4. 3번에서 잡은 구슬을 다시 위로 쭉 들어, 가장 아래에 있는 구슬을 잡는다.
 * 5. 3번에서 잡은 구슬과 4번에서 잡은 구슬이 가장 거리가 먼 구슬이다.
 *
 * [상세 설명]
 * - 임의의 한 노드로부터 가장 거리가 먼 노드를 선택한다면, 선택된 노드는 '지름을 이루는 경로의 양끝 노드 중 하나'이다.
 * - 그렇기 때문에, 그 노드로부터 다시 가장 거리가 먼 노드를 선택한다면 지름을 구할 수 있다.
 */
public class Q1167 {

  static int n;
  static List<List<Node>> graph = new ArrayList<>();
  static boolean[] visited;
  static long answer = 0;
  static long firstDistance = 0; //firstNode를 구하기 위한 변수
  static int firstNode = 0; //트리의 지름인 경로에서의 한쪽 끝 노드

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());

    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 1; i <= n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int nodeA = Integer.parseInt(st.nextToken());
      int nodeB = 0;
      int distance = 0;
      while (st.hasMoreTokens()) {
        nodeB = Integer.parseInt(st.nextToken());

        if (nodeB == -1) break;

        distance = Integer.parseInt(st.nextToken());

        graph.get(nodeA).add(new Node(nodeB, distance));
      }

    }

    solution();

  }

  public static void solution() {
    visited = new boolean[n+1];
    getFirstNode(1, 0);

    visited = new boolean[n+1];
    getLongestDistance(firstNode, 0);

    System.out.print(answer);
  }

  private static void getFirstNode(int current, long totalDistance) {
    if (visited[current]) {
      return;
    }
    visited[current] = true;

    if (firstDistance < totalDistance) {
      firstDistance = totalDistance;
      firstNode = current;
    }

    List<Node> nearList = graph.get(current);
    for (Node near : nearList) {
      getFirstNode(near.getIndex(), totalDistance + near.getDistance());
    }
  }

  private static void getLongestDistance(int current, long totalDistance) {
    if (visited[current]) {
      return;
    }
    visited[current] = true;

    answer = Math.max(answer, totalDistance);

    List<Node> nearList = graph.get(current);
    for (Node near : nearList) {
      getLongestDistance(near.getIndex(), totalDistance + near.getDistance());
    }
  }

  private static class Node {
    private int index;
    private int distance;

    public Node() {}

    public Node(int index, int distance) {
      this.index = index;
      this.distance = distance;
    }

    public int getIndex() {
      return index;
    }

    public int getDistance() {
      return distance;
    }
  }
}