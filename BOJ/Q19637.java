import java.io.*;
import java.util.*;

public class Q19637 {
  public static int n;
  public static int m;
  public static HashMap<Integer, String> titleMap = new HashMap<>();
  public static List<Integer> limitList = new ArrayList<>();
  public static int[] scoreAry;
  public static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      String title = st.nextToken();
      int limit = Integer.parseInt(st.nextToken());
      if (titleMap.containsKey(limit)) continue; //가장 먼저 저장된것만 유지
      titleMap.put(limit, title);
      limitList.add(limit);
    }
    scoreAry = new int[m];
    for (int i = 0; i < m; i++) {
      scoreAry[i] = Integer.parseInt(br.readLine());
    }

    //Solution
    for (int i = 0; i < m; i++) {
      String result = getTitle(scoreAry[i]);
      answer.append(result + "\n");
    }

    //정답 출력
    System.out.println(answer.toString());
  }

  private static String getTitle(int score) {
    //Binary Search - Lower Bound
    int min = 0;
    int max = limitList.size();
    int mid = (min + max) / 2;

    while (min < max) {
      int limit = limitList.get(mid);

      if (score < limit) {
        max = mid;

      } else if (score > limit) {
        min = mid + 1;

      } else {
        return titleMap.get(limitList.get(mid));
      }

      mid = (min + max) / 2;
    }

    return titleMap.get(limitList.get(mid));
  }
}
