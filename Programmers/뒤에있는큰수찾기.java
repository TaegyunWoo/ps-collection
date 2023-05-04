import java.util.*;

class Solution {
  Deque<Integer> stack = new ArrayDeque<>();
  int[] answer;
  int answerIdx;

  public int[] solution(int[] numbers) {
    //init answer
    answer = new int[numbers.length];
    answerIdx = answer.length-1;

    //Stack 활용 계산
    for (int i = numbers.length-1; i >= 0; i--) {
      /*
       * 현재 수보다 스택의 최상단 수가 더 작은 경우 pop
       * 어차피 앞으로 남은 numbers 수들은 최소한 현재 수를 뒤큰수로 삼을 것이기 때문에 pop
       */
      while (!stack.isEmpty() && stack.peekLast() <= numbers[i]) {
        stack.pollLast();
      }

      if (stack.isEmpty()) { //스택이 비었다면
        answer[answerIdx--] = -1; //-1 저장
      } else { //스택이 비지 않았다면 (더 큰 수가 존재한다면)
        answer[answerIdx--] = stack.peekLast(); //뒤큰수 저장
      }

      stack.offerLast(numbers[i]); //push
    }

    return answer;
  }
}