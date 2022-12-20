import java.util.*;

class Solution {
  static final int INF = (int) 1e9;
  int[][] countMap;
  int n; //세로 길이
  int m; //가로 길이
  int[] dc = {-1, 1, 0, 0};
  int[] dr = {0, 0, -1, 1};

  public int solution(int[][] maps) {
    n = maps.length;
    m = maps[0].length;

    //countMap 초기화
    countMap = new int[n][m];
    for (int i = 0; i < n; i++) {
      Arrays.fill(countMap[i], INF);
    }

    //bfs 탐색
    bfs(0, 0, maps);

    //정답 출력
    if (countMap[n-1][m-1] == INF) {
      return -1;
    }
    return countMap[n-1][m-1];
  }

  private void bfs(int startX, int startY, int[][] maps) {
    Deque<int[]> deque = new ArrayDeque<>();
    countMap[startY][startX] = 1;
    deque.offerLast(new int[] {startX, startY});

    while (!deque.isEmpty()) {
      int[] current = deque.pollFirst();
      int currentX = current[0];
      int currentY = current[1];

      for (int i = 0; i < 4; i++) {
        int nearX = currentX + dc[i];
        int nearY = currentY + dr[i];

        if (nearX < 0 || nearX >= m) continue; //맵 밖으로 나간 경우
        if (nearY < 0 || nearY >= n) continue; //맵 밖으로 나간 경우
        if (maps[nearY][nearX] == 0) continue; //벽이 있는 경우
        if (countMap[currentY][currentX] + 1 >= countMap[nearY][nearX]) continue; //기존에 기록된 '다음 칸까지의 이동횟수'가 더 작다면

        countMap[nearY][nearX] = countMap[currentY][currentX] + 1;
        deque.offerLast(new int[] {nearX, nearY});
      }
    }
  }
}