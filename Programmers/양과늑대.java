import java.util.*;

/**
 * [완전탐색 (백트래킹)]
 * 핵심 개념 : 각 노드를 방문할 때마다, **현재까지 방문한 노드에서 갈 수 있는 모든 노드**를 확인
 *          (현재 노드에서 갈 수 있는 노드에 국한 X, 현재까지 방문한 노드에서 갈 수 있는 노드 O)
 */
class Solution {
  List<Integer> sheepCountList = new ArrayList<>();
  boolean[] visited = new boolean[17];

  public int solution(int[] info, int[][] edges) {
    visited[0] = true;
    backTracking(1, 0, info, edges);

    return sheepCountList.stream().mapToInt(i->i).max().getAsInt();
  }

  private void backTracking(int sheep, int wolf, int[] info, int[][] edges) {
    if (sheep > wolf) sheepCountList.add(sheep);
    else return;

    for (int i = 0; i < edges.length; i++) {
      int parent = edges[i][0];
      int child = edges[i][1];
      boolean isNextWolf = info[child] == 1;

      if (!visited[parent]) continue; //건너뛰어서 방문하는 경우
      if (visited[child]) continue; //방문했던 곳을 다시 방문하려는 경우

      visited[child] = true; //다음 노드 방문처리
      if (isNextWolf) {
        backTracking(sheep, wolf+1, info, edges);
      } else {
        backTracking(sheep+1, wolf, info, edges);
      }
      visited[child] = false; //다음 노드 방문처리 해제
    }
  }
}