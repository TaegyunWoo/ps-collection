import java.util.*;
import java.io.*;

public class Q1865 {
  static final int INF = (int) 1e9;
  static int tc; //테이스케이스 개수
  static int n; //지점 개수
  static int m; //도로 개수
  static int w; //웜홀 개수
  static ArrayList<Edge> edgeList;
  static int[] shortestTable;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    tc = Integer.parseInt(br.readLine());

    //테스트케이스만큼 반복
    while (tc-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      w = Integer.parseInt(st.nextToken());

      //최단거리 테이블 초기화
      shortestTable = new int[n + 1];
      Arrays.fill(shortestTable, INF);

      //edgeList 초기화
      edgeList = new ArrayList<>();
      for (int i = 0; i < m; i++) { //도로
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int distance = Integer.parseInt(st.nextToken());
        edgeList.add(new Edge(from, to, distance));
        edgeList.add(new Edge(to, from, distance));
      }
      for (int i = 0; i < w; i++) { //웜홀
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int distance = Integer.parseInt(st.nextToken());
        edgeList.add(new Edge(from, to, -distance));
      }

      //벨만포드
      boolean result = bellman(1);

      //정답 출력
      if (result) System.out.println("NO");
      else System.out.println("YES");
    }

    br.close();
  }

  /**
   * 벨만포드 수행 메서드
   * @param startIndex
   * @return true:음수간선 사이클 X / false:음수간선 사이클 O
   */
  private static boolean bellman(int startIndex) {
    shortestTable[startIndex] = 0;

    //노드 개수만큼 반복
    for (int i = 0; i < n; i++) {
      //모든 간선마다 반복
      for (Edge edge : edgeList) {
        int current = edge.from;
        int next = edge.to;
        int distanceOfCurrentToNext = edge.distance;

        /*
         * [만약 시작노드(from)으로 도달하는 최단거리가 갱신되지 않았으면 생략하는 코드]
         * 하지만 이 코드는 제외해야 한다.
         * 왜냐하면, '시작노드에서 도달할 수 없는 노드'가 음수 간선 사이클을 만들 수 있기 때문이다.
         * 즉 shortestTable[current] == INF 이라면, 현재 시작 노드에서 current 노드로 이동할 수 없다는 의미이고,
         * current 노드로 이동할 수 없더라도, current 노드가 음수 간선을 만들 수 있기 때문에
         * 도달하지 못하는 경우에도 벨만포드 연산을 해야 한다.
         */
//        if (shortestTable[current] == INF) continue;

        //만약 최단거리테이블의 'start~next' 거리값이 더 크다면
        int distanceOfStartToNext = shortestTable[current] + distanceOfCurrentToNext;
        if (shortestTable[next] > distanceOfStartToNext) {
          shortestTable[next] = distanceOfStartToNext;

          /*
           * 음수간선 사이클 확인
           * 만약 n번째 반복에서도 갱신된다면 음수 사이클이 존재하는 것
           */
          if (i == n-1) {
            return false;
          }
        }
      }
    }

    return true;
  }

  //간선 정보 클래스
  public static class Edge {
    public int from;
    public int to;
    public int distance;

    public Edge(int from, int to, int distance) {
      this.from = from;
      this.to = to;
      this.distance = distance;
    }
  }

}
