import java.util.*;

class Solution {
  String[] maps;
  int[] dr = {-1, 1, 0, 0};
  int[] dc = {0, 0, -1, 1};
  boolean[][] visited;
  List<Integer> answerList = new ArrayList<>();

  public int[] solution(String[] m) {
    maps = m;
    visited = new boolean[maps.length][maps[0].length()];

    //계산
    for (int i = 0; i < maps.length; i++) {
      for (int u = 0; u < maps[i].length(); u++) {
        if (maps[i].charAt(u) == 'X') continue;
        if (visited[i][u]) continue;
        answerList.add(bfs(new int[] {i, u}));
      }
    }

    //정렬
    answerList.sort(Comparator.naturalOrder());

    if (answerList.size() == 0) return new int[] {-1};
    return answerList.stream().mapToInt(i->i).toArray();
  }

  private int bfs(int[] start) {
    int result = maps[start[0]].charAt(start[1]) - '0';
    Deque<int[]> deque = new ArrayDeque<>();
    visited[start[0]][start[1]] = true;
    deque.offerLast(start);

    while (!deque.isEmpty()) {
      int[] current = deque.pollFirst();
      for (int i = 0; i < 4; i++) {
        int nextR = current[0] + dr[i];
        int nextC = current[1] + dc[i];

        if (nextR < 0 || nextR >= maps.length) continue;
        if (nextC < 0 || nextC >= maps[0].length()) continue;
        if (maps[nextR].charAt(nextC) == 'X') continue;
        if (visited[nextR][nextC]) continue;

        result += maps[nextR].charAt(nextC) - '0';
        visited[nextR][nextC] = true;
        deque.offerLast(new int[] {nextR, nextC});
      }
    }

    return result;
  }
}