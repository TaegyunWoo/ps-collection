class Solution {

  public long solution(int n, int m, int x, int y, int[][] queries) {
    int startR = x;
    int endR = x;
    int startC = y;
    int endC = y;

    for (int i = queries.length-1; i >= 0; i--) {
      int[] query = queries[i];

      if (query[0] == 0) { //좌 이동시 -> 반대 = 우
        endC += query[1];
        if (endC > m-1) endC = m - 1;

        if (startC != 0) startC += query[1];

        if (startC > m - 1) return 0;

      } else if (query[0] == 1) { //우 이동시 -> 반대 = 좌
        startC -= query[1];
        if (startC < 0) startC = 0;

        if (endC != m-1) endC -= query[1];

        if (endC < 0) return 0;

      } else if (query[0] == 2) { //상 이동시 -> 반대 = 하
        endR += query[1];
        if (endR > n-1) endR = n - 1;

        if (startR != 0) startR += query[1];

        if (startR > n - 1) return 0;

      } else if (query[0] == 3) { //하 이동시 -> 반대 = 상
        startR -= query[1];
        if (startR < 0) startR = 0;

        if (endR != n-1) endR -= query[1];

        if (endR < 0) return 0;
      }
    }

    return (long) (endR - startR + 1) * (long) (endC - startC + 1);
  }
}