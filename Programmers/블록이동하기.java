import java.util.*;

class Solution {
  int[] dr = {-1, 1, 0, 0};
  int[] dc = {0, 0, -1, 1};

  public int solution(int[][] board) {
    int answer = bfs(board, new Info(0, 0, 0, 1, 0, false));

    return answer;
  }

  private int bfs(int[][] board, Info start) {
    Deque<Info> deque = new ArrayDeque<>();
    boolean[][][] visited = new boolean[board.length][board[0].length][2];
    deque.offerLast(start);

    while (!deque.isEmpty()) {
      Info current = deque.pollFirst();

      //보드 밖으로 나가는 경우
      if (!isAtBoard(board, current.leftR, current.leftC)) continue;
      if (!isAtBoard(board, current.rightR, current.rightC)) continue;

      //벽으로 막힌 경우
      if (board[current.leftR][current.leftC] == 1 || board[current.rightR][current.rightC] == 1) continue;

      if (current.isVertical) { //세로 상태인 경우
        //이미 현재상태(세로)로 왼쪽 날개 위치와 오른쪽 날개 위치를 모두 방문했었다면
        if (visited[current.leftR][current.leftC][1] && visited[current.rightR][current.rightC][1]) continue;

        //방문
        visited[current.leftR][current.leftC][1] = true;
        visited[current.rightR][current.rightC][1] = true;

      } else { //가로 상태인 경우
        //이미 현재상태(가로)로 왼쪽 날개 위치와 오른쪽 날개 위치를 모두 방문했었다면
        if (visited[current.leftR][current.leftC][0] && visited[current.rightR][current.rightC][0]) continue;

        //방문
        visited[current.leftR][current.leftC][0] = true;
        visited[current.rightR][current.rightC][0] = true;
      }

      //목적지에 도착했다면
      if (current.leftR == board.length-1 && current.leftC == board[0].length-1) return current.time;
      if (current.rightR == board.length-1 && current.rightC == board[0].length-1) return current.time;

      //단순히 상하좌우로 이동하는 경우
      for (int i = 0; i < 4; i++) {
        int nextLeftR = current.leftR + dr[i];
        int nextLeftC = current.leftC + dc[i];
        int nextRightR = current.rightR + dr[i];
        int nextRightC = current.rightC + dc[i];
        int nextTime = current.time + 1;

        Info next = new Info(nextLeftR, nextLeftC, nextRightR, nextRightC, nextTime, current.isVertical);
        deque.offerLast(next);
      }

      //회전하는 경우
      if (current.isVertical) { //세로 상태라면
        int nextLeftR = current.leftR; //왼쪽 날개를 기준
        int nextLeftC = current.leftC; //왼쪽 날개를 기준
        int nextRightR = current.leftR;
        int nextRightCLeft = current.leftC - 1; //오른쪽 날개를 좌로 회전
        int nextRightCRight = current.leftC + 1; //오른쪽 날개를 우로 회전
        int nextTime = current.time + 1;

        //오른쪽 날개를 좌로 회전했을 때, 회전이 가능하다면
        if (nextRightCLeft >= 0 && board[current.rightR][nextRightCLeft] != 1) {
          Info next = new Info(nextLeftR, nextLeftC, nextRightR, nextRightCLeft, nextTime, false);
          deque.offerLast(next);
        }

        //오른쪽 날개를 우로 회전했을 때, 회전이 가능하다면
        if (nextRightCRight < board[0].length && board[current.rightR][nextRightCRight] != 1) {
          Info next = new Info(nextLeftR, nextLeftC, nextRightR, nextRightCRight, nextTime, false);
          deque.offerLast(next);
        }

        //오른쪽 날개를 기준으로 했을 때
        nextLeftR = current.rightR;
        int nextLeftCLeft = current.rightC - 1; //왼쪽 날개를 좌로 회전
        int nextLeftCRight = current.rightC + 1; //왼쪽 날개를 우로 회전
        nextRightR = current.rightR; //오른쪽 날개를 기준
        int nextRightC = current.rightC; //오른쪽 날개를 기준

        //왼쪽 날개를 좌로 회전했을 때, 회전이 가능하다면
        if (nextLeftCLeft >= 0 && board[current.leftR][nextLeftCLeft] != 1) {
          Info next = new Info(nextLeftR, nextLeftCLeft, nextRightR, nextRightC, nextTime, false);
          deque.offerLast(next);
        }

        //왼쪽 날개를 우로 회전했을 때, 회전이 가능하다면
        if (nextLeftCRight < board[0].length && board[current.leftR][nextLeftCRight] != 1) {
          Info next = new Info(nextLeftR, nextLeftCRight, nextRightR, nextRightC, nextTime, false);
          deque.offerLast(next);
        }

      } else { //가로 상태라면
        int nextLeftR = current.leftR; //왼쪽 날개를 기준
        int nextLeftC = current.leftC; //왼쪽 날개를 기준
        int nextRightRDown = current.leftR + 1; //오른쪽 날개를 아래로 회전
        int nextRightRUp = current.leftR - 1; //오른쪽 날개를 위로 회전
        int nextRightC = current.leftC;
        int nextTime = current.time + 1;

        //오른쪽 날개를 아래로 회전했을 때, 회전이 가능하다면
        if (nextRightRDown < board.length && board[nextRightRDown][current.rightC] != 1) {
          Info next = new Info(nextLeftR, nextLeftC, nextRightRDown, nextRightC, nextTime, true);
          deque.offerLast(next);
        }

        //오른쪽 날개를 위로 회전했을 때, 회전이 가능하다면
        if (nextRightRUp >= 0 && board[nextRightRUp][current.rightC] != 1) {
          Info next = new Info(nextLeftR, nextLeftC, nextRightRUp, nextRightC, nextTime, true);
          deque.offerLast(next);
        }

        //오른쪽 날개를 기준으로 했을 때
        int nextLeftRDown = current.rightR + 1; //왼쪽 날개를 아래로 회전
        int nextLeftRUp = current.rightR - 1; //왼쪽 날개를 위로 회전
        nextLeftC = current.rightC;
        int nextRightR = current.rightR; //오른쪽 날개를 기준
        nextRightC = current.rightC; //오른쪽 날개를 기준

        //왼쪽 날개를 아래로 회전했을 때, 회전이 가능하다면
        if (nextLeftRDown < board.length && board[nextLeftRDown][current.leftC] != 1) {
          Info next = new Info(nextLeftRDown, nextLeftC, nextRightR, nextRightC, nextTime, true);
          deque.offerLast(next);
        }

        //오른쪽 날개를 위로 회전했을 때, 회전이 가능하다면
        if (nextLeftRUp >= 0 && board[nextLeftRUp][current.leftC] != 1) {
          Info next = new Info(nextLeftRUp, nextLeftC, nextRightR, nextRightC, nextTime, true);
          deque.offerLast(next);
        }
      }

    }

    return -1;
  }

  private boolean isAtBoard(int[][] board, int r, int c) {
    if (r < 0 || r >= board.length) return false;
    if (c < 0 || c >= board[0].length) return false;
    return true;
  }

  public class Info {
    public int leftR;
    public int leftC;
    public int rightR;
    public int rightC;
    public int time;
    public boolean isVertical;
    public Info(int leftR, int leftC, int rightR, int rightC, int time, boolean isVertical) {
      this.leftR = leftR;
      this.leftC = leftC;
      this.rightR = rightR;
      this.rightC = rightC;
      this.time = time;
      this.isVertical = isVertical;
    }
  }
}