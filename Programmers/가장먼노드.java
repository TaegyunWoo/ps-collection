import java.util.*;

class Solution {
  static final int INF = (int) 1e9;
  ArrayList<ArrayList<Node>> graph = new ArrayList<>();
  int[] shortestTable;
  int longestDistance = 0;

  public int solution(int n, int[][] edge) {
    //최단거리 테이블 초기화
    shortestTable = new int[n+1];
    Arrays.fill(shortestTable, INF);

    //graph 초기화
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < edge.length; i++) {
      int a = edge[i][0];
      int b = edge[i][1];
      graph.get(a).add(new Node(b, 1));
      graph.get(b).add(new Node(a, 1));
    }

    //다익스트라
    dij(1);

    //정답 출력
    int answer = (int) Arrays.stream(shortestTable).filter(v -> v == longestDistance).count();

    return answer;
  }

  private void dij(int startIndex) {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    queue.offer(new Node(startIndex, 0));
    shortestTable[startIndex] = 0;

    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();
      int currentIndex = currentNode.index;
      int currentDistance = currentNode.distance;

      if (shortestTable[currentIndex] < currentDistance) continue;

      List<Node> nearNodeList = graph.get(currentIndex);
      for (Node nearNode : nearNodeList) {
        int nearIndex = nearNode.index;
        int distanceOfCurrentToNear = nearNode.distance;

        if (shortestTable[nearIndex] > currentDistance + distanceOfCurrentToNear) {
          shortestTable[nearIndex] = currentDistance + distanceOfCurrentToNear;
          queue.offer(new Node(nearIndex, shortestTable[nearIndex]));
          longestDistance = Math.max(longestDistance, shortestTable[nearIndex]);
        }
      }
    }
  }

  class Node implements Comparable<Node> {
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