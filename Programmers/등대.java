import java.util.*;

class Solution {
  List<List<Integer>> graph = new ArrayList<>();
  boolean[] light;
  int root = 1;
  int answer = 0;

  public int solution(int n, int[][] lighthouse) {
    //init
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < lighthouse.length; i++) {
      graph.get(lighthouse[i][0]).add(lighthouse[i][1]);
      graph.get(lighthouse[i][1]).add(lighthouse[i][0]); //양방향
    }
    light = new boolean[n+1];

    //dfs
    dfs(root, 0);

    //정답
    for (int i = 1; i < light.length; i++)
      if (light[i]) answer++;

    return answer;
  }

  /*
   * current : 현재 노드
   * before : 직전 노드
   */
  private int dfs(int current, int before) {
    //만약 리프노드라면
    if (graph.get(current).size() == 1 && graph.get(current).get(0) == before) {
      return 0; //현재 노드 점등 X
    }

    int resultSum = 0;
    for (int next : graph.get(current)) {
      if (next == before) continue;
      resultSum += dfs(next, current);
    }

    if (current != root) { //루트노드가 아니고
      if (resultSum != graph.get(current).size() - 1) { //자식 노드가 하나라도 점등되어 있지 않다면
        //현재 노드 점등
        light[current] = true;
        return 1;

      } else { //모든 자식 노드가 점등되어 있다면
        return 0; //현재 노드 점등 X
      }

    } else { //루트노드라면
      if (resultSum != graph.get(current).size()) { //자식 노드가 하나라도 점등되어 있지 않다면
        //현재 노드 점등
        light[current] = true;
        return 1;

      } else { //모든 자식 노드가 점등되어 있다면
        return 0; //현재 노드 점등 X
      }
    }

  }
}