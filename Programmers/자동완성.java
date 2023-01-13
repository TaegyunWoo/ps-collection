import java.util.*;

class Solution {

  public int solution(String[] words) {
    //정렬
    Arrays.sort(words, Comparator.naturalOrder());

    int answer = 0;
    for (int i = 0; i < words.length; i++) {
      int countA = 0;
      int countB = 0;
      int countResult = 0;
      if (i == 0) {
        countResult = count(words[i], words[i+1]);
      } else if (i == words.length-1) {
        countResult = count(words[i], words[i-1]);
      } else {
        countA = count(words[i], words[i-1]); //정렬된 배열에서 위 단어와 비교
        countB = count(words[i], words[i+1]); //정렬된 배열에서 아래 단어와 비교
        countResult = Math.max(countA, countB);
      }
      answer += countResult;
    }

    return answer;
  }

  //a를 b와 비교했을 때, 입력해야 하는 문자수 (a에 대해)
  private int count(String a, String b) {
    int count = 0;
    for (int i = 0; i < a.length(); i++) {
      count++;
      if (i >= b.length()) return count;
      if (a.charAt(i) != b.charAt(i)) return count;
    }
    return count;
  }
}