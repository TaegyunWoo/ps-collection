class Solution {
  int totalSize;
  int[][] mapArr;
  int[] rowFillCount;
  String[] direction = {"DOWN", "RIGHT", "UP", "LEFT"};
  int directionIdx = 0;

  public int[] solution(int n) {
    mapArr = new int[n][n];
    rowFillCount = new int[n];

    totalSize = getTotalSize(n);

    //계산
    int rotateCount = 1;
    int r = 0;
    int c = 0;
    mapArr[0][0] = 1;
    rowFillCount[0] = 1;
    for (int i = 2; i <= totalSize; i++) {
      switch (direction[directionIdx]) {
        case "DOWN":
          if (r == n - 1 || mapArr[r+1][c] != 0) {
            changeDirection();
            i--;
            continue;
          }
          r++;
          mapArr[r][c] = i;
          rowFillCount[r] += 1;
          break;
        case "RIGHT":
          if (rowFillCount[r] == r + 1) {
            changeDirection();
            i--;
            continue;
          }
          c++;
          mapArr[r][c] = i;
          rowFillCount[r] += 1;
          break;
        case "UP":
          if (rowFillCount[r-1] == r) {
            changeDirection();
            i--;
            continue;
          }
          r--;
          mapArr[r][c] = i;
          rowFillCount[r] += 1;
          break;
        case "LEFT":
          if (rowFillCount[r] == r + 1) {
            changeDirection();
            c = rotateCount;
            i--;
            rotateCount++; //회전횟수 증가
            continue;
          }
          c--;
          mapArr[r][c] = i;
          rowFillCount[r] += 1;
          break;
      }
    }

    int[] answer = new int[totalSize];
    int answerIdx = 0;
    for (int i = 0; i < mapArr.length; i++) {
      for (int u = 0; u < mapArr[i].length; u++) {
        if (mapArr[i][u] == 0) continue;
        answer[answerIdx++] = mapArr[i][u];
      }
    }

    return answer;
  }

  private int getTotalSize(int n) {
    int result = 0;
    for (int i = 1; i <= n; i++) {
      result += i;
    }
    return result;
  }

  private void changeDirection() {
    directionIdx = (directionIdx + 1) % 4;
  }
}