class Solution {
  int[][] array;
  int min = (int) 1e9;

  public int[] solution(int rows, int columns, int[][] queries) {
    //init
    array = new int[rows][columns];
    int num = 1;
    for (int i = 0; i < rows; i++) {
      for (int u = 0; u < columns; u++) {
        array[i][u] = num++;
      }
    }

    //make zero-base
    for (int i = 0; i < queries.length; i++) {
      for (int u = 0; u < queries[0].length; u++) {
        queries[i][u]--;
      }
    }

    //get answer
    int[] answer = new int[queries.length];
    int idx = 0;
    for (int[] query : queries) {
      min = (int) 1e9; //최솟값 초기화

      try {
        spin(query); //회전
      } catch (Exception e) {
        return null;
      }

      answer[idx++] = min; //정답 저장
    }

    return answer;
  }

  private void spin(int[] query) {
    int x1 = query[0];
    int y1 = query[1];
    int x2 = query[2];
    int y2 = query[3];
    int lastNum = array[x1+1][y1]; //좌변 마지막 값

    lastNum = top(x1, y1, y2, lastNum); //윗변 회전
    lastNum = right(x1, x2, y2, lastNum); //우변 회전
    lastNum = bottom(x2, y1, y2, lastNum); //아랫변 회전
    left(x1, x2, y1, lastNum); //좌변 회전
  }

  //윗변 시계방향으로 밀기 (return = 가장 마지막 값)
  private int top(int x, int y1, int y2, int firstValue) {
    int beforeValue = array[x][y1];

    array[x][y1] = firstValue;

    for (int i = y1 + 1; i <= y2; i++) {
      int tmp = array[x][i];
      array[x][i] = beforeValue;

      min = Math.min(min, beforeValue); //최솟값 업데이트

      beforeValue = tmp;
    }

    min = Math.min(min, beforeValue); //최솟값 업데이트

    return beforeValue;
  }

  //아랫변 시계방향으로 밀기 (return = 가장 마지막 값)
  private int bottom(int x, int y1, int y2, int firstValue) {
    int beforeValue = array[x][y2-1];

    array[x][y2-1] = firstValue;
    for (int i = y2 - 2; i >= y1; i--) {
      int tmp = array[x][i];
      array[x][i] = beforeValue;

      min = Math.min(min, beforeValue); //최솟값 업데이트

      beforeValue = tmp;
    }

    min = Math.min(min, beforeValue); //최솟값 업데이트

    return beforeValue;
  }

  //우변 시계방향으로 밀기 (return = 가장 마지막 값)
  private int right(int x1, int x2, int y, int firstValue) {
    int beforeValue = array[x1+1][y];

    array[x1+1][y] = firstValue;
    for (int i = x1 + 2; i <= x2; i++) {
      int tmp = array[i][y];
      array[i][y] = beforeValue;

      min = Math.min(min, beforeValue); //최솟값 업데이트

      beforeValue = tmp;
    }

    min = Math.min(min, beforeValue); //최솟값 업데이트

    return beforeValue;
  }

  //좌변 시계방향으로 밀기 (return = 가장 마지막 값)
  private int left(int x1, int x2, int y, int firstValue) {
    int beforeValue = array[x2-1][y];

    array[x2-1][y] = firstValue;
    for (int i = x2 - 2; i >= x1; i--) {
      int tmp = array[i][y];
      array[i][y] = beforeValue;

      min = Math.min(min, beforeValue); //최솟값 업데이트

      beforeValue = tmp;
    }

    min = Math.min(min, beforeValue); //최솟값 업데이트

    return beforeValue;
  }
}