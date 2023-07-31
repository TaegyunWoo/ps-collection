import java.util.*;

class Solution {
  public String solution(String p) {
    String answer = run(p);
    return answer;
  }

  private String run(String s) {
    //탈출조건
    if (s.length() == 0) return "";

    String[] uv = getUV(s);
    String u = uv[0];
    String v = uv[1];

    //v에 대해 재귀
    String result = run(v);

    if (isValid(u)) {
      return u + result;
    } else {
      StringBuilder sb = new StringBuilder();
      sb.append("(");
      sb.append(result);
      sb.append(")");

      for (int i = 1; i < u.length()-1; i++) {
        if (u.charAt(i) == '(') sb.append(')');
        else sb.append('(');
      }

      return sb.toString();
    }

  }

  private String[] getUV(String p) {
    String u = "";
    String v = "";
    int countLeft = 0;
    int countRight = 0;

    for (int i = 0; i < p.length(); i++) {
      char c = p.charAt(i);

      if (c == '(') countLeft++;
      else countRight++;

      if (countLeft == countRight) {
        u = p.substring(0, i+1);
        v = p.substring(i+1, p.length());
        break;
      }
    }

    return new String[] {u, v};
  }

  private boolean isValid(String s) {
    Deque<Character> deque = new ArrayDeque<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') {
        deque.offerLast(c);
      } else {
        if (deque.size() == 0) return false;
        deque.pollLast();
      }
    }

    if (deque.size() != 0) return false;
    return true;
  }
}