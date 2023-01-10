import java.util.*;

class Solution {
  int[][] milliSecTime;
  int answer = 0;

  public int solution(String[] lines) {
    tansform(lines);
    for (int i = 0; i < milliSecTime.length; i++) {
      int endTime = milliSecTime[i][1];
      answer = Math.max(answer, count(endTime, endTime+999));
    }
    return answer;
  }

  private void tansform(String[] lines) {
    milliSecTime = new int[lines.length][2];
    for (int i = 0; i < lines.length; i++) {
      String[] line = lines[i].split(" ");
      String[] endTime = line[1].split(":");
      int endHour = Integer.parseInt(endTime[0]) * 3600000;
      int endMinute = Integer.parseInt(endTime[1]) * 60000;
      int endSec = (int) (Double.parseDouble(endTime[2]) * 1000);
      line[2] = line[2].split("s")[0];
      int spendTime = (int) (Double.parseDouble(line[2]) * 1000);
      milliSecTime[i][0] = endHour + endMinute + endSec - spendTime + 3001;
      milliSecTime[i][1] = endHour + endMinute + endSec + 3000;
    }
  }

  private int count(int sectorStart, int sectorEnd) {
    int count = 0;
    for (int i = 0; i < milliSecTime.length; i++) {
      int startTime = milliSecTime[i][0];
      int endTime = milliSecTime[i][1];
      if (startTime <= sectorEnd && endTime >= sectorStart) {
        count++;
      }
    }
    return count;
  }
}