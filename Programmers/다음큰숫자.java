class Solution {
  public int solution(int n) {
    int nOneCount = countOne(n);

    while (true) {
      n++;
      if (nOneCount == countOne(n)) return n;
    }
  }

  private int countOne(int num) {
    int count = 0;
    while (num > 0) {
      if (num % 2 == 1) count++;
      num /= 2;
    }
    return count;
  }
}