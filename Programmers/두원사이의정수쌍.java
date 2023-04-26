class Solution {
  long answer = 0;

  public long solution(int r1, int r2) {
    int smallR = Math.min(r1, r2);
    int bigR = Math.max(r1, r2);
    int countEdge = 0;

    //1사분면의 점 갯수 구하기
    for (int x = 0; x <= bigR; x++) {
      int maxY = getMaxY(x, bigR); //x = i 일때, 점이 될 수 있는 Y의 최댓값
      int minY = getMinY(x, smallR); //x = i 일때, 점이 될 수 있는 Y의 최솟값
      answer += maxY - minY + 1;

      if (x == 0) countEdge = maxY - minY + 1;
    }

    return (answer - countEdge) * 4;
  }

  private int getMaxY(long x, long r) {
    double result = Math.sqrt((r*r) - (x*x));
    return (int) result;
  }

  private int getMinY(long x, long r) {
    double result = Math.sqrt((r*r) - (x*x));
    return (int) Math.ceil(result);
  }
}