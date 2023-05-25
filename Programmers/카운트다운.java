import java.util.*;

class Solution {
  /*
   * d[a][0][0] = 불을 맞춰서 a 점수로 만드는 최소 다트 횟수
   * d[a][0][1] = 불 + 싱글 횟수 합
   * d[a][1][0] = 싱글을 맞춰서 a 점수로 만드는 최소 다트 횟수
   * d[a][1][1] = 불 + 싱글 횟수 합
   * d[a][2][0] = 더블을 맞춰서 a 점수로 만드는 최소 다트 횟수
   * d[a][2][1] = 불 + 싱글 횟수 합
   * d[a][3][0] = 트리플을 맞춰서 a 점수로 만드는 최소 다트 횟수
   * d[a][3][1] = 불 + 싱글 횟수 합
   */
  int[][][] d;
  PriorityQueue<Info> priorityQueue;
  public static final int INF = (int) 1e9;

  public int[] solution(int target) {
    //init
    d = new int[target+61][4][2];
    for (int i = 0; i < d.length; i++) {
      for (int u = 0; u < d[i].length; u++)
        d[i][u][0] = INF;
    }
    for (int i = 0; i < d[0].length; i++)
      d[target][i][0] = 0;
    priorityQueue = new PriorityQueue<>(
        (a, b) -> {
          if (a.countDart == b.countDart) return b.countBullSingle - a.countBullSingle;
          return a.countDart - b.countDart;
        }
    );

    //dp
    for (int i = target-1; i >= 0; i--) {
      Info info;

      //불
      for (int u = 0; u < 4; u++)
        priorityQueue.offer(new Info(d[i+50][u], u));
      info = priorityQueue.poll();
      d[i][0][0] = info.countDart;
      if (d[i][0][0] != INF) {
        d[i][0][0]++;
        d[i][0][1] = info.countBullSingle + 1;
      }
      while (!priorityQueue.isEmpty())
        priorityQueue.poll();

      for (int num = 1; num <= 20; num++) {

        //multi=1 : 싱글, multi=2 : 더블, multi=3 : 트리플
        for (int multi = 1; multi <= 3; multi++) { //각 배수 for문 시작
          int score = num * multi; //획득 점수

          //이전 점수 정보를 모두 priorityQueue에 삽입
          for (int u = 0; u < 4; u++)
            priorityQueue.offer(new Info(d[i+score][u], u));

          //가장 던진 횟수가 작고, 불+싱글 합이 큰 점수 정보 가져오기
          info = priorityQueue.poll();

          //d[i] 업데이트
          if (d[i][multi][0] >= info.countDart + 1) {
            int countBullSingle = (multi == 1) ? info.countBullSingle + 1 : info.countBullSingle;
            if (d[i][multi][0] == info.countDart + 1)
              d[i][multi][1] = Math.max(d[i][multi][1], countBullSingle);
            else
              d[i][multi][1] = countBullSingle;
            d[i][multi][0] = info.countDart + 1;
          }

          //남은 나머지 점수 정보 모두 추출 (큐 비우기)
          while (!priorityQueue.isEmpty())
            priorityQueue.poll();
        } //각 배수 for문 종료
      } //num for문 종료
    } //dp for문 종료

    //정답
    for (int i = 0; i < 4; i++) {
      priorityQueue.offer(new Info(d[0][i], i));
    }
    Info answer = priorityQueue.poll();

    return new int[] {answer.countDart, answer.countBullSingle};
  }

  class Info {
    public int countDart;
    public int countBullSingle;
    public int kind;
    public Info(int[] d, int kind) {
      this.countDart = d[0];
      this.countBullSingle = d[1];
      this.kind = kind;
    }
  }

}