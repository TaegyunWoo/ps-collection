import java.io.*;
import java.util.*;

public class Q22233 {
  public static int n;
  public static int m;
  public static String[] postAry;
  public static HashSet<String> memoSet = new HashSet<>();
  public static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    for (int i = 0; i < n; i++) {
      memoSet.add(br.readLine());
    }
    postAry = new String[m];
    for (int i = 0; i < m; i++) {
      postAry[i] = br.readLine();
    }

    //Solution
    solution();

    //정답 출력
    System.out.println(answer);
  }

  private static void solution() {
    for (int i = 0; i < postAry.length; i++) {
      String[] postKeywordAry = postAry[i].split(",");
      int count = 0;
      int totalSize = memoSet.size();

      for (String keyword : postKeywordAry) {
        if (memoSet.contains(keyword)) {
          count++;
          memoSet.remove(keyword);
        }
      }

      answer.append(totalSize - count + "\n");
    }
  }
}
