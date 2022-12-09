import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Q9205 {
  static ArrayList<int[]> nodeList;
  static boolean[] visited;
  static int t; //테스트케이수 수
  static int n; //편의점 개수

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    t = Integer.parseInt(br.readLine());

    //테스트케이스마다 반복
    while (t-- > 0) {
      n = Integer.parseInt(br.readLine());

      nodeList = new ArrayList<>();
      visited = new boolean[n + 2];

      //노드 저장
      for (int i = 0; i < n+2; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        nodeList.add(new int[]{x, y});
      }

      //BFS 수행
      bfs();
    }

    br.close();
  }

  static void bfs() {
    int beerCount = 20;
    Deque<int[]> deque = new ArrayDeque<>();

    //집에서 출발
    visited[0] = true;
    deque.addLast(nodeList.get(0));

    while (!deque.isEmpty()) {
      int[] nowNode = deque.pollFirst();

      for (int i = 0; i < nodeList.size(); i++) {
        int[] nearNode = nodeList.get(i);

        //갈수없는 조건인 경우
        if (visited[i]) continue;
        int willDrinkBeerCount = countDrinkBeer(nowNode, nearNode);
        if (willDrinkBeerCount > beerCount) continue;

        //방문
        visited[i] = true;
        deque.addLast(nearNode);
        beerCount -= willDrinkBeerCount;

        //방문한 곳이 편의점인 경우
        if (i > 0 && i < nodeList.size()-1) {
          beerCount = 20;
        }
        //방문한 곳이 락페스티벌인 경우
        else if (i == nodeList.size()-1) {
          System.out.println("happy");
          return;
        }
      }
    }

    System.out.println("sad");
  }

  //이동하기 위해 마셔야하는 맥주 개수 구하기
  static int countDrinkBeer(int[] currentNode, int[] nextNode) {
    double distance = Math.abs(currentNode[0] - nextNode[0]) + Math.abs(currentNode[1] - nextNode[1]);
    int drinkBeerCount = (int) Math.ceil(distance / 50);
    return drinkBeerCount;
  }

}
