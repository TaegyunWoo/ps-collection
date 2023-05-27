import java.util.*;

class Solution {
  public int solution(int n, int k, int[] enemy) {
    //현재 라운드까지의 enemy 수를 내림차순으로 우선순위 큐
    PriorityQueue<Integer> pastEnemyQueue = new PriorityQueue<>(
        Collections.reverseOrder()
    );

    //solution
    int answer = enemy.length;
    for (int round = 0; round < enemy.length; round++) {
      pastEnemyQueue.offer(enemy[round]);
      n -= enemy[round];

      if (n < 0) { //병사수가 모자르다면
        if (k == 0) { //무적권을 사용할 수 없다면
          answer = round;
          break;
        } else { //무적권을 사용할 수 있다면
          n += pastEnemyQueue.poll();
          k--;
        }
      }
    }

    return answer;
  }
}