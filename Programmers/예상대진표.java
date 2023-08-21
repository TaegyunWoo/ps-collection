import java.util.*;

class Solution
{
  Deque<Boolean> deque = new ArrayDeque<Boolean>();

  public int solution(int n, int a, int b) {
    //init
    for (int i = 1; i <= n; i++) {
      if (i == a || i == b)
        deque.offerLast(true);
      else
        deque.offerLast(false);
    }

    //solution
    int answer = 1;
    while (deque.size() != 0) {
      Deque<Boolean> tmpDeque = new ArrayDeque<>();

      while (!deque.isEmpty()) {
        boolean entryA = deque.pollLast();
        boolean entryB = deque.pollLast();

        if (entryA && entryB) {
          return answer;
        } else if (entryA || entryB) {
          tmpDeque.offerLast(true);
        } else {
          tmpDeque.offerLast(false);
        }
      } //내부 while 종료

      answer++;
      deque = tmpDeque;
    } //외부 while 종료

    return -1;
  }
}