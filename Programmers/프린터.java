import java.util.*;

class Solution {
  Deque<Doc> printQueue = new ArrayDeque<>(); //문서 순서 저장
  PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder()); //우선순위 저장

  public int solution(int[] priorities, int location) {
    //printQueue, priorityQueue 초기화
    for (int i = 0; i < priorities.length; i++) {
      printQueue.offerLast(new Doc(i, priorities[i]));
      priorityQueue.offer(priorities[i]);
    }

    int count = 0;
    while (true) {
      Doc currentDoc = printQueue.pollFirst();
      int biggestPriority = priorityQueue.peek();

      if (currentDoc.priority != biggestPriority) { //가장 높은 우선순위가 아니라면
        printQueue.offerLast(currentDoc);

      } else { //가장 높은 우선순위라면
        priorityQueue.poll(); //priorityQueue 에서 해당 우선순위 제거
        count++;
        if (currentDoc.index == location) break; //원하는 문서를 찾았으면
      }
    }

    return count;
  }

  class Doc {
    public int index;
    public int priority;

    public Doc(int i, int p) {
      this.index = i;
      this.priority = p;
    }
  }
}