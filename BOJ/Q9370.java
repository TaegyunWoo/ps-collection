import java.util.*;
import java.io.*;

public class Q9370 {
  static int testCase;
  static int n; //교차로(node) 개수
  static int m; //도로(edge) 개수
  static int t; //목적지 후보 개수
  static int s; //시작 교차로
  static int g; //냄새를 맡은 도로의 교차로1
  static int h; //냄새를 맡은 도로의 교차로2
  static ArrayList<Integer> destinationList; //목적지 후보
  static ArrayList<ArrayList<Node>> graph;
  static int[] shortestTable;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    testCase = Integer.parseInt(br.readLine());

    while (testCase-- > 0) {

      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      t = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(br.readLine());
      s = Integer.parseInt(st.nextToken());
      g = Integer.parseInt(st.nextToken());
      h = Integer.parseInt(st.nextToken());

      //graph 초기화
      graph = new ArrayList<>();
      for (int i = 0; i < n + 1; i++) {
        graph.add(new ArrayList<>());
      }
      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        //(g,h)에 해당하는 길이면, 홀수로 표시 (같은 가중치인 경우, (g,h) 길이 무조건 선택되도록 '-1'로 처리)
        if ((a == g && b == h) || (a == h && b == g)) {
          graph.get(a).add(new Node(b, d * 2 - 1));
          graph.get(b).add(new Node(a, d * 2 - 1));

          //(g,h)에 해당하는 길이 아니면, 짝수로 표시
        } else {
          graph.get(a).add(new Node(b, d * 2));
          graph.get(b).add(new Node(a, d * 2));
        }
      }

      /*
       * 목적지 후보 초기화
       */
      destinationList = new ArrayList<>();
      for (int i = 0; i < t; i++) {
        int x = Integer.parseInt(br.readLine());
        destinationList.add(x);
      }

      //최단거리 테이블 초기화
      shortestTable = new int[n + 1];
      Arrays.fill(shortestTable, Integer.MAX_VALUE / 2 * 2);

      //다익스트라
      dij(s);

      //정답 출력
      destinationList.sort(Comparator.naturalOrder());
      for (int i = 0; i < t; i++) {
        int destinationIndex = destinationList.get(i);
        if (shortestTable[destinationIndex] % 2 == 0) continue; //짝수인 경우, (g,h) 길을 지나지 않은 것이므로 생략
        System.out.print(destinationIndex + " ");
      }
    }

    br.close();
  }

  private static void dij(int start) {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    queue.offer(new Node(start, 0));
    shortestTable[start] = 0;

    while (!queue.isEmpty()) {
      Node currentNode = queue.poll();
      int currentIndex = currentNode.index;
      double currentDistance = currentNode.distance;

      if (shortestTable[currentIndex] < currentDistance) continue;

      ArrayList<Node> nextNodeList = graph.get(currentIndex);
      for (Node nextNode : nextNodeList) {
        int distanceOfStartToNext = shortestTable[currentIndex] + nextNode.distance;
        int nextIndex = nextNode.index;

        if (shortestTable[nextIndex] > distanceOfStartToNext) {
          shortestTable[nextIndex] = distanceOfStartToNext;
          queue.offer(new Node(nextIndex, distanceOfStartToNext));
        }
      }
    }
  }


  static class Node implements Comparable<Node> {
    public int index;
    public int distance;

    public Node(int index, int distance) {
      this.index = index;
      this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
      if (this.distance < other.distance) return -1;
      return 1;
    }
  }
}
