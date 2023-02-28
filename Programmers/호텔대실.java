import java.util.*;

class Solution {
  List<String> roomEndTimeList = new LinkedList<>();

  public int solution(String[][] book_time) {
    sortByStartTime(book_time);
    add10Minute(book_time);

    roomEndTimeList.add(book_time[0][1]);
    for (int i = 1; i < book_time.length; i++) {
      boolean wasEmptyRoom = false;
      String startTime = book_time[i][0];
      String endTime = book_time[i][1];

      for (int u = 0; u < roomEndTimeList.size(); u++) {
        if (compareTime(startTime, roomEndTimeList.get(u)) >= 0) { //빈 기존 방이 있다면
          roomEndTimeList.add(u, endTime);
          roomEndTimeList.remove(u+1);
          wasEmptyRoom = true;
          break;
        }
      }

      //빈 기존 방이 없다면
      if (!wasEmptyRoom) roomEndTimeList.add(endTime);
    }

    return roomEndTimeList.size();
  }

  private void sortByStartTime(String[][] book_time) {
    Arrays.sort(book_time, (a, b) -> {
      String startTimeA = a[0];
      String startTimeB = b[0];
      return compareTime(startTimeA, startTimeB);
    });
  }

  private void add10Minute(String[][] book_time) {
    for (int i = 0; i < book_time.length; i++) {
      String[] time = book_time[i][1].split(":");
      int hour = Integer.parseInt(time[0]);
      int minute = Integer.parseInt(time[1]);
      if (minute < 50) {
        minute += 10;
      } else {
        hour++;
        minute = 10 - (60 - minute);
      }
      book_time[i][1] = hour + ":" + minute;
    }
  }

  private int compareTime(String a, String b) {
    String[] timeA = a.split(":");
    int hourA = Integer.parseInt(timeA[0]);
    int minuteA = Integer.parseInt(timeA[1]);
    String[] timeB = b.split(":");
    int hourB = Integer.parseInt(timeB[0]);
    int minuteB = Integer.parseInt(timeB[1]);

    if (hourA != hourB) return hourA - hourB;
    else return minuteA - minuteB;
  }
}