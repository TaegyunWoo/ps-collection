import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2606 {
  static ArrayList<ArrayList<Integer>> graph;
  static boolean[] visitedNode;
  static int totalNumber;
  static int pairNumber;
  static int answer = 0;

  public static void main(String[] args) throws IOException {
    init();
    dfs(1);
    answer--;
    System.out.println(answer);
  }

  private static void dfs(int nowNodeIndex) {
    answer++;
    visitedNode[nowNodeIndex] = true;
    ArrayList<Integer> nearNodeList = graph.get(nowNodeIndex);
    nearNodeList.stream().forEach(nearNode -> {
      if (!visitedNode[nearNode]) dfs(nearNode);
    });
  }

  private static void init() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    totalNumber = Integer.parseInt(br.readLine());
    pairNumber = Integer.parseInt(br.readLine());

    //그래프 초기화
    graph = new ArrayList<>();
    for (int i = 0; i <= totalNumber; i++) {
      graph.add(new ArrayList<>());
    }

    //방문 캐싱 배열 초기화
    visitedNode = new boolean[totalNumber+1];

    //그래프 만들기
    for (int i = 0; i < pairNumber; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int nodeA = Integer.parseInt(st.nextToken());
      int nodeB = Integer.parseInt(st.nextToken());
      graph.get(nodeA).add(nodeB);
      graph.get(nodeB).add(nodeA);
    }
  }
}
