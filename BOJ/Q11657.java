import java.io.*;
import java.util.*;

/**
 * [해결방법]
 * 벨만포드 알고리즘을 사용한다.
 * 최단거리 테이블을 long형 배열로 선언해야, 언더플로우가 발생하지 않는다.
 * 노드가 2개일 때, 벨만포드는 무조건 -1을 반환하므로 예외처리가 필요하다.
 */
public class Q11657 {

  static int n; //도시 개수
  static int m; //버스 노선 개수
  static List<Edge> edgeList = new ArrayList<>();

  public static void main(String[] args)throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int distance = Integer.parseInt(st.nextToken());
      edgeList.add(new Edge(from, to, distance));
    }

    //노드가 2개인 경우 처리
    if (n == 2) {
      long tmp = (int) 1e9;
      for (int i = 0; i < edgeList.size(); i++) {
        if (tmp > edgeList.get(i).getDistance()) {
          tmp = edgeList.get(i).getDistance();
        }
      }
      System.out.println(tmp);
      return;
    }

    solution();
  }

  private static void solution() {
    long[] table = new long[n+1];
    Arrays.fill(table, (int) 1e9);

    table[1] = 0; //시작노드 초기화

    //노드 개수만큼 반복
    for (int i = 1; i <= n; i++) {

      //매 반복마다 모든 간선 확인
      for (int edgeIndex = 0; edgeIndex < m; edgeIndex++) {
        int from = edgeList.get(edgeIndex).getFrom();
        int to = edgeList.get(edgeIndex).getTo();
        long distance = edgeList.get(edgeIndex).getDistance();

        if (table[from] == (int) 1e9) continue; //만약 아직 방문하지 않은 노드면 생략

        //만약 최단거리 테이블에 저장된 값이 더 크다면 갱신
        if (table[to] > table[from] + distance) {

          //만약 노드 개수번째의 반복이라면 음수간선 사이클이 존재하므로
          if (i == n-1) {
            System.out.println(-1);
            return;
          }

          table[to] = table[from] + distance; //갱신신
        }
      }
    }

    for (int i = 2; i < table.length; i++) {
      if (table[i] == (int) 1e9) {
        System.out.println(-1);
      } else {
        System.out.println(table[i]);
      }
    }

  }

  private static class Edge {
    private int from;
    private int to;
    private long distance;

    public Edge() {}

    public Edge(int from, int to, long distance) {
      this.from = from;
      this.to = to;
      this.distance = distance;
    }

    public int getFrom() {
      return from;
    }

    public int getTo() {
      return to;
    }

    public long getDistance() {
      return distance;
    }
  }

}