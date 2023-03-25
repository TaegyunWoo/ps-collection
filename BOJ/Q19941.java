import java.util.*;
import java.io.*;

public class Q19941 {
  public static int n;
  public static int k;
  public static String input;
  public static List<Integer> personIndexList = new ArrayList<>();
  public static boolean[] visited;
  public static int answer;

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    input = br.readLine();
    visited = new boolean[n];
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == 'P') personIndexList.add(i);
    }

    //Solution
    solution(0, 0);

    //정답 출력
    System.out.println(answer);
  }

  //백트래킹 (depth == depth 번째 사람)
  public static void solution(int depth, int count) {
    if (depth == personIndexList.size()) {
      //정답 저장
      answer = Math.max(answer, count);

      return;
    }

    int currentPersonIndex = personIndexList.get(depth);
    boolean canSelect = false;

    //선택할 수 있는 햄버거가 있다면, 그 중 가장 앞에 있는 햄버거 선택
    for (int i = currentPersonIndex - k; i <= currentPersonIndex + k; i++) {
      if (i < 0 || i >= n) continue;
      if (input.charAt(i) != 'H') continue;
      if (visited[i]) continue;

      visited[i] = true; //선택 가능한 햄버거 중, 가장 앞에 있는 햄버거 선택
      solution(depth+1, count+1); //다음 사람 차례
      canSelect = true; //선택 성공
      break;
    }

    //햄버거를 선택하지 못한 경우
    if (!canSelect) solution(depth+1, count);
  }
}
