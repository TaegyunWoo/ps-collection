class Solution {
  public long solution(int n, int m, int x, int y, int[][] queries) {
    int[] areaLeftTop = {x, y};
    int[] areaRightBottom = {x, y};

    //쿼리 역순으로 수행
    for (int i = queries.length - 1; i >= 0; i--) {
      int[] query = queries[i];
      int direction = query[0];
      int dx = query[1];

      //방향별 계산
      switch (direction) {
        case 0: //좌로 이동하는 쿼리인 경우
          if (areaLeftTop[1] != 0) { //영역이 좌측 끝에 붙어있지 않은 경우
            areaLeftTop[1] += dx;
            if (areaLeftTop[1] >= m) return 0; //벗어난 위치로부터 와야하는 경우
          }
          areaRightBottom[1] = (areaRightBottom[1] + dx >= m) ? m-1 : areaRightBottom[1] + dx;
          break;

        case 1: //우로 이동하는 쿼리인 경우
          if (areaRightBottom[1] != m-1) { //영역이 우측 끝에 붙어있지 않은 경우
            areaRightBottom[1] -= dx;
            if (areaRightBottom[1] < 0) return 0; //벗어난 위치로부터 와야하는 경우
          }
          areaLeftTop[1] = (areaLeftTop[1] - dx < 0) ? 0 : areaLeftTop[1] - dx;
          break;

        case 2: //상으로 이동하는 쿼리인 경우
          if (areaLeftTop[0] != 0) { //영역이 윗쪽 끝에 붙어있지 않은 경우
            areaLeftTop[0] += dx;
            if (areaLeftTop[0] >= n) return 0; //벗어난 위치로부터 와야하는 경우
          }
          areaRightBottom[0] = (areaRightBottom[0] + dx >= n) ? n-1 : areaRightBottom[0] + dx;
          break;

        case 3: //하로 이동하는 쿼리인 경우
          if (areaRightBottom[0] != n-1) { //영역이 아랫쪽 끝에 붙어있지 않은 경우
            areaRightBottom[0] -= dx;
            if (areaRightBottom[0] < 0) return 0; //벗어난 위치로부터 와야하는 경우
          }
          areaLeftTop[0] = (areaLeftTop[0] - dx < 0) ? 0 : areaLeftTop[0] - dx;
          break;
      } //switch 문 종료
    } //for 문 종료

    //정답 구하기
    long answer = (long) (areaRightBottom[0] - areaLeftTop[0] + 1) * (long) (areaRightBottom[1] - areaLeftTop[1] + 1);
    return answer;
  }
}