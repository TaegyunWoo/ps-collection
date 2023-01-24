class Solution {
  String binary = "";
  boolean available = true;

  public int[] solution(long[] numbers) {
    int[] answer = new int[numbers.length];
    int answerIdx = 0;

    for (int i = 0; i < numbers.length; i++) {
      binary = getBinary(numbers[i]); //이진수로 변환
      check(0, binary.length() - 1, false); //검사

      if (available) answer[answerIdx++] = 1;
      else answer[answerIdx++] = 0;

      available = true;
    }

    return answer;
  }

  //검증
  private void check(int start, int end, boolean wasZero) {
    int root = (start + end) / 2;
    boolean currentIsZero = binary.charAt(root) == '0';

    if (wasZero && !currentIsZero) { //부모 노드가 0이고, 자식 노드(현 노드)가 1이면
      available = false;
      return;
    }

    if (start == end) return;

    check(start, root - 1, currentIsZero);
    check(root + 1, end, currentIsZero);
  }

  private String getBinary(long number) {
    StringBuilder result = new StringBuilder();

    while (number > 0) {
      result.insert(0, number % 2);
      number /= 2;
    }

    //포화이진트리의 노드개수에 맞춰 앞에 0 삽입
    int nodeSize = getNodeSize(result.length()); //필요한 포화이진트리의 노드 개수 (해당 이진수의 길이보다 크거나 같음)
    while (result.length() < nodeSize) {
      result.insert(0, 0);
    }

    return result.toString();
  }

  private int getNodeSize(int minSize) {
    int tmp = 2;
    int size = 1;

    while (size < minSize) {
      size += tmp;
      tmp *= 2;
    }

    return size;
  }
}