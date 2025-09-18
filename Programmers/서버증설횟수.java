import java.util.*;

public class 서버증설횟수 {
  private Deque<Integer> serverReturnDeque = new ArrayDeque<>();
  private int addedServer = 0;
  private int answer = 0;

  public int solution(int[] players, int m, int k) {
    for (int currentTime = 0; currentTime < 24; currentTime++) {
      //제거될 증설 서버 계산
      while (!serverReturnDeque.isEmpty()) {
        if (serverReturnDeque.peekFirst() == currentTime) {
          serverReturnDeque.pollFirst();
          addedServer--;
        } else {
          break;
        }
      }

      //필요한 서버 증설 계산
      int currentPlayers = players[currentTime];
      int needServer = (currentPlayers - addedServer * m) / m;
      if (needServer <= 0) continue;

      //서버 증설 기록
      addedServer += needServer;
      //정답 기록
      answer += needServer;

      //서버 반환 시각 기록
      for (int i = 0; i < needServer; i++) {
        serverReturnDeque.offerLast(currentTime + k);
      }
    }

    return answer;
  }
}