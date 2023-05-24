import java.util.*;

class Solution {
  public int solution(int[][] data, int col, int row_begin, int row_end) {
    //정렬
    sort(data, col-1);

    //S_i 계산
    int result = 0;
    for (int tupleIdx = row_begin-1; tupleIdx < row_end; tupleIdx++) {
      int si = getSi(data, tupleIdx);
      result = result ^ si;
    }

    return result;
  }

  private void sort(int[][] data, int col) {
    Arrays.sort(data, (a, b) -> {
      if (a[col] == b[col]) {
        return b[0] - a[0];
      }
      return a[col] - b[col];
    });
  }

  private int getSi(int[][] data, int idx) {
    int result = 0;
    for (int i = 0; i < data[idx].length; i++) {
      result += data[idx][i] % (idx+1);
    }
    return result;
  }
}