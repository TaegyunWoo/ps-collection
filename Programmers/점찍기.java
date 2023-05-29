class Solution {
  public long solution(int k, long d) {
    long result = 0;

    for (long x = 0; x <= d; x += k) {
      int maxY = (int) Math.sqrt((d*d) - (x*x));
      result += (maxY/k)+1;
    }

    return result;
  }
}