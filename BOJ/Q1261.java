import java.util.*;
import java.io.*;

public class Q1261 {
  static int m; //가로 길이
  static int n; //세로 길이
  static ArrayList<ArrayList<Node>> graph;
  static int[] shortestTable;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    m = Integer.parseInt(st.nextToken());
    n = Integer.parseInt(st.nextToken());


    //info
    int[][] infoMap = new int[n + 1][m + 1]; //infoMap[a][b] = (a, b)의 값
    for (int i = 1; i <= n; i++) {
      String[] info = br.readLine().split("");
      for (int u = 1; u <= m; u++) {
        infoMap[i][u] = Integer.parseInt(info[u-1]);
      }
    }

    shortestTable = new int[n * m + 1];
    Arrays.fill(shortestTable, (int) 1e9);

    //그래프 초기화
    int[] d = {-m, m, -1, 1};
    graph = new ArrayList<>(); //(1,1) = 1번노드, (1,2) = 2번노드
    for (int i = 0; i <= n * m; i++) {
      graph.add(new ArrayList<>());
    }
    for (int currentIndex = 1; currentIndex < n * m + 1; currentIndex++) {
      for (int u = 0; u < 4; u++) {
        int nearIndex = currentIndex + d[u];
        if (nearIndex < 1 || nearIndex > n*m) continue;
        if (m != 1 && currentIndex % m == 0 && nearIndex == currentIndex + 1) continue; //줄바꿈 되는 경우
        if (m != 1 && currentIndex % m == 1 && nearIndex == currentIndex - 1) continue; //줄바꿈 되는 경우
        int a = ((nearIndex-1) / m) + 1;
        int b = ((nearIndex-1) % m) + 1;
        System.out.println(currentIndex + " -> " + nearIndex + " : " + infoMap[a][b] + " ");
        graph.get(currentIndex).add(new Node(nearIndex, infoMap[a][b]));
      }
    }

    //다익스트라
    dij(1);

    //정답 출력
    System.out.println(shortestTable[n*m]);
  }

  private static void dij(int startIndex) {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    queue.offer(new Node(startIndex, 0));
    shortestTable[startIndex] = 0;

    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();
      int currentIndex = currentNode.index;
      int currentDistance = currentNode.distance;

      if (currentDistance > shortestTable[currentIndex]) continue;

      ArrayList<Node> nearNodeList = graph.get(currentIndex);
      for (Node nearNode : nearNodeList) {
        int nearIndex = nearNode.index;
        int distanceOfStartToNear = shortestTable[currentIndex] + nearNode.distance;
        if (shortestTable[nearIndex] > distanceOfStartToNear) {
          shortestTable[nearIndex] = distanceOfStartToNear;
          queue.offer(new Node(nearIndex, distanceOfStartToNear));
        }
      }
    }
  }

  public static class Node implements Comparable<Node> {
    public int index;
    public int distance;

    public Node(int i, int d) {
      index = i;
      distance = d;
    }

    @Override
    public int compareTo(Node other) {
      if (this.distance < other.distance) return -1;
      return 1;
    }
  }
}
