class Solution {
  public int solution(String[] board) {
    //count
    int countO = 0;
    int countX = 0;
    for (int i = 0; i < 3; i++) {
      for (int u = 0; u < 3; u++) {
        if (board[i].charAt(u) == 'O') countO++;
        else if (board[i].charAt(u) == 'X') countX++;
      }
    }

    //check
    //'O의 개수 - 1의 개수'가 0이나 1이 아닌 경우
    if (countO - countX != 0 && countO - countX != 1) return 0;

    boolean oWin = isWin('O', board);
    boolean xWin = isWin('X', board);

    //모두 이긴 경우
    if (oWin && xWin) return 0;

    //O이 이기고, X의 개수와 O의 개수가 같은 경우
    if (oWin && countX == countO) return 0;

    //X가 이기고, O의 개수가 X의 개수보다 1만큼 더 많은 경우
    if (xWin && countO - countX == 1) return 0;

    return 1;
  }

  private boolean isWin(char c, String[] board) {
    boolean flag = true;

    for (int i = 0; i < 3; i++) {
      flag = true;

      //가로 3칸
      for (int u = 0; u < 3; u++) {
        if (board[i].charAt(u) != c) flag = false;
      }
      if (flag) return true;

      flag = true;

      //세로 3칸
      for (int u = 0; u < 3; u++) {
        if (board[u].charAt(i) != c) flag = false;
      }
      if (flag) return true;
    }

    //대각선 3칸
    if (board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c) return true;
    if (board[0].charAt(2) == c && board[1].charAt(1) == c && board[2].charAt(0) == c) return true;

    return false;
  }
}