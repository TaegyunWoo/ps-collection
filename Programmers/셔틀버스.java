import java.util.*;

class Solution {

  public String solution(int n, int t, int m, String[] timetable) {
    int min = 0;
    int max = t * (n-1) + 540; //마지막 버스 오는 시각
    int mid = (min + max) / 2;

    while (min <= max) {
      boolean canTake = false;
      List<Integer> crewMinuteList = getCrewMinuteList(timetable);
      int busMinute = 540;
      while (busMinute <= t * (n-1) + 540) {
        canTake = check(busMinute, mid, m, crewMinuteList);
        if (canTake) break;
        busMinute += t;
      }

      if (canTake) {
        min = mid + 1;
      } else {
        max = mid - 1;
      }

      mid = (min + max) / 2;
    }

    return toTime(mid);
  }

  private boolean check(int busMinute, int currentMinute, int m, List<Integer> crewMinuteList) {
    boolean canTake = busMinute >= currentMinute;

    int takeCount = 0;
    for (int i = 0; i < crewMinuteList.size(); i++) {
      int crewMinute = crewMinuteList.get(i);
      if (crewMinute > currentMinute) break;
      if (crewMinute <= busMinute) {
        crewMinuteList.remove(i--);
        takeCount++;
      }
      if (takeCount >= m) {
        canTake = false;
        break;
      }
    }

    return canTake;
  }

  private List<Integer> getCrewMinuteList(String[] timetable) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < timetable.length; i++) {
      result.add(toMinute(timetable[i]));
    }
    result.sort(Comparator.naturalOrder());
    return result;
  }

  private int toMinute(String time) {
    int result = 0;
    String[] timeAry = time.split(":");
    result += Integer.parseInt(timeAry[0]) * 60;
    result += Integer.parseInt(timeAry[1]);
    return result;
  }

  private String toTime(int minute) {
    String result = "";
    int h = minute / 60;
    int m = minute % 60;
    if (h < 10) {
      result = "0" + h + ":";
    } else {
      result = h + ":";
    }
    if (m < 10) {
      result = result + "0" + m;
    } else {
      result = result + m;
    }

    return result;
  }
}