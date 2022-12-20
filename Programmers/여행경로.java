import java.util.*;

class Solution {
  HashMap<String, List<String>> ticketMap; //<출발공항, 도착공항_목록>
  int totalTicket = 0;
  PriorityQueue<String> answerQueue = new PriorityQueue<>();

  public String[] solution(String[][] tickets) {
    totalTicket = tickets.length;

    //ticketMap 초기화
    ticketMap = new HashMap<>();
    for (int i = 0; i < tickets.length; i++) {
      String start = tickets[i][0];
      String end = tickets[i][1];
      if (ticketMap.containsKey(start)) {
        ticketMap.get(start).add(end);
      } else {
        List<String> endList = new LinkedList<>();
        endList.add(end);
        ticketMap.put(start, endList);
      }
    }

    //dfs
    String[] visitCityAry = new String[totalTicket];
    dfs("ICN", 0, visitCityAry);

    //정답출력
    return answerQueue.poll().split(",");
  }

  private void dfs(String start, int ticketCount, String[] visitCityAry) {
    if (ticketCount == totalTicket) {
      //방문순서 저장
      save(visitCityAry);
      return;
    }

    List<String> nextList = ticketMap.get(start);

    if (nextList == null) return; //만약 start에서 출발하는 티켓이 없는 경우

    int nextSize = nextList.size();
    for (int i = 0; i < nextSize; i++) {
      String next = nextList.get(i);

      //해당 티켓 사용
      nextList.remove(i);
      visitCityAry[ticketCount] = next;
      dfs(next, ticketCount + 1, visitCityAry);

      //해당 티켓 사용 X
      nextList.add(i, next);
    }
  }

  private void save(String[] visitCityAry) {
    StringBuilder tmp = new StringBuilder("ICN,");
    for (int i = 0; i < visitCityAry.length; i++) {
      if (visitCityAry[i] == null) break;
      tmp.append(visitCityAry[i]);
      tmp.append(",");
    }
    answerQueue.offer(tmp.toString());
  }
}