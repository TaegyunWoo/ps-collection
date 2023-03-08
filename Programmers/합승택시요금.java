import java.util.*;

class Solution {
  public static final int INF = (int) 1e9;
  List<List<Node>> graph = new ArrayList<>();
  int[][] totalShortestTable;
  int answer = INF;

  public int solution(int n, int s, int a, int b, int[][] fares) {
    //그래프 초기화
    initGraph(n, fares);

    //최단거리 테이블 초기화
    initTotalShortestTable(n);

    //다익스트라
    dij(s, 0); //s에서 출발하는 경우
    dij(a, 1); //a에서 출발하는 경우 ( == a에 도착하는 경우 -> 양방향이기 때문에 )
    dij(b, 2); //b에서 출발하는 경우 ( == b에 도착하는 경우 -> 양방향이기 때문에 )

    //각 노드까지 합승하는 경우, 총 택시요금 계산 (s에서 합승 후 breakIndex에 내려서, 각자 따로 택시타고 a,b로 가는 것)
    for (int breakIndex = 1; breakIndex <= n; breakIndex++) {
      int sToBreakIndex = totalShortestTable[0][breakIndex]; //(s -> breakIndex)
      int breakIndexToA = totalShortestTable[1][breakIndex]; //(breakIndex -> a)
      int breakIndexToB = totalShortestTable[2][breakIndex]; //(breakIndex -> b)

      //만약 breakIndex에 도달하지 못하는 경우
      if (sToBreakIndex == INF || breakIndexToA == INF || breakIndexToB == INF) continue;

      int totalFare = sToBreakIndex + breakIndexToA + breakIndexToB;

      answer = Math.min(answer, totalFare);
    }

    return answer;
  }

  private void dij(int start, int shortestTableIndex) {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    int[] shortestTable = totalShortestTable[shortestTableIndex];
    shortestTable[start] = 0;

    queue.offer(new Node(start, 0));

    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();
      int currentIndex = currentNode.index;
      int fareStartToCurrent = currentNode.fare;

      if (shortestTable[currentIndex] < fareStartToCurrent) continue;

      List<Node> nearNodeList = graph.get(currentIndex);
      for (Node nearNode : nearNodeList) {
        int nearIndex = nearNode.index;
        int fareCurrentToNear = nearNode.fare;
        int fareStartToNear = fareStartToCurrent + fareCurrentToNear;

        if (shortestTable[nearIndex] > fareStartToNear) {
          shortestTable[nearIndex] = fareStartToNear;
          queue.offer(new Node(nearIndex, fareStartToNear));
        }
      }
    }
  }

  private void initGraph(int n, int[][] fares) {
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] fareAry : fares) {
      int a = fareAry[0];
      int b = fareAry[1];
      int fare = fareAry[2];
      graph.get(a).add(new Node(b, fare)); //양방향
      graph.get(b).add(new Node(a, fare)); //양방향
    }
  }

  private void initTotalShortestTable(int n) {
    totalShortestTable = new int[3][n+1];
    for (int i = 0; i <= n; i++) {
      totalShortestTable[0][i] = INF;
      totalShortestTable[1][i] = INF;
      totalShortestTable[2][i] = INF;
    }
  }

  class Node implements Comparable<Node> {
    public int index;
    public int fare;

    public Node(int i, int f) {
      this.index = i;
      this.fare = f;
    }

    @Override
    public int compareTo(Node other) {
      return this.fare - other.fare;
    }
  }
}