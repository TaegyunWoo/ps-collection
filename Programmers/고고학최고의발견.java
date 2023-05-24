class Solution {
  int[][] clockHands;
  int[] dr = {0, -1, 1, 0, 0};
  int[] dc = {0, 0, 0, -1, 1};
  int answer = (int) 1e9;

  public int solution(int[][] c) {
    clockHands = c;

    brute(0, 0);

    return answer;
  }

  private void brute(int currentC, int count) {
    if (currentC == clockHands.length) {
      //정답 계산
      int result = getTotalCount(count);
      if (result != -1) answer = Math.min(answer, result);
      return;
    }

    brute(currentC+1, count); // (0, currentC) 회전 X

    for (int i = 1; i < 4; i++) {
      turn(0, currentC, clockHands); // (0, currentC)를 한번 회전
      brute(currentC+1, count + i); // 다음 c
    }

    turn(0, currentC, clockHands); // 한번 더 회전시켜서 기존 상태로 복구
  }

  private void turn(int r, int c, int[][] clockHands) {
    for (int i = 0; i < 5; i++) { //현재 r, c 포함
      int nearR = r + dr[i];
      int nearC = c + dc[i];

      if (nearR < 0 || nearR >= clockHands.length) continue;
      if (nearC < 0 || nearC >= clockHands.length) continue;

      clockHands[nearR][nearC] = (clockHands[nearR][nearC] + 1) % 4;
    }
  }

  private int getTotalCount(int firstRowCount) {
    int result = 0;
    int[][] tmpClockHands = new int[clockHands.length][clockHands.length];
    for (int i = 0; i < tmpClockHands.length; i++) {
      for (int u = 0; u < tmpClockHands.length; u++) {
        tmpClockHands[i][u] = clockHands[i][u];
      }
    }

    for (int r = 1; r < tmpClockHands.length; r++) {
      for (int c = 0; c < tmpClockHands.length; c++) {
        while (tmpClockHands[r-1][c] != 0) {
          turn(r, c, tmpClockHands);
          result++;
        }
      }
    }

    for (int i = 0; i < tmpClockHands.length; i++) {
      if (tmpClockHands[tmpClockHands.length-1][i] != 0) return -1;
    }

    return result + firstRowCount;
  }
}