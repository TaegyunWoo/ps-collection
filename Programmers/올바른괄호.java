import java.util.*;

class Solution {
  Stack<String> stack = new Stack<>();

  boolean solution(String s) {
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        stack.push("(");
      } else {
        if (stack.size() == 0) return false;
        stack.pop();
      }
    }

    if (stack.size() != 0) return false;
    return true;
  }
}