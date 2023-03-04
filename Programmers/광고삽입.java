import java.util.*;

class Solution {
  int advTime = 0;
  int playTime = 0;
  int[][] logTime;
  long[] accumulateTable = new long[360000]; //누적합 배열

  public String solution(String play_time, String adv_time, String[] logs) {
    init(adv_time, play_time, logs);

    if (advTime == timeToInt(play_time)) return "00:00:00";

    //---[누적합 로직]----

    //로그마다 시작시각, 끝시각 기록
    for (int[] log : logTime) {
      int start = log[0];
      int end = log[1];
      accumulateTable[start]++;
      accumulateTable[end]--;
    }

    //누적합 연산 1 (동시 시청자 수 계산) Ex) accumulateTable[a] = (a ~ a+1) 초 구간의 동시 시청자 수
    for (int i = 1; i < accumulateTable.length; i++) {
      accumulateTable[i] += accumulateTable[i-1];
    }

    //누적합 연산 2 (누적 시청 시간 계산) Ex) accumulateTable[a] = (0 ~ a+1) 초 구간의 누적 시청 시간
    for (int i = 1; i < accumulateTable.length; i++) {
      accumulateTable[i] += accumulateTable[i-1];
    }

    //------------------

    //정답 구하기
    int answerTime = 0;
    long answerTimeSum = accumulateTable[advTime-1];

    for (int i = advTime; i < playTime; i++) {
      long timeSum = accumulateTable[i] - accumulateTable[i - advTime];
      if (answerTimeSum < timeSum) {
        answerTime = i + 1 - advTime;
        answerTimeSum = timeSum;
      }
    }

    return intToTime(answerTime);
  }

  private int timeToInt(String time) {
    String[] timeAry = time.split(":");
    int hour = Integer.parseInt(timeAry[0]);
    int minute = Integer.parseInt(timeAry[1]);
    int second = Integer.parseInt(timeAry[2]);
    return (hour * 60 * 60) + (minute * 60) + second;
  }

  private String intToTime(int intTime) {
    StringBuilder time = new StringBuilder();
    int hour = intTime / (60 * 60);
    intTime = intTime % (60 * 60);
    int minute = intTime / 60;
    intTime = intTime % 60;
    int second = intTime;

    if (hour < 10) {
      time.append(0);
      time.append(hour);
    } else {
      time.append(hour);
    }
    time.append(":");
    if (minute < 10) {
      time.append(0);
      time.append(minute);
    } else {
      time.append(minute);
    }
    time.append(":");
    if (second < 10) {
      time.append(0);
      time.append(second);
    } else {
      time.append(second);
    }

    return time.toString();
  }

  private void init(String adv_time, String play_time, String[] logs) {
    advTime = timeToInt(adv_time);
    playTime = timeToInt(play_time);
    logTime = new int[logs.length][2];

    int idx = 0;
    for (String log : logs) {
      String[] logAry = log.split("-");
      int logStart = timeToInt(logAry[0]);
      int logEnd = timeToInt(logAry[1]);
      logTime[idx][0] = logStart;
      logTime[idx][1] = logEnd;
      idx++;
    }

    Arrays.sort(logTime, (a, b) -> {
      return a[0] - b[0];
    });
  }
}