import java.util.*;

class Solution {
  public int solution(String s) {
    int answer = 0;

    for (int i = 0; i < s.length(); i++) {
      s = goLeft(s);
      boolean result = isRight(s);
      if (result) answer++;
    }

    return answer;
  }

  private String goLeft(String s) {
    StringBuilder sb = new StringBuilder(s);
    char c = sb.charAt(0);
    sb.deleteCharAt(0);
    sb.append(c);
    return sb.toString();
  }

  private boolean isRight(String s) {
    Deque<Character> deque = new ArrayDeque<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(' || c == '{' || c == '[') {
        deque.offerFirst(c);

      } else {
        if (deque.size() == 0) return false;

        char top = deque.pollFirst();
        if (c == ')' && top != '(') return false;
        else if (c == '}' && top != '{') return false;
        else if (c == ']' && top != '[') return false;
      }
    } //for문 종료

    return deque.size() == 0;
  }
}