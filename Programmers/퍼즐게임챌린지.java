class Solution {
  private int[] diffs;
  private int[] times;
  private long limit;
  private int min = 1;
  private int max = 1;
  private int answer = 1;

  public int solution(int[] diffs, int[] times, long limit) {
    this.diffs = diffs;
    this.times = times;
    this.limit = limit;

    for (int i = 0; i < diffs.length; i++) {
      max = Math.max(max, diffs[i] + 1);
    }

    //solution
    while (min != max) {
      int level = (min + max) / 2;
      long calResult = cal(level);

      if (calResult <= limit) {
        answer = level;
        max = level;
      } else {
        min = level + 1;
      }
    }

    return answer;
  }

  private long cal(int level) {
    long result = times[0];

    for (int i = 1; i < diffs.length; i++) {
      if (diffs[i] <= level) {
        result += times[i];
      } else {
        result += ((long) (diffs[i] - level)) * (long) (times[i] + times[i-1]) + (long) times[i];
      }

      if (result > limit) return limit + 1;
    }

    return result;
  }
}