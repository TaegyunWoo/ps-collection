import java.util.*;

class Solution {
  Map<String, Integer> map = new HashMap<>();

  public int solution(String[][] clothes) {
    //옷 종류별 개수 정보 map 초기화
    for (int i = 0; i < clothes.length; i++) {
      String type = clothes[i][1];
      if (map.containsKey(type)) {
        map.put(type, map.get(type) + 1);
      } else {
        map.put(type, 1);
      }
    }

    //계산
    int answer = 1;
    for (String key : map.keySet()) {
      int currentCount = map.get(key);
      answer *= currentCount + 1; //종류당 옷 개수 + 입지 않는 경우
    }
    answer--; //모든 옷을 입지 않는 경우 빼기

    //정답출력
    return answer;
  }
}