import java.io.*;
import java.util.*;

/**
 * [point]
 * 정점1에서부터 정점A와 정점B를 모두 경유하여, 정점n으로 가는 최단거리를 구해야한다.
 *
 * [풀이]
 * "정점A를 먼저 방문하는 경우 (1 -> A -> n)" 와 "정점B를 먼저 방문하는 경우 (1 -> B -> n)"으로 구분할 수 있다.
 * 위 두가지 경우 중, 가장 작은 것이 정답이다.
 *
 * [실수 요인 1]
 * 정점1에서 A와 B 중 가장 가까운 정점을 먼저 방문한 뒤, 다음 정점을 방문하고 최종적으로 정점n으로 가는 거리를 구하면 되는 줄 알았다.
 * 하지만 이러한 로직으로는 답을 구할 수 없다.
 * 반례는 아래와 같다.
 *
 * [반례]
 * (1 <-> a) 거리: 10
 * (1 <-> b) 거리: 11
 * (a <-> b) 거리: 3
 * (a <-> n) 거리: 1
 * (b <-> b) 거리: 4
 *
 * [실수 요인 2]
 * 목적지까지 도달하지 못하는 경우를 생각하지 못했다.
 * 문제를 꼼꼼히 읽지 않아 발생한 실수이다.
 */
public class Q1504 {

  static int n;
  static int e;
  static List<List<Node>> graph = new ArrayList<>();
  static int[] table;
  static int mustVisitNodeA;
  static int mustVisitNodeB;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    e = Integer.parseInt(st.nextToken());

    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());

      graph.get(a).add(new Node(b, d));
      graph.get(b).add(new Node(a, d));
    }

    st = new StringTokenizer(br.readLine());
    mustVisitNodeA = Integer.parseInt(st.nextToken());
    mustVisitNodeB = Integer.parseInt(st.nextToken());

    solution();
  }

  private static void solution() {
    //정점A 먼저 방문하는 경우
    int resultA = 0;
    dij(1);
    if (table[mustVisitNodeA] == Integer.MAX_VALUE) { //정점1과 정점A가 서로 연결되어 있지 않을때
      System.out.print(-1);
      return;
    }
    resultA += table[mustVisitNodeA]; //1~A 까지의 거리

    dij(mustVisitNodeA);
    resultA += table[mustVisitNodeB]; //A~B 까지의 거리

    dij(mustVisitNodeB);
    resultA += table[n]; //B~n 까지의 거리
    if (table[n] == Integer.MAX_VALUE) { //정점A와 정점n이 서로 연결되어 있지 않을때
      resultA = -1;
    }


    //정점B 먼저 방문하는 경우
    int resultB = 0;
    dij(1);
    if (table[mustVisitNodeB] == Integer.MAX_VALUE) { //정점1과 정점B가 서로 연결되어 있지 않을때
      System.out.print(-1);
      return;
    }
    resultB += table[mustVisitNodeB]; //1~B 까지의 거리

    dij(mustVisitNodeB);
    resultB += table[mustVisitNodeA]; //B~A 까지의 거리

    dij(mustVisitNodeA);
    resultB += table[n]; //A~n 까지의 거리
    if (table[n] == Integer.MAX_VALUE) { //정점A와 정점n이 서로 연결되어 있지 않을때
      resultB = -1;
    }


    //정답 출력
    if (resultA == -1 && resultB == -1) {
      System.out.print(-1);
    } else {
      System.out.print(Math.min(resultA, resultB));
    }
  }


  private static void dij(int start) {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    queue.offer(new Node(start, 0));
    table = new int[n+1];
    Arrays.fill(table, Integer.MAX_VALUE);
    table[start] = 0;

    while (!queue.isEmpty()) {
      Node current = queue.poll();

      if (table[current.getIndex()] < current.getDistance()) continue;

      List<Node> nearList = graph.get(current.getIndex());
      for (Node near : nearList) {
        if (table[near.getIndex()] > table[current.getIndex()] + near.getDistance()) {
          table[near.getIndex()] = table[current.getIndex()] + near.getDistance();
          queue.offer(new Node(near.getIndex(), table[near.getIndex()]));
        }
      }
    }
  }

  private static class Node implements Comparable<Node> {
    private int index;
    private int distance;

    public Node() {}

    public Node(int index, int distance) {
      this.index = index;
      this.distance = distance;
    }

    public int getIndex() {
      return index;
    }

    public int getDistance() {
      return distance;
    }

    @Override
    public int compareTo(Node other) {
      if (this.distance < other.distance) {
        return -1;
      } else if (this.distance > other.distance) {
        return 1;
      } else {
        return 0;
      }
    }
  }
}