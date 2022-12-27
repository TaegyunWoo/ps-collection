/**
 * 크루스칼 알고리즘 활용 풀이
 */

import java.util.*;

class Solution {
  int[] cycleTable;
  PriorityQueue<Edge> edgeQueue = new PriorityQueue<>();
  int answer = 0;

  public int solution(int n, int[][] costs) {
    //사이클테이블 초기화
    cycleTable = new int[n];
    for (int i = 0; i < n; i++) {
      cycleTable[i] = i;
    }

    //엣지 정렬
    for (int i = 0; i < costs.length; i++) {
      edgeQueue.offer(new Edge(costs[i][0], costs[i][1], costs[i][2]));
      edgeQueue.offer(new Edge(costs[i][1], costs[i][0], costs[i][2]));
    }

    //kruskal algo.
    for (int i = 0; i < n-1; i++) { //간선의 개수는 n-1개만 있으면 된다. (최소신장트리이기 때문에)
      Edge minEdge = edgeQueue.poll();

      //해당 엣지를 선택했을 때
      if ( find(minEdge.indexA) == find(minEdge.indexB) ) { //사이클을 이룬다면
        i--;

      } else { //사이클을 이루지 않는다면
        merge(minEdge.indexA, minEdge.indexB);
        answer += minEdge.cost;
      }
    }

    return answer;
  }

  private int find(int index) {
    if (index == cycleTable[index])
      return index;
    return find(cycleTable[index]);
  }

  private void merge(int indexA, int indexB) {
    int parentA = find(indexA);
    int parentB = find(indexB);

    if (parentA == parentB) return;

    if (parentA < parentB) {
      cycleTable[parentB] = parentA;
    } else {
      cycleTable[parentA] = parentB;
    }
  }

  class Edge implements Comparable<Edge> {
    public int indexA;
    public int indexB;
    public int cost;

    public Edge(int indexA, int indexB, int cost) {
      this.indexA = indexA;
      this.indexB = indexB;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge other) {
      if (this.cost < other.cost) return -1;
      return 1;
    }
  }
}