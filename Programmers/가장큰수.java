import java.util.*;

class Solution {
  public String solution(int[] numbers) {
    /*
     * [우선순위 큐 정의]
     * 두 정수를 ab, ba 형태로 합쳐보고, 더 큰 형태의 앞 정수(a|b)를 우선 선택한다.
     */
    PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> {
      int digitA = getDigit(a); //a의 자릿수
      int digitB = getDigit(b); //b의 자릿수

      int ab = a * ((int) Math.pow(10, digitB)) + b;
      int ba = b * ((int) Math.pow(10, digitA)) + a;
      if (ab > ba) {
        return -1;
      } else if (ab == ba) {
        return 0;
      }
      return 1;
    });

    //우선순위 큐에 삽입
    for (int i = 0; i < numbers.length; i++) {
      queue.offer(numbers[i]);
    }

    //정답 출력
    boolean isAllZero = true;
    StringBuilder answer = new StringBuilder();
    for (int i = 0; i < numbers.length; i++) {
      int num = queue.poll();
      answer.append(num);
      if (num != 0) isAllZero = false;
    }

    if (isAllZero) {
      return "0";
    }
    return answer.toString();
  }

  //자릿수 구하는 메서드
  private int getDigit(int num) {
    int result = 0;
    while (true) {
      if (num/10 >= 0) {
        num /= 10;
        result++;
      }
      if (num == 0) break;
    }
    return result;
  }
}