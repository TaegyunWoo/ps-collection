class Solution {
  public int solution(int[] stones, int k) {
    int answer = binarySearch(stones, k);
    return answer;
  }

  private int binarySearch(int[] stones, int k) {
    int min = 1;
    int max = 200000000;
    int mid = (min + max) / 2;
    int answer = 0;

    while (min <= max) {
      boolean go = canGo(stones, mid, k);

      if (go) { //mid만큼의 친구들이 건널 수 있다면
        min = mid + 1;
        answer = Math.max(answer, mid);
      } else { //mid만큼의 친구들이 건널 수 없다면
        max = mid - 1;
      }

      mid = (min + max) / 2;
    }

    return answer;
  }

  private boolean canGo(int[] stones, int friends, int k) {
    int countGap = 0;
    for (int i = 0; i < stones.length; i++) {
      if (stones[i] < friends) countGap++;
      else countGap = 0;
      if (countGap == k) return false;
    }
    return true;
  }
}