class Solution {

  public String[] solution(int n, int[] arr1, int[] arr2) {
    String[] answer = new String[n];

    for (int i = 0; i < n; i++) {
      StringBuilder sb = new StringBuilder();
      int[] result1 = tansfer(arr1[i], n);
      int[] result2 = tansfer(arr2[i], n);

      for (int u = 0; u < n; u++) {
        if (result1[u] == 0 && result2[u] == 0) {
          sb.append(" ");
        } else {
          sb.append("#");
        }
      }

      answer[i] = sb.toString();
    }

    return answer;
  }

  private int[] tansfer(int num, int n) {
    int[] result = new int[n];

    for (int i = n-1; i >= 0; i--) {
      result[i] = num % 2;
      num /= 2;
    }

    return result;
  }
}