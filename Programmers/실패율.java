import java.util.*;

class Solution {
  PriorityQueue<Stage> queue = new PriorityQueue<>();
  public int[] solution(int N, int[] stages) {
    for (int stageNum = 1; stageNum <= N; stageNum++) {
      double reachedUser = 0;
      double notClearUser = 0;
      for (int i = 0; i < stages.length; i++) {
        if (stageNum <= stages[i]) {
          reachedUser++;
          if (stageNum == stages[i]) notClearUser++;
        }
      }
      if (reachedUser == 0) {
        queue.offer(new Stage(stageNum, 0));
      } else {
        queue.offer(new Stage(stageNum, notClearUser / reachedUser));
      }
    }

    int[] answer = new int[N];
    for (int i = 0; i < answer.length; i++) {
      answer[i] = queue.poll().num;
    }

    return answer;
  }

  class Stage implements Comparable<Stage> {
    public int num;
    public double fail;
    public Stage(int num, double fail) {
      this.num = num;
      this.fail = fail;
    }
    @Override
    public int compareTo(Stage other) {
      if (this.fail > other.fail) return -1;
      if (this.fail == other.fail) {
        if (this.num < other.num) return -1;
        return 1;
      }
      return 1;
    }
  }
}