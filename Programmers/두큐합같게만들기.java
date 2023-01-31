import java.util.*;

class Solution {
  List<Integer> queueA = new LinkedList<>();
  List<Integer> queueB = new LinkedList<>();
  long sumA = 0;
  long sumB = 0;

  public int solution(int[] queue1, int[] queue2) {
    int answer = 0;

    init(queue1, queue2);

    //총 원소의 합이 짝수가 아닌 경우 답을 구할 수 없음
    if ((sumA + sumB) % 2 != 0) return -1;

    while (sumA != sumB) {
      /*
       * 최대 연산 횟수를 벗어난 경우 (즉 루프에 빠진 경우)
       * 큐의 최대 길이가 300,000 이므로 최대 연산 횟수는 600,000 번이다.
       */
      if (answer > 600000) return -1;

      if (sumA < sumB) { //큐A의 원소합이 더 작은 경우
        bToA();
      } else if (sumA > sumB) { //큐B의 원소합이 더 작은 경우
        aToB();
      }

      answer++;
    }

    return answer;
  }

  private void init(int[] q1, int[] q2) {
    for (int i = 0; i < q1.length; i++) {
      queueA.add(q1[i]);
      queueB.add(q2[i]);
      sumA += q1[i];
      sumB += q2[i];
    }
  }

  private void aToB() {
    int element = queueA.get(0);
    queueA.remove(0);

    queueB.add(element);
    sumA -= element;
    sumB += element;
  }

  private void bToA() {
    int element = queueB.get(0);
    queueB.remove(0);

    queueA.add(element);
    sumB -= element;
    sumA += element;
  }
}