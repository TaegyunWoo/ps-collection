import java.util.*;

class Solution {
  PriorityQueue<String> queue = new PriorityQueue<>();
  String[] charAry = {"A", "E", "I", "O", "U"};
  int[] visited;
  String[] wordAry;

  public int solution(String word) {

    //단어 길이만큼 반복
    for (int r = 1; r <= 5; r++) {
      visited = new int[5];
      wordAry = new String[r];

      permutation(0, r);
    }

    //정답 출력
    int answer = 0;
    while (!queue.isEmpty()) {
      String tmp = queue.poll();
      answer++;
      if (tmp.equals(word)) return answer;
    }

    return 0;
  }

  //중복 가능 순열
  private void permutation(int depth, int r) {
    if (depth == r) {
      String result = mergeChar();
      queue.offer(result);
      return;
    }

    for (int i = 0; i < charAry.length; i++) {
      wordAry[depth] = charAry[i];

      //다음 뽑기
      permutation(depth+1, r);
    }
  }

  private String mergeChar() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < wordAry.length; i++) {
      result.append(wordAry[i]);
    }
    return result.toString();
  }
}