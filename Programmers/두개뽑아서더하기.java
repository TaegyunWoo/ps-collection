import java.util.*;

class Solution {
  HashSet<Integer> resultSet = new HashSet<>();

  public int[] solution(int[] numbers) {
    for (int i = 0; i < numbers.length; i++) {
      for (int u = i + 1; u < numbers.length; u++) {
        resultSet.add(numbers[i] + numbers[u]);
      }
    }

    int[] answer = new int[resultSet.size()];
    int idx = 0;
    for (int n : resultSet) {
      answer[idx++] = n;
    }

    Arrays.sort(answer);

    return answer;
  }
}