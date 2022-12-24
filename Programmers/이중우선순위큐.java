import java.util.*;

class Solution {
  //최대큐
  PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
  //최소큐
  PriorityQueue<Integer> minQueue = new PriorityQueue<>();
  //삭제 여부 체크
  HashMap<Integer, Boolean> deleteMap = new HashMap<>(); // <숫자, 삭제여부>

  public int[] solution(String[] operations) {
    //연산 수행
    for (int i = 0; i < operations.length; i++) {
      String[] operationAry = operations[i].split(" ");
      if (operationAry[0].equals("I")) { //삽입 연산
        int num = Integer.parseInt(operationAry[1]);
        maxQueue.offer(num);
        minQueue.offer(num);
        deleteMap.put(num, false);
      } else if (operationAry[1].equals("1")) { //최댓값 제거 연산
        if (maxQueue.isEmpty()) continue;
        int num = maxQueue.poll();
        while (deleteMap.get(num) && !maxQueue.isEmpty()) { //이미 삭제했던 것이면 다시 poll
          num = maxQueue.poll();
        }
        deleteMap.put(num, true);
      } else { //최솟값 제거 연산
        if (minQueue.isEmpty()) continue;
        int num = minQueue.poll();
        while (deleteMap.get(num) && !minQueue.isEmpty()) { //이미 삭제했던 것이면 다시 poll
          num = minQueue.poll();
        }
        deleteMap.put(num, true);
      }
    }

    //정답 계산
    int[] answer = {0, 0};
    while (!maxQueue.isEmpty()) {
      int maxNum = maxQueue.poll();
      if (!deleteMap.get(maxNum)) {
        answer[0] = maxNum;
        break;
      }
    }
    while (!minQueue.isEmpty()) {
      int minNum = minQueue.poll();
      if (!deleteMap.get(minNum)) {
        answer[1] = minNum;
        break;
      }
    }

    return answer;
  }
}