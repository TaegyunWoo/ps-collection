class Solution {
  public int solution(int left, int right) {
    int answer = 0;
    for (int num = left; num <= right; num++) {
      boolean sub = false;

      for (int i = 1; i <= Math.sqrt(num); i++) {
        if (num == (i * i)) {
          sub = true;
          break;
        }
      }

      if (sub) answer -= num;
      else answer += num;
    }

    return answer;
  }
}