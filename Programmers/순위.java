/**
 * 핵심 아이디어: 등수를 어떻게 알아낼 것인가?
 *
 * [등수를 알 수 있다고 판단하는 방법]
 * 'i를 시작으로 정방향으로 그래프를 탐색했을 때 방문하는 노드 개수' = 선수 i가 이긴 선수 수
 * 'i를 시작으로 역방향으로 그래프를 탐색했을 때 방문하는 노드 개수' = 선수 i가 진 선수 수
 *
 * 'i를 시작으로 정방향으로 그래프를 탐색했을 때 방문하는 노드 개수' +
 * 'i를 시작으로 역방향으로 그래프를 탐색했을 때 방문하는 노드 개수' = n - 1
 * 인 경우, 등수를 알 수 있음
 */

import java.util.*;

class Solution {
  ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); //정방향
  ArrayList<ArrayList<Integer>> reverseGraph = new ArrayList<>(); //역방향

  public int solution(int n, int[][] results) {
    int answer = 0;

    //그래프 초기화
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
      reverseGraph.add(new ArrayList<>());
    }
    for (int i = 0; i < results.length; i++) {
      int a = results[i][0];
      int b = results[i][1];
      graph.get(a).add(b); //정방향
      reverseGraph.get(b).add(a); //역방향
    }

    //n만큼 반복
    for (int i = 1; i <= n; i++) {
      int winCount = bfs(i, n, false);
      int loseCount = bfs(i, n, true);
      if (winCount + loseCount == n - 1) answer++;
    }

    return answer;
  }

  private int bfs(int startIndex, int n, boolean reverse) {
    int visitNodeCount = 0;
    Deque<Integer> deque = new ArrayDeque<>();
    boolean[] visited = new boolean[n+1];
    deque.addLast(startIndex);
    visited[startIndex] = true;

    while (!deque.isEmpty()) {
      int currentIndex = deque.pollFirst();
      List<Integer> nearList = null;

      if (reverse) {
        nearList = reverseGraph.get(currentIndex);
      } else {
        nearList = graph.get(currentIndex);
      }

      for (int nearIndex : nearList) {
        if (visited[nearIndex]) continue;
        visited[nearIndex] = true;
        deque.addLast(nearIndex);
        visitNodeCount++;
      }
    }

    return visitNodeCount;
  }
}