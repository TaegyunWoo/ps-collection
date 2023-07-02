import java.util.*;

class Solution {
  HashMap<String, String> relationMap = new HashMap<>(); //<자식, 부모>
  HashMap<String, Integer> earnMap = new HashMap<>(); //<판매원, 수익금>

  public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
    //init
    for (int i = 0; i < enroll.length; i++) {
      relationMap.put(enroll[i], referral[i]);
      earnMap.put(enroll[i], 0);
    }

    //수익금 계산
    calculateEarnMoney(seller, amount);

    //정답
    int[] answer = new int[enroll.length];
    for (int i = 0; i < enroll.length; i++) {
      answer[i] = earnMap.get(enroll[i]);
    }

    return answer;
  }

  //수익금 계산
  private void calculateEarnMoney(String[] seller, int[] amount) {

    for (int i = 0; i < seller.length; i++) {
      String currentName = seller[i];
      String parentName = relationMap.get(currentName);
      int currentMoney = amount[i] * 100;

      while (true) {
        int realEarnMoney = currentMoney - (currentMoney / 10);

        earnMap.put(currentName, earnMap.get(currentName) + realEarnMoney);

        if (parentName.equals("-") || realEarnMoney == 0) break;

        currentMoney /= 10;
        currentName = parentName;
        parentName = relationMap.get(currentName);
      }
    }
  }
}