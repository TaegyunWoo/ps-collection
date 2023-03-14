import java.util.*;
import java.io.*;

public class Q17471 {
  public static final int INF = (int) 1e9;
  public static int n; //구역 개수
  public static int[] peopleAry; //인구수
  public static List<List<Integer>> graph;
  public static boolean[] combinationVisited;
  public static int answer = INF;

  public static void main(String[] args) throws IOException {
    //input 처리
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    peopleAry = new int[n+1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= n; i++) {
      peopleAry[i] = Integer.parseInt(st.nextToken());
    }
    graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      int nearCount = Integer.parseInt(st.nextToken());
      for (int u = 0; u < nearCount; u++) {
        int nearIndex = Integer.parseInt(st.nextToken());
        graph.get(i).add(nearIndex);
        graph.get(nearIndex).add(i);
      }
    }
    combinationVisited = new boolean[n+1];
    br.close();

    //Solution
    for (int r = 1; r < n; r++) {
      combination(1, r);
    }

    if (answer == INF) System.out.println(-1);
    else System.out.println(answer);
  }

  private static void combination(int depth, int r) {
    if (r == 0) {
      //bfs
      boolean isValidA = isValid(true);
      boolean isValidB = isValid(false);
      if (isValidA && isValidB) {
        answer = Math.min(answer, getPeopleGap());
      }

      return;
    }

    for (int i = depth; i <= n; i++) {
      combinationVisited[i] = true;
      combination(i+1, r-1);
      combinationVisited[i] = false;
    }
  }

  private static boolean isValid(boolean isAGu) {
    HashSet<Integer> sameGuSet = new HashSet<>();
    int startIndex = 0;
    for (int i = 1; i <= n; i++) {
      if (isAGu) {
        if (combinationVisited[i]) {
          sameGuSet.add(i);
          startIndex = i;
        }
      } else {
        if (!combinationVisited[i]) {
          sameGuSet.add(i);
          startIndex = i;
        }
      }
    }

    boolean[] visited = new boolean[n+1];
    Deque<Integer> deque = new ArrayDeque<>();
    deque.offerLast(startIndex);
    visited[startIndex] = true;
    int visitCount = 1;

    while (!deque.isEmpty()) {
      int currentIndex = deque.pollFirst();
      for (int nearIndex : graph.get(currentIndex)) {
        if (visited[nearIndex]) continue; //방문한 구역이라면
        if (!sameGuSet.contains(nearIndex)) continue; //같은 선거구가 아니라면
        visited[nearIndex] = true;
        deque.offerLast(nearIndex);
        visitCount++;
      }
    }

    return sameGuSet.size() == visitCount;
  }

  private static int getPeopleGap() {
    int countA = 0;
    int countB = 0;
    for (int i = 1; i <= n; i++) {
      if (combinationVisited[i]) countA += peopleAry[i];
      else countB += peopleAry[i];
    }

    return Math.abs(countA - countB);
  }
}
