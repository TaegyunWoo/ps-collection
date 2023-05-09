class Solution {
  static final int INF = (int) 1e9;
  int answer = INF;

  public int solution(int storey) {
    brute(storey, 0, 0);

    return answer;
  }

  private void brute(int n, int depth, int countStone) {
    if (n == 0) {
      answer = Math.min(answer, countStone);
      return;
    }

    int pow = (int) Math.pow(10, depth);
    int digit = ((n % (pow*10)) - (n % (pow))) / pow;

    if (digit < 5) {
      brute(n - digit * pow, depth + 1, countStone + digit);
    } else if (digit > 5) {
      brute(n + ((10 - digit) * pow), depth + 1, countStone + (10 - digit));
    } else {
      brute(n - digit * pow, depth + 1, countStone + digit);
      brute(n + ((10 - digit) * pow), depth + 1, countStone + (10 - digit));
    }
  }
}