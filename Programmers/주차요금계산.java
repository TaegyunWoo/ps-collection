import java.util.*;

class Solution {
  List<Info> answerList = new ArrayList<>();
  HashMap<String, Integer> accumTimeMap = new HashMap<>(); //<차번호, 누적분>

  public int[] solution(int[] fees, String[] records) {
    initAccumTimeMap(records); //누적 분 계산

    //누적분에 따른 최종 금액 계산
    for (String carNum : accumTimeMap.keySet()) {
      int time = accumTimeMap.get(carNum);
      int costResult = calCost(time, fees);
      answerList.add(new Info(carNum, costResult));
    }

    //정답 출력
    int[] answer = new int[answerList.size()];
    int idx = 0;
    answerList.sort((a, b) -> a.carNum.compareTo(b.carNum));
    for (Info info : answerList) {
      answer[idx++] = info.cost;
    }


    return answer;
  }

  private void initAccumTimeMap(String[] records) {
    HashMap<String, String> recordMap = new HashMap<>();

    for (int i = 0; i < records.length; i++) {
      String[] record = records[i].split(" ");
      String time = record[0];
      String carNum = record[1];

      if (recordMap.containsKey(carNum)) { //입차 이력이 있는 경우
        String startTime = recordMap.get(carNum);
        int accumTime = getTime(startTime, time);

        if (accumTimeMap.containsKey(carNum)) { //이전에 누적분을 계산한 결과가 있는 경우
          accumTimeMap.put(carNum, accumTimeMap.get(carNum) + accumTime);
        } else {//이전에 누적분을 계산한 결과가 없는 경우
          accumTimeMap.put(carNum, accumTime);
        }

        recordMap.remove(carNum); //입차 이력 제거

      } else { //입차 이력이 없는 경우
        recordMap.put(carNum, time);
      }
    }

    //입차 이력만 있는 경우
    for (String carNum : recordMap.keySet()) {
      String startTime = recordMap.get(carNum);
      int accumTime = getTime(startTime, "23:59");

      if (accumTimeMap.containsKey(carNum)) { //이전에 누적분을 계산한 결과가 있는 경우
        accumTimeMap.put(carNum, accumTimeMap.get(carNum) + accumTime);
      } else {//이전에 누적분을 계산한 결과가 없는 경우
        accumTimeMap.put(carNum, accumTime);
      }
    }
  }

  private int getTime(String s, String e) {
    String[] sAry = s.split(":");
    int startHour = Integer.parseInt(sAry[0]);
    int startMin = Integer.parseInt(sAry[1]);
    String[] eAry = e.split(":");
    int endHour = Integer.parseInt(eAry[0]);
    int endMin = Integer.parseInt(eAry[1]);

    return (endHour - startHour) * 60 + (endMin - startMin);
  }

  private int calCost(int time, int[] fees) {
    int defaultTime = fees[0];
    int defaultCost = fees[1];
    int unitTime = fees[2];
    int unitCost = fees[3];
    int result = (int) (defaultCost + Math.ceil(((double) (time - defaultTime) / unitTime)) * unitCost);

    return (result > defaultCost) ? result : defaultCost;
  }

  class Info {
    public String carNum;
    public int cost;
    public Info(String carNum, int cost) {
      this.carNum = carNum;
      this.cost = cost;
    }
  }
}