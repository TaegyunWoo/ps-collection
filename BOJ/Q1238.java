import java.io.*;
import java.util.*;

/**
 * ['각 마을에서 파티까지 가는 최단거리' 구하기]
 * - 시작점을 하나씩 설정하여 다익스트라 알고리즘을 수행하면, 총 (n-1)번 수행해야 한다.
 * - 이것은 비효율적이다.
 * - "마을 → 파티"를 나타내는 간선의 방향을 반대로 바꾼 후, 파티에서 마을로 가는 최단거리를 구하면 된다.
 * - 즉 간선의 방향을 반대로 취한 후, 파티에서 마을로 가는 최단거리를 다익스트라 알고리즘으로 구한다면,
 *   "마을에서 파티로 가는 최단거리 테이블"을 완성시킬 수 있다.
 *
 * ['파티에서 각 마을까지 가는 최단거리' 구하기]
 * - 기존의 다익스트라 알고리즘을 사용하면 된다.
 *
 * ['가장 오래걸리는 사람' 구하기]
 * - 최단거리로 왔다갔다했을 때, 그 중 가장 오래 걸린 사람을 구해야한다.
 * - 각 마을마다 1명의 학생이 있으므로,
 *   'a마을 학생이 오고가며 걸린 총 시간', 'b마을 학생이 오고가며 걸린 총 시간', ...
 *   을 '위에서 만든 총 2개의 최단거리 테이블'로 구할 수 있다.
 */
public class Q1238 {

  static int n;
  static int m;
  static int x;
  static List<List<Node>> normalGraph = new ArrayList<>();
  static List<List<Node>> reverseGraph = new ArrayList<>();
  static int[] villageToPartyTable;
  static int[] partyToVillageTable;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    x = Integer.parseInt(st.nextToken());

    for (int i = 0; i < n + 1; i++) {
      normalGraph.add(new ArrayList<>());
      reverseGraph.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      normalGraph.get(a).add(new Node(b, d)); //정상 그래프
      reverseGraph.get(b).add(new Node(a, d)); //방향이 역방향인 그래프
    }

    solution();
  }

  private static void solution() {
    //각 마을에서 파티로 가는 최단거리 구하기
    dijVillageToParty(x);

    //파티에서 각 마을로 가능 최단거리 구하기
    dijPartyToVillage(x);

    //각 사람마다 걸리는 총 시간 계산
    int result = 0;
    for (int person = 1; person < n + 1; person++) {
      int totalTime = villageToPartyTable[person] + partyToVillageTable[person];
      if (result < totalTime) {
        result = totalTime;
      }
    }

    System.out.print(result);
  }

  /**
   * 간선의 방향을 반대로 하여, 각 마을에서 파티로 가는 최소비용 구하기
   * 다익스트라 알고리즘 메서드 활용
   * @param destination 목적지 (간선 방향을 뒤집어서 계산하기 때문에, 출발점이 x(파티)이다.)
   */
  private static void dijVillageToParty(int destination) {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    villageToPartyTable = new int[n+1];
    Arrays.fill(villageToPartyTable, (int)1e9);
    queue.offer(new Node(destination, 0));
    villageToPartyTable[destination] = 0;

    while (!queue.isEmpty()) {
      Node current = queue.poll();

      if (villageToPartyTable[current.getIndex()] < current.getDistance()) {
        continue;
      }

      List<Node> nearList = reverseGraph.get(current.getIndex());
      for (Node near : nearList) {
        if (villageToPartyTable[near.getIndex()] > villageToPartyTable[current.getIndex()] + near.getDistance()) {
          villageToPartyTable[near.getIndex()] = villageToPartyTable[current.getIndex()] + near.getDistance();
          queue.offer(new Node(near.getIndex(), villageToPartyTable[near.getIndex()]));
        }
      }
    }
  }

  /**
   * 파티에서 각 마을로 가는 최소비용 구하기
   * 다익스트라 알고리즘 메서드 활용
   * @param start
   */
  private static void dijPartyToVillage(int start) {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    partyToVillageTable = new int[n+1];
    Arrays.fill(partyToVillageTable, (int)1e9);
    queue.offer(new Node(start, 0));
    partyToVillageTable[start] = 0;

    while (!queue.isEmpty()) {
      Node current = queue.poll();

      if (partyToVillageTable[current.getIndex()] < current.getDistance()) {
        continue;
      }

      List<Node> nearList = normalGraph.get(current.getIndex());
      for (Node near : nearList) {
        if (partyToVillageTable[near.getIndex()] > partyToVillageTable[current.getIndex()] + near.getDistance()) {
          partyToVillageTable[near.getIndex()] = partyToVillageTable[current.getIndex()] + near.getDistance();
          queue.offer(new Node(near.getIndex(), partyToVillageTable[near.getIndex()]));
        }
      }
    }
  }

  static class Node implements Comparable<Node> {
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