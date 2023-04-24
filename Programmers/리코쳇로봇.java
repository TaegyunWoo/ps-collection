import java.util.*;

class Solution {
  public int solution(String[] board) {
    //get start point
    int[] start = new int[2];
    for (int i = 0; i < board.length; i++) {
      for (int u = 0; u < board[i].length(); u++) {
        if (board[i].charAt(u) == 'R') {
          start[0] = i;
          start[1] = u;
        }
      }
    }

    //bfs
    return bfs(start, board);
  }

  private int bfs(int[] start, String[] board) {
    int[][] countAry = new int[board.length][board[0].length()];
    Deque<int[]> deque = new ArrayDeque<>();
    deque.offerLast(start);
    for (int i = 0; i < countAry.length; i++)
      Arrays.fill(countAry[i], -1);
    countAry[start[0]][start[1]] = 0;

    while (!deque.isEmpty()) {
      int[] current = deque.pollFirst();
      int[][] nexts = move(current, board);

      for (int[] next : nexts) {
        if (countAry[next[0]][next[1]] != -1) continue;

        countAry[next[0]][next[1]] = countAry[current[0]][current[1]] + 1;
        deque.offerLast(next);

        if (board[next[0]].charAt(next[1]) == 'G')
          return countAry[next[0]][next[1]];
      }
    }

    return -1;
  }

  private int[][] move(int[] current, String[] board) {
    //LEFT
    int left = 0;
    for (int i = current[1]; i >= 0; i--) {
      if (board[current[0]].charAt(i) == 'D') {
        left = i + 1;
        break;
      }
    }
    //RIGHT
    int right = board[0].length() - 1;
    for (int i = current[1]; i < board[0].length(); i++) {
      if (board[current[0]].charAt(i) == 'D') {
        right = i - 1;
        break;
      }
    }
    //UP
    int up = 0;
    for (int i = current[0]; i >= 0; i--) {
      if (board[i].charAt(current[1]) == 'D') {
        up = i + 1;
        break;
      }
    }
    //DOWN
    int down = board.length - 1;
    for (int i = current[0]; i < board.length; i++) {
      if (board[i].charAt(current[1]) == 'D') {
        down = i - 1;
        break;
      }
    }

    return new int[][] {{current[0], left}, {current[0], right}, {up, current[1]}, {down, current[1]}};
  }
}