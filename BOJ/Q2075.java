import java.io.*;
import java.util.*;

public class Q2075 {
  static int n;
  static int[][] map;
  static List<Deque<Integer>> dequeList = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    map = new int[n][n];
    for (int i = 0; i < n; i++) {
      String[] tmp = br.readLine().split(" ");
      for (int u = 0; u < n; u++) {
        map[i][u] = Integer.parseInt(tmp[u]);
      }
    }

    //solution
    initDequeList(); //dequeList 초기화
    sortDescDequeList(); //deque의 첫 아이템을 기준으로 내림차순 정렬
    int answer = 0;
    for (int i = 0; i < n; i++) {
      Deque<Integer> lastDeque = dequeList.get(dequeList.size() - 1); //마지막 deque
      answer = lastDeque.pollLast(); //현재 가장 작은 값 저장
      if (lastDeque.size() == 0) { //사이즈가 0이면
        dequeList.remove(dequeList.size()-1); //dequeList에서 제거
      }
      sortDescDequeList(); //다시 내림차순 정렬
    }

    System.out.println(answer);
  }

  private static void initDequeList() {
    for (int c = 0; c < n; c++) {
      Deque<Integer> deque = new ArrayDeque<>();
      for (int r = 0; r < n; r++) {
        deque.offerLast(map[r][c]); //역순으로 삽입
      }
      dequeList.add(deque);
    }
  }

  private static void sortDescDequeList() {
    dequeList.sort((a, b) -> {
      return a.peekLast() - b.peekLast();
    });
  }
}