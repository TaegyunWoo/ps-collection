import java.util.*;

class Solution {
  Deque<Truck> bridgeDeque = new ArrayDeque<>(); //다리 위에 존재하는 트럭 저장

  public int solution(int bridge_length, int weight, int[] truck_weights) {
    int currentSec = 0; //현재 초
    int totalWeight = 0; //현재 다리 위의 무게

    //트럭마다 반복
    for (int i = 0; i < truck_weights.length; i++) {
      currentSec++; //1초 증가

      //트럭 건넘 처리
      if (!bridgeDeque.isEmpty()) {
        Truck t = bridgeDeque.peekFirst();
        if (bridge_length + t.startSec == currentSec) {
          bridgeDeque.pollFirst();
          totalWeight -= t.weight;
        }
      }

      //건너지 못하는 경우
      if (totalWeight + truck_weights[i] > weight) {
        i--;
        continue;
      }

      //건널 수 있는 경우
      bridgeDeque.offerLast(new Truck(truck_weights[i], currentSec));
      totalWeight += truck_weights[i];
    }

    //가장 마지막 트럭이 완전히 건너는 시간을 더해서 계산
    return currentSec + bridge_length;
  }

  class Truck {
    public int weight; //무게
    public int startSec; //건너기 시작한 초

    public Truck(int w, int s) {
      this.weight = w;
      this.startSec = s;
    }
  }
}