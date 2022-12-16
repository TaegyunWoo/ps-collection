import java.util.*;

class Solution {
  static Set<Integer> set;

  public int solution(int[] nums) {
    int n = nums.length;

    //set 초기화
    set = new HashSet<>();
    for (int i = 0; i < n; i++) {
      set.add(nums[i]);
    }

    if (set.size() >= n / 2) {
      return n / 2;
    } else {
      return set.size();
    }
  }
}