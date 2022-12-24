import java.util.*;

class Solution {
  //시작시점 오름차순 우선순위 큐
  PriorityQueue<Job> startTimeQueue = new PriorityQueue<>((me, other) -> {
    if (me.startTime < other.startTime) return -1;
    if (me.startTime == other.startTime) {
      if (me.spendTime < other.spendTime) return -1;
    }
    return 1;
  });

  //소요시간 오름차순 우선순위 큐
  PriorityQueue<Job> spendTimeQueue = new PriorityQueue<>((me, other) -> {
    if (me.spendTime < other.spendTime) return -1;
    if (me.spendTime == other.spendTime) return 0;
    return 1;
  });

  public int solution(int[][] jobs) {
    //시작시간 큐 초기화
    for (int i = 0; i < jobs.length; i++) {
      startTimeQueue.offer(new Job(jobs[i][0], jobs[i][1]));
    }

    //계산
    int startToEndTimeSum = 0;
    int currentEndTime = 0;
    while (!startTimeQueue.isEmpty() || !spendTimeQueue.isEmpty()) {
      while (!startTimeQueue.isEmpty()) {
        Job job = startTimeQueue.peek();
        if (job.startTime <= currentEndTime) {
          startTimeQueue.poll();
          spendTimeQueue.offer(job);
        } else {
          break;
        }
      }

      if (spendTimeQueue.isEmpty()) {
        currentEndTime++;
        continue;
      }

      Job job = spendTimeQueue.poll();
      startToEndTimeSum += (currentEndTime - job.startTime) + job.spendTime;
      currentEndTime += job.spendTime;
    }

    return startToEndTimeSum / jobs.length;
  }

  class Job {
    public int startTime;
    public int spendTime;

    public Job(int start, int spend) {
      this.startTime = start;
      this.spendTime = spend;
    }
  }
}