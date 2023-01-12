import java.util.*;

class Solution {
  List<Set<Integer>> allComResultList = new ArrayList<>();

  public int solution(String[][] relation) {
    for (int r = 1; r < relation[0].length + 1; r++) {
      com(0, relation[0].length, r, relation, new boolean[relation[0].length]);
    }

    removeNotMinResult();

    int answer = allComResultList.size();
    return answer;
  }

  private void com(int depth, int n, int r, String[][] relation, boolean[] visited) {
    if (r == 0) {
      addUniqueComResult(visited, relation);
      return;
    }
    if (depth == n) return;

    visited[depth] = true;
    com(depth+1, n, r-1, relation, visited);

    visited[depth] = false;
    com(depth+1, n, r, relation, visited);
  }

  private void addUniqueComResult(boolean[] visited, String[][] relation) {
    Set<Integer> result = new HashSet<>();

    Set<String> valueSet = new HashSet<>();
    for (int i = 0; i < relation.length; i++) {
      StringBuilder sb = new StringBuilder();
      for (int u = 0; u < relation[i].length; u++) {
        if (visited[u]) {
          sb.append(relation[i][u] + " ");
        }
      }
      if (valueSet.contains(sb.toString())) return;
      valueSet.add(sb.toString());
    }

    for (int i = 0; i < visited.length; i++) {
      if (visited[i]) {
        result.add(i);
      }
    }

    allComResultList.add(result);
  }

  private void removeNotMinResult() {
    for (int i = 0; i < allComResultList.size(); i++) {
      Set<Integer> resultA = allComResultList.get(i);
      if (resultA.size() == 0) continue;

      for (int u = 0; u < allComResultList.size(); u++) {
        Set<Integer> resultB = allComResultList.get(u);
        if (resultA == resultB) continue;
        if (resultB.containsAll(resultA)) {
          resultB.clear();
        }
      }
    }
    for (int i = 0; i < allComResultList.size(); i++) {
      if (allComResultList.get(i).isEmpty()) allComResultList.remove(i--);
    }
  }
}