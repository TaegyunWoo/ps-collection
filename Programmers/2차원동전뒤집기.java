class Solution {
  static final int INF = (int) 1e9;
  int[][] beginning;
  int[][] target;
  boolean[] combinationArray;
  int answer = INF;

  public int solution(int[][] b, int[][] t) {
    beginning = b;
    target = t;
    combinationArray = new boolean[beginning.length];

    for (int row = 0; row <= beginning.length; row++) {
      combine(0, row, row);
    }

    return answer == INF ? -1 : answer;
  }

  private void combine(int depth, int r, int countRow) {
    if (r == 0) {
      int countCol = checkCol();
      if (countCol != -1)
        answer = Math.min(answer, countRow + countCol);
      return;
    }
    if (depth == combinationArray.length) {
      return;
    }

    combinationArray[depth] = true;
    combine(depth+1, r-1, countRow);

    combinationArray[depth] = false;
    combine(depth+1, r, countRow);
  }

  private int checkCol() {
    int result = 0;

    for (int col = 0; col < beginning[0].length; col++) {
      boolean isSameWithTarget = true;

      //col이 모두 같은지
      for (int row = 0; row < beginning.length; row++) {
        if (combinationArray[row]) { //뒤집을 행이라면
          if (beginning[row][col] == target[row][col]) isSameWithTarget = false;
        } else { //안뒤집을 행이라면
          if (beginning[row][col] != target[row][col]) isSameWithTarget = false;
        }
      }

      if (isSameWithTarget) continue;

      isSameWithTarget = true;

      //col이 모두 다른지
      for (int row = 0; row < beginning.length; row++) {
        if (combinationArray[row]) { //뒤집을 행이라면
          if (beginning[row][col] != target[row][col]) isSameWithTarget = false;
        } else { //안뒤집을 행이라면
          if (beginning[row][col] == target[row][col]) isSameWithTarget = false;
        }
      }

      if (!isSameWithTarget) {
        return -1;
      }

      result++; //뒤집어야 하는 col 세기
    }

    return result;
  }
}