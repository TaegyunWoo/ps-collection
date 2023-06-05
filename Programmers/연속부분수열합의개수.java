import java.util.*;

class Solution {
  public int solution(int[] elements) {
    Set<Integer> sumSet = new HashSet<>();

    for (int start = 0; start < elements.length; start++) {
      int sum = 0;
      for (int i = 0; i < elements.length; i++) {
        int end = (start + i) % elements.length;
        sum += elements[end];
        sumSet.add(sum);
      }
    }

    return sumSet.size();
  }
}