import java.util.*;
import java.io.*;

public class Q13549 {
  static int n;
  static int k;
  static ArrayList<ArrayList<Node>> graph;
  static int[] shortestTable;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    //최단거리 테이블 초기화
    shortestTable = new int[100001];
    Arrays.fill(shortestTable, Integer.MAX_VALUE);

    //그래프 초기화
    graph = new ArrayList<>();
    for (int i = 0; i < 100001; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < 100001; i++) {
      //걸어서 한칸씩 움직이는 경우
      if (i == 0) {
        graph.get(0).add(new Node(1, 1));
      } else if (i == 100000) {
        graph.get(100000).add(new Node(99999, 1));
      } else {
        graph.get(i).add(new Node(i - 1, 1));
        graph.get(i).add(new Node(i + 1, 1));
      }

      //순간이동 하는 경우
      if (i != 0 && i <= 50000) {
        graph.get(i).add(new Node(i * 2, 0));
      }
    }

    //다익스트라
    dij(n);

    //정답 출력
    System.out.println(shortestTable[k]);
  }

  private static void dij(int start) {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    queue.offer(new Node(start, 0));
    shortestTable[start] = 0;

    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();
      int currentIndex = currentNode.index;
      int currentDistance = currentNode.distance;

      //만약 start ~ current 까지의 최단거리 테이블 값이 poll한 거리값보다 작다면, 이미 처리된 경우이므로 생략
      if (shortestTable[currentIndex] < currentDistance) continue;

      ArrayList<Node> nextNodeList = graph.get(currentIndex);
      for (Node nextNode : nextNodeList) {
        int nextIndex = nextNode.index;
        int distanceOfStartToNext = shortestTable[currentIndex] + nextNode.distance;

        if (shortestTable[nextIndex] > distanceOfStartToNext) {
          shortestTable[nextIndex] = distanceOfStartToNext;
          queue.offer(new Node(nextIndex, distanceOfStartToNext));
        }
      }
    }
  }

  public static class Node implements Comparable<Node> {
    public int index;
    public int distance;

    public Node(int index, int distance) {
      this.index = index;
      this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
      if (this.distance < other.distance) return -1;
      return 1;
    }
  }
}
