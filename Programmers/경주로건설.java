import java.util.*;

class Solution {
  public static final int INF = (int) 1e9;
  public int[][] board;
  //int[a][b][0] = (a,b)에 도로를 '하->상' 방향으로 놓았을 때, 총 최소 비용
  //int[a][b][1] = (a,b)에 도로를 '상->하' 방향으로 놓았을 때, 총 최소 비용
  //int[a][b][2] = (a,b)에 도로를 '우->좌' 방향으로 놓았을 때, 총 최소 비용
  //int[a][b][3] = (a,b)에 도로를 '좌->우' 방향으로 놓았을 때, 총 최소 비용
  public int[][][] priceAry;
  public int[] dr = {-1, 1, 0, 0};
  public int[] dc = {0, 0, -1, 1};
  public int answer = INF;

  public int solution(int[][] b) {
    //init
    board = b;
    priceAry = new int[board.length][board.length][4];
    for (int i = 0; i < board.length; i++) {
      for (int u = 0; u < board.length; u++) {
        priceAry[i][u][0] = INF;
        priceAry[i][u][1] = INF;
        priceAry[i][u][2] = INF;
        priceAry[i][u][3] = INF;
      }
    }

    //(0,0)에서 아래쪽으로 시작하는 경우
    bfs();

    return answer;
  }

  private void bfs() {
    Deque<int[]> deque = new ArrayDeque<>(); //{r, c, 방향}
    priceAry[0][0][0] = 0;
    priceAry[0][0][1] = 0;
    priceAry[0][0][2] = 0;
    priceAry[0][0][3] = 0;

    deque.offerLast(new int[] {0, 0, 0});

    while (!deque.isEmpty()) {
      int[] current = deque.pollFirst();
      int currentR = current[0];
      int currentC = current[1];
      int currentDir = current[2];

      //만약 목적지에 도착했다면 중지
      if (currentR == board.length - 1 && currentC == board.length - 1) {
        answer = Math.min(answer, priceAry[currentR][currentC][currentDir]);
        continue;
      }

      for (int i = 0; i < 4; i++) {
        int nextR = currentR + dr[i];
        int nextC = currentC + dc[i];

        if (nextR < 0 || nextR >= board.length) continue;
        if (nextC < 0 || nextC >= board.length) continue;
        if (board[nextR][nextC] == 1) continue;

        if ((currentR == 0 && currentC == 0) || currentDir == i) { //코너 X (이전과 방향이 같은 경우)
          if (priceAry[nextR][nextC][i] >= priceAry[currentR][currentC][currentDir] + 100) {
            priceAry[nextR][nextC][i] = priceAry[currentR][currentC][currentDir] + 100;
            deque.offerLast(new int[] {nextR, nextC, i});
          }
        } else { //코너 O (이전과 방향이 다른 경우)
          if (priceAry[nextR][nextC][i] >= priceAry[currentR][currentC][currentDir] + 600) {
            priceAry[nextR][nextC][i] = priceAry[currentR][currentC][currentDir] + 600;
            deque.offerLast(new int[] {nextR, nextC, i});
          }
        }
      }
    }
  }
}
