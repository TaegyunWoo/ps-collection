import java.io.*;
import java.util.*;

/**
 * [해결방법]
 * 조건에 따라, stack에 연산자를 PUSH하거나 POP한다.
 *
 * [연산: push]
 * 현재 연산자가 스택 최상단 요소보다 우선순위가 높을 때 ( 글자 '('는 우선순위가 가장 낮다. )
 * 현재 글자가 '(' 일 때
 * 스택이 비어있을 때
 *
 * [연산: pop]
 * 현재 연산자가 스택 최상단 요소보다 우선순위가 낮거나 같을 때
 * 현재 글자가 ')' 일 때, '('까지 pop
 */
public class Q1918 {

  static char[] expression;

  public static void main(String[] args)throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    expression = br.readLine().toCharArray();

    solution();
  }

  private static void solution() {
    StringBuilder answer = new StringBuilder();
    Stack<Character> stack = new Stack<>();
    boolean isFirstOperation = false;

    for (int i = 0; i < expression.length; i++) {
      char c = expression[i];

      if (c >= 'A' && c <= 'Z') { //피연산자일 때
        answer.append(c);
      } else { //연산자일 때

        if (c == '(') {
          stack.push(c);
        } else if (c == ')') {
          while (stack.peek() != '(') {
            answer.append(stack.pop());
          }
          stack.pop();
        } else if (c == '-' || c == '+') {
          if (stack.isEmpty() || stack.peek() == '(') {
            stack.push(c);
          } else {
            answer.append(stack.pop());
            i--;
          }
        } else if (c == '*' || c == '/') {
          if (stack.isEmpty() || stack.peek() == '(' || stack.peek() == '+' || stack.peek() == '-') {
            stack.push(c);
          } else {
            answer.append(stack.pop());
            i--;
          }
        }

      }

    }

    while (!stack.isEmpty()) {
      answer.append(stack.pop());
    }

    System.out.println(answer);
  }

}