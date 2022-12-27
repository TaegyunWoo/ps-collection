import java.util.*;

class Solution {
  PriorityQueue<Car> carQueue = new PriorityQueue<>();
  int lastCameraLocation = -30001;
  int answer = 0;

  public int solution(int[][] routes) {
    //carList 초기화
    for (int i = 0 ; i < routes.length; i++) {
      carQueue.offer(new Car(routes[i][0], routes[i][1]));
    }

    while (!carQueue.isEmpty()) {
      Car car = carQueue.poll();
      if (car.start > lastCameraLocation) {
        lastCameraLocation = car.end;
        answer++;
      }
    }

    return answer;
  }

  class Car implements Comparable<Car> {
    public int start;
    public int end;
    public Car(int s, int e) {
      this.start = s;
      this.end = e;
    }

    @Override
    public int compareTo(Car other) {
      if (this.end < other.end) return -1;
      return 1;
    }
  }
}